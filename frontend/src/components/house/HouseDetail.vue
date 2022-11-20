<template>
  <v-card>
    <v-toolbar>
      <v-btn icon class="hidden-xs-only" @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>{{ house.aptName }}</v-toolbar-title>
    </v-toolbar>
    <v-card class="overflow-y-auto" height="72vh">
      <b-container v-if="house" class="bv-example-container-row">
        <b-row class="mb-2 mt-1">
          <div id="roadview"></div>
        </b-row>
        <v-list-item>
          <v-list-item-title> 아파트 이름 : {{ house.aptName }} </v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title> 법정동 : {{ house.dongName }} </v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title> 층수 : {{ house.floor }}층 </v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title> 면적 : {{ house.area }} 제곱미터 </v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>
            거래금액 : {{ (parseInt(house.dealAmount.replace(",", "")) * 10000) | price }}원
          </v-list-item-title>
        </v-list-item>
      </b-container>
    </v-card>
  </v-card>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    setRoadView() {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new window.kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new window.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      var position = new window.kakao.maps.LatLng(this.house.lat, this.house.lng);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
  mounted() {
    this.setRoadView();
  },
};
</script>

<style>
#roadview {
  width: 100%;
  height: 200px;
}
</style>
