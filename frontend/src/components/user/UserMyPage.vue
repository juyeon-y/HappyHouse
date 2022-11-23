<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-8" my-2>
            <b-row class="my-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">별명</b-col
              ><b-col cols="6" align-self="start"><b-input v-model="member.nickname" :readonly="readonly"></b-input></b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row class="my-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
                ><b-col cols="6" align-self="start"><b-input v-model="member.name" :readonly="readonly"></b-input></b-col>
              <b-col cols="2"></b-col> 
            </b-row>
            <b-row class="my-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">비밀번호</b-col
                ><b-col cols="6" align-self="start"><b-input v-model="member.password" type="password" :readonly="readonly"></b-input></b-col>
              <b-col cols="2"></b-col>
            </b-row>

            <b-row class="my-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
                ><b-col cols="6" align-self="start"><b-input v-model="member.email" type="email" :readonly="readonly"></b-input></b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" class="mr-1" @click="changeReadonly" v-if="readonly">정보수정</b-button>
          <b-button variant="primary" class="mr-1" @click="changeUser" v-if="!readonly">제출</b-button>
          <b-button variant="danger" >회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>

  </b-container>
</template>

<script>
import axios from 'axios';
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      member:{
        nickname:'',
        password:'',
        name:'',
        email:'',
      },
      readonly:true
    }
  },
  async created(){
    await axios.get("http://localhost:9999/member/myInfo",{
      headers:{
        "Authorization" : sessionStorage.getItem("access-token"),
      }}).then(({data}) =>{
        console.log(data)
        this.member.nickname = data.nickname;
        this.member.password = data.password;
        this.member.name = data.name;
        this.member.email = data.email;
      })
    }
  ,
  methods:{
    changeReadonly(){
      this.readonly = !this.readonly;
    },
    changeUser(){
      axios.post("http://localhost:9999/member/update",this.member,{
        headers : {"Authorization" : sessionStorage.getItem("access-token")}
      }).then((response) =>{
        console.log(response);
        this.$router.go();
      })
    }
  }
};
</script>

<style></style>
