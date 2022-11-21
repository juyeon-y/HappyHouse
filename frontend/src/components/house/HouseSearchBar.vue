<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col>
      <v-select v-model="sidoCode" :items="sidos" @change="gugunList" dense solo></v-select>
    </b-col>
    <b-col>
      <v-select v-model="gugunCode" :items="guguns" @change="dongList" dense solo></v-select>
    </b-col>
    <b-col>
      <v-select v-model="dongCode" :items="dongs" @change="yearList" dense solo></v-select>
    </b-col>
    <b-col>
      <v-select v-model="dealYear" :items="years" @change="monthList" dense solo></v-select>
    </b-col>
    <b-col>
      <v-select v-model="dealMonth" :items="months" @change="searchApt" dense solo></v-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      dealYear: null,
      dealMonth: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "years", "months", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getYear", "getMonth", "getHouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_YEAR_LIST",
      "CLEAR_MONTH_LIST",
      "CLEAR_APT_LIST",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      // console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    yearList() {
      this.CLEAR_YEAR_LIST();
      this.dealYear = null;
      if (this.dongCode) this.getYear(this.dongCode);
    },
    monthList() {
      this.CLEAR_MONTH_LIST();
      this.dealMonth = null;
      if (this.dealYear) this.getMonth([this.dongCode, this.dealYear]);
    },
    searchApt() {
      if (this.dealMonth) this.getHouseList([this.dongCode, this.dealYear, this.dealMonth]);
    },
  },
};
</script>

<style scoped></style>
