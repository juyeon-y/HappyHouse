<template>
    <div id="root">
        <b-navbar toggleable="lg">
            <b-navbar-brand href="#">
                <router-link :to="{ name: 'main' }">
                    <b-img
                        :src="require('@/assets/logo.png')"
                        id="logo"
                        class="d-inline-block align-top"
                        alt="logo"
                    ></b-img>
                </router-link>
            </b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="#">
                        <router-link :to="{ name: 'main' }" class="link"> 메인 </router-link>
                        <router-link :to="{ name: 'board' }" class="link"> 게시판 </router-link>
                        <router-link :to="{ name: 'house' }" class="link"> 아파트정보 </router-link>
                    </b-nav-item>
                </b-navbar-nav>

                <!-- after login -->
                <b-navbar-nav class="ml-auto" v-if="userInfo">
                    <b-nav-item class="align-self-center">
                        <b-avatar
                            variant="primary"
                            v-text="userInfo.name.charAt(0).toUpperCase()"
                        ></b-avatar>
                        {{ userInfo.name }}님 환영합니다.
                    </b-nav-item>
                    <b-nav-item class="align-self-center">
                        <router-link :to="{ name: 'mypage' }" class="link align-self-center"
                            >내정보보기</router-link
                        >
                    </b-nav-item>
                    <b-nav-item class="align-self-center link" @click.prevent="onClickLogout"
                        >로그아웃</b-nav-item
                    >
                </b-navbar-nav>
                <!-- before login -->
                <b-navbar-nav class="ml-auto" v-else>
                    <b-nav-item-dropdown right>
                        <template #button-content>
                            <b-icon icon="people" font-scale="2"></b-icon>
                        </template>
                        <b-dropdown-item href="#">
                            <router-link :to="{ name: 'join' }" class="link">
                                <b-icon icon="person-circle"></b-icon> 회원가입
                            </router-link>
                        </b-dropdown-item>
                        <b-dropdown-item href="#">
                            <router-link :to="{ name: 'login' }" class="link">
                                <b-icon icon="key"></b-icon> 로그인
                            </router-link>
                        </b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
    name: "TheHeaderNavbar",
    data() {
        return {};
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        //...mapGetters(["checkUserInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userLogout"]),
        // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
        onClickLogout() {
            // this.SET_IS_LOGIN(false);
            // this.SET_USER_INFO(null);
            // sessionStorage.removeItem("access-token");
            // if (this.$route.path != "/") this.$router.push({ name: "main" });
            console.log(this.userInfo.userid);
            //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
            //+ satate에 isLogin, userInfo 정보 변경)
            // this.$store.dispatch("userLogout", this.userInfo.userid);
            this.userLogout(this.userInfo.userid);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            if (this.$route.path != "/") this.$router.push({ name: "main" });
        },
    },
};
</script>

<style scoped>
#logo {
    margin-left: 20px;
    width: 120px;
    height: 50px;
}

.link {
    text-decoration: none;
    font-family: "Pretendard-Regular";
    color: hsl(200, 15%, 43%);
    font-size: 0.9rem;
    font-weight: 600;
    padding-block: 15px;
    margin-left: 40px;
}

@font-face {
    font-family: "Pretendard-Regular";
    src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff")
        format("woff");
    font-weight: 400;
    font-style: normal;
}
</style>
