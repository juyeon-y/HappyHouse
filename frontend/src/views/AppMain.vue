<template>
  <div id="main-root">
    <v-container id="container">
      <row>
        <h1 align="left" id="h1">
          집 구할 땐,<br />
          언제 어디서나 해피 하우스
        </h1>
      </row>
      <row>
        <div class="d-flex justify-content-start" id="plain-text">
          원하는 집을 찾기 위한 가장 빠른 선택
        </div>
      </row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "AppMain",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
#container {
  position: absolute;
  margin-left: 55%;
  margin-top: 16%;
}
#main-root {
  height: 100%;
  background-image: url("@/assets/main_back.jpg");
  background-size: cover;
}
#h1 {
  color: rgb(91, 172, 248);
  font-family: "GmarketSansMedium";
  font-size: 60px;
}
#plain-text {
  color: white;
  font-family: "GmarketSansMedium";
  font-style: light;
  font-size: 30px;
}
@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>
