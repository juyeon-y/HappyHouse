<template>
    <b-container class="bv-example-row mt-3 text-center" id="container">
        <house-search-bar id="search-bar"></house-search-bar>
        <v-row>
            <v-col cols="4" align="left">
                <router-view></router-view>
            </v-col>
            <v-col cols="8">
                <house-map id="map"></house-map>
            </v-col>
        </v-row>
    </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseMap from "@/components/house/HouseMap.vue";

export default {
    name: "AppHouse",
    components: {
        HouseSearchBar,
        HouseMap,
    },
};
</script>
<style scoped>
* {
    padding: 2px !important;
    margin: 0 !important;
}

.underline-orange {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}

#search-bar {
    position: absolute;
    z-index: 2;
    top: 100px;
    left: 37%;
    height: 0px;
    width: 800px;
    opacity: 90%;
}

#map {
    width: 78vw;
    height: 85vh;
}
</style>
