<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        >
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'boardview',
                params: { code: data.item.code },
              }"
            >
              {{ data.item.title }}
            </router-link>
          </template>
        </b-table>
        <span v-if="data.hasPreviousPage" @click="reload(data.prePage)"
          >&lt;&lt;</span
        >
        &nbsp; &nbsp; {{ data.pageNum }} &nbsp; &nbsp;
        <span v-if="data.hasNextPage" @click="reload(data.nextPage)"
          >&gt;&gt;</span
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";
import { listArticle } from "@/api/board";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "code", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "writer", label: "작성자", tdClass: "tdClass" },
        { key: "reg_datetime", label: "작성일", tdClass: "tdClass" },
        { key: "comment", label: "댓글수", tdClass: "tdClass" },
        { key: "like", label: "추천수", tdClass: "tdClass" },
      ],
      data: {},
    };
  },
  created() {
    let param = { pageNum: 1, pageSize: 5 };
    listArticle(
      param,
      ({ data }) => {
        console.log(data);
        this.articles = data.list;
        this.data = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { code: article.code },
      });
    },
    reload(pageNum) {
      console.log(pageNum);
      axios
        .get("http://localhost:9999/vue/board", null, {
          params: { pageNum, pageSize: 10 },
        })
        .then(({ data }) => {
          console.log("새 데이터", data);
          this.articles = data.list;
          this.data = data;
        });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
