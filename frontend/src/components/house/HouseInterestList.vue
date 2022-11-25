<template>
  <div>
    <v-card>
      <v-toolbar>
        <v-btn icon class="hidden-xs-only" @click="goBack">
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <v-toolbar-title class="font-weight-bold">관심매물 보기</v-toolbar-title>
      </v-toolbar>
      <v-card class="overflow-y-auto" id="list-card">
        <v-container
          v-if="interests && interests.length != 0"
          class="bv-example-row mt-3 overflow-auto"
        >
          <house-list-item v-for="(house, index) in interests" :key="index" :house="house" />
        </v-container>
      </v-card>
    </v-card>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseInterestList",
  components: {
    HouseListItem,
  },
  methods: {
    ...mapActions(houseStore, ["getInterestList"]),
    ...mapMutations(houseStore, ["UPDATE_RESET_MAP_CHK"]),
    goBack() {
      this.$router.go(-1);
      this.UPDATE_RESET_MAP_CHK(true);
    },
  },
  mounted() {
    this.UPDATE_RESET_MAP_CHK(false);
  },
  computed: {
    ...mapState(houseStore, ["interests"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.getInterestList(this.userInfo.id);
  },
};
</script>

<style scoped>
#list-card {
  height: 78vh;
}
</style>
