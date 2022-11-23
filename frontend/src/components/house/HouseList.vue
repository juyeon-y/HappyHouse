<template>
  <div>
    <v-card>
      <v-toolbar>
        <v-icon large color="blue darken-2" id="home-icon"> mdi-home-search </v-icon>
        <v-toolbar-title>매물검색</v-toolbar-title>
      </v-toolbar>
      <v-card class="overflow-y-auto" max-height="72vh" id="list-card">
        <v-container v-if="houses && houses.length != 0" class="bv-example-row mt-3 overflow-auto">
          <v-card-title>주변장소</v-card-title>
          <v-card-text>
            <v-chip-group v-model="selection" active-class="deep-purple accent-4 white--text" column>
              <v-chip>문화시설</v-chip>
              <v-chip>대형마트</v-chip>
              <v-chip>편의점</v-chip>
              <v-chip>까페</v-chip>
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
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      selection: 1,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {},
};
</script>

<style scoped>
#list-card {
  height: 72vh;
}
#home-icon {
  margin-right: 20px;
}
</style>
