<template>
  <div>
    <v-card>
      <v-toolbar>
        <v-icon large color="blue darken-2" id="home-icon"> mdi-home-search </v-icon>
        <v-toolbar-title class="font-weight-bold">매물검색</v-toolbar-title>
      </v-toolbar>
      <v-card class="overflow-y-auto" id="list-card">
        <v-container v-if="houses && houses.length != 0" class="bv-example-row mt-3 overflow-auto">
          <v-card-title class="font-weight-bold">주변장소</v-card-title>
          <v-card-text>
            <v-chip-group v-model="category" active-class="deep-purple accent-4 white--text" column>
              <v-chip value="MT1">대형마트</v-chip>
              <v-chip value="CS2">편의점</v-chip>
              <v-chip value="PS3">어린이집 / 유치원</v-chip>
              <v-chip value="SC4">학교</v-chip>
              <v-chip value="AC5">학원</v-chip>
              <v-chip value="PK6">주차장</v-chip>
              <v-chip value="OL7">주유소 / 충전소</v-chip>
              <v-chip value="SW8">지하철역</v-chip>
              <v-chip value="BK9">은행</v-chip>
              <v-chip value="CT1">문화시설</v-chip>
              <v-chip value="AG2">중개업소</v-chip>
              <v-chip value="PO3">공공기관</v-chip>
              <v-chip value="FD6">음식점</v-chip>
              <v-chip value="CE7">까페</v-chip>
              <v-chip value="HP8">병원</v-chip>
              <v-chip value="PM9">약국</v-chip>
            </v-chip-group>
          </v-card-text>
          <v-divider class="mx-4"></v-divider>
          <house-list-item v-for="(house, index) in houses" :key="index" :house="house" />
        </v-container>
        <v-container v-else>
          <v-spacer></v-spacer>
          <b-col>지역을 선택해주세요</b-col>
        </v-container>
      </v-card>
    </v-card>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      category:"",
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "currCategory"]),
  },
  methods: {
    ...mapMutations(houseStore, ["UPDATE_CURR_CATEGORY"]),
  },
  watch:{
    category: function(){
      this.UPDATE_CURR_CATEGORY(this.category);
    }
  }
};
</script>

<style scoped>
#list-card {
  height: 78vh;
}
#home-icon {
  margin-right: 20px;
}
</style>
