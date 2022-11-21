<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>

    <div class="form-group">
        <label>Email address</label>
        <b-input
            id="input-1"
            v-model="member.email"
            type="email"
            placeholder="Enter email"
            required="required"
            aria-describedby="input-live-feedback email-not-duplicate"
            :state="emailDuplicated"></b-input>
            <b-form-valid-feedback id="email-not-duplicate">사용 가능한 이메일입니다.</b-form-valid-feedback> 
            <b-form-invalid-feedback id="input-live-feedback">사용 불가능한 이메일입니다. </b-form-invalid-feedback>

    </div>
    <div class="form-group">
        <label>Name</label>
        <b-input
            id="input-2"
            v-model="member.name"
            placeholder="Enter name"
            required="required"></b-input>
    </div>

    <div class="form-group">
        <label>Password</label>
        <b-input
            id="input-3"
            v-model="member.password"
            type="password"
            placeholder="Enter password"
            required="required"></b-input>
    </div>
    <div class="form-group">
        <label>Nickname</label>
        <b-input
            id="input-4"
            v-model="member.nickname"
            placeholder="Enter nickname"
            required="required"></b-input>
    </div>
    <div class>
      <button class="my-2 w-25 btn btn-lg btn-info" @click="checkEmail">이메일 중복체크</button>
    </div>
    <button class="w-25 btn btn-lg btn-primary" @click="register" >가입</button>
    
  </b-container>
</template>

<script>
import axios from 'axios';
export default {
  name: "UserRegister",

  data() {
    return {
      member:{
        email:'',
        name:'',
        nickname:'',
        password:'',
      },
      emailDuplicated : null,
    }
  },
  methods:{
    async checkEmail(){
      await axios.post(`http://localhost:9999/member/checkEmail`,{
        email:this.member.email
      })
      .then(({data})=>{
        this.emailDuplicated = data;
        console.log(data);
      })
      .catch(()=>{
        alert("이메일 체크 오류");
      })
    
    },
    async register(){
      if(this.emailDuplicated){
      await axios.post(`http://localhost:9999/member/register`,{
        email:this.member.email,
        name:this.member.name,
        password:this.member.password,
        nickname:this.member.nickname,
      })
      .then(()=>{
        this.$router.push(`/`);
          })}
    }
  },
};
</script>

<style></style>