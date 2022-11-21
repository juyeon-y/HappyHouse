<template>
  <div></div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
    name:"UserOAuthRedirect",
    created(){
        let token = this.$route.query.token;
        let name = this.$route.query.name;
        console.log(token);
        console.log(name);
        this.redirectOAuth({name:name,token:token});
        this.$router.push('/');
        },
    methods:{
        ...mapActions("memberStore",['redirectOAuth']),
    }
}
</script>

<style>

</style>