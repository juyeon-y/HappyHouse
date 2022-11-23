<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="userInfo.name === article.writer">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${article.title}</h3><div><h6>${article.writer}</div></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
          >작성일시: {{ article.reg_datetime | dateFormat }}
          <b-card-body class="text-left">
            <div v-html="message  "></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-col class="text-right" v-if="article.like === 1">
      <b-button
        variant="outline-info"
        size="sm"
        @click="likeDownArticle"
        class="mr-2"
      >
        <b-icon icon="star-fill" aria-hidden="true"></b-icon
      ></b-button>
    </b-col>
    <b-col class="text-right" v-else>
      <b-button
        variant="outline-info"
        size="sm"
        @click="likeUpArticle"
        class="mr-2"
        >추천</b-button
      >
    </b-col>

    <b-table striped hover :items="items" :fields="fields"
      ><template v-slot:cell(actions)="{ item }">
        <span
          ><b-button @click="likeDownComment(item.id)" v-if="item.likes === 1"
            ><b-icon icon="star-fill" aria-hidden="true"></b-icon
          ></b-button>
          <b-button variant="primary" @click="likeUpComment(item.id)" v-else
            >추천</b-button
          >
          <b-col v-if="userInfo.name === item.writer">
            <b-button
              variant="outline-danger"
              size="sm"
              @click="deleteComment(item.id)"
              >삭제</b-button
            >
          </b-col></span
        >
      </template>
    </b-table>
    <div>
      <b-form-input
        v-model="text"
        placeholder="댓글을 입력하세요"
      ></b-form-input>
      <b-button @click="writeComment()">등록</b-button>
    </div>
  </b-container>
</template>

<script>
import moment from "moment";
import { getArticle, getComment } from "@/api/board";
import { mapState } from "vuex";
import axios from "axios";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      items: [],
      fields: [
        { key: "comment", label: "댓글", tdClass: "tdSubject" },
        { key: "writer", label: "글쓴이", tdClass: "tdSubject" },
        { key: "liked", label: "추천수", tdClass: "tdSubject" },
        { key: "actions", label: "추천" },
      ],
      article: [],
      data: {},
      text: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = { code: this.$route.params.code, username: this.userInfo.name };
    // let param2 = { code: this.$route.params.code };
    getArticle(
      param,
      ({ data }) => {
        console.log(data);
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
    getComment(
      param,
      ({ data }) => {
        console.log(data);
        this.items = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { code: this.article.code },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { code: this.article.code },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
    likeUpArticle() {
      let param = {
        code: this.$route.params.code,
        id: this.userInfo.id,
      };
      axios
        .post("http://localhost:9999/board/likeUp", null, {
          params: param,
        })
        .then(({ data }) => {
          console.log(data);
          this.article.like = 1;
        });
    },
    likeDownArticle() {
      let param = {
        code: this.$route.params.code,
        id: this.userInfo.id,
      };
      axios
        .post("http://localhost:9999/board/likeDown", null, {
          params: param,
        })
        .then(({ data }) => {
          console.log(data);
          this.article.like = 0;
        });
    },
    writeComment() {
      let err = true;
      let msg = "";

      !this.text && ((msg = "내용 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else {
        let param = {
          code: this.$route.params.code,
          username: this.userInfo.name,
        };

        let a = {
          board_code: this.$route.params.code,
          writer: this.userInfo.name,
          comment: this.text,
          member_id: this.userInfo.id,
        };
        console.log(a);
        axios
          .post("http://localhost:9999/comment/write", null, {
            params: a,
          })
          .then(({ data }) => {
            console.log(data);
            this.data = data;
            this.text = "";
            getComment(
              param,
              ({ data }) => {
                console.log(data);
                this.items = data;
              },
              (error) => {
                console.log(error);
              }
            );
          });
      }
    },
    deleteComment(id) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        let a = {
          id: id,
        };
        axios
          .post("http://localhost:9999/comment/delete", null, {
            params: a,
          })
          .then(({ data }) => {
            console.log(data);
            this.data = data;
            this.text = "";
            let param = {
              code: this.$route.params.code,
              username: this.userInfo.name,
            };
            getComment(
              param,
              ({ data }) => {
                console.log(data);
                this.items = data;
              },
              (error) => {
                console.log(error);
              }
            );
          });
      }
    },
    likeUpComment(id) {
      let param = {
        comment_id: id,
        user_id: this.userInfo.id,
      };
      axios
        .post("http://localhost:9999/comment/likeUp", null, {
          params: param,
        })
        .then(({ data }) => {
          console.log(data);
          param = {
            code: this.$route.params.code,
            username: this.userInfo.name,
          };
          getComment(
            param,
            ({ data }) => {
              console.log(data);
              this.items = data;
            },
            (error) => {
              console.log(error);
            }
          );
        });
    },
    likeDownComment(id) {
      let param = {
        comment_id: id,
        user_id: this.userInfo.id,
      };
      axios
        .post("http://localhost:9999/comment/likeDown", null, {
          params: param,
        })
        .then(({ data }) => {
          console.log(data);
          param = {
            code: this.$route.params.code,
            username: this.userInfo.name,
          };
          getComment(
            param,
            ({ data }) => {
              console.log(data);
              this.items = data;
            },
            (error) => {
              console.log(error);
            }
          );
        });
    },
  },
  filters: {
    dateFormat(reg_datetime) {
      return moment(new Date(reg_datetime)).format("YY.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style></style>
