<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError">이메일 과 비밀번호를 확인하세요.</b-alert>
            <b-form-group label="이메일:" label-for="email">
              <b-input
                id="email"
                v-model="user.email"
                required
                placeholder="아이디 입력...."
                @keyup.enter="login"
                aria-describedby="input-live-feedback"
            :state="emailPasswordVaild"
              ></b-input>
              <b-form-invalid-feedback id="input-live-feedback">이메일과 비밀번호를 확인해 주세요 </b-form-invalid-feedback>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="password">
              <b-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="login"
              ></b-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="login">로그인</b-button>
            <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button>
            <b-button type="button" class="m-1" href="http://localhost:9999/oauth2/authorization/google">
              <b-icon-google></b-icon-google>
              구글 로그인
            </b-button>
          
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        email: null,
        password: null,
      },
      emailPasswordVaild:null
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo","MemberLogin"]),
    // async confirm() {
    //   await this.userConfirm(this.user);
    //   let token = sessionStorage.getItem("access-token");
    //   // console.log("1. confirm() token >> " + token);
    //   if (this.isLogin) {
    //     await this.getUserInfo(token);
    //     // console.log("4. confirm() userInfo :: ", this.userInfo);
    //     this.$router.push({ name: "main" });
    //   }
    // },
    async login(){
      await this.MemberLogin(this.user);
      this.$router.push('/');
      // await axios.post(`http://localhost:9999/login`,JSON.stringify(this.user)).then((response)=>{
      //   console.log(response);
      // })
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style></style>
