<template>
    <div>
        <v-card>
            <v-toolbar>
                <v-btn icon class="hidden-xs-only" @click="goBack">
                    <v-icon>mdi-arrow-left</v-icon>
                </v-btn>
                <v-toolbar-title class="font-weight-bold">키워드로 검색하기</v-toolbar-title>
            </v-toolbar>
            <v-card class="overflow-y-auto" id="list-card">
                <v-container>
                    <v-spacer></v-spacer>
                    <b-col>키워드 검색 결과 리스트</b-col>
                </v-container>
            </v-card>
        </v-card>
    </div>
</template>

<script>
export default {
    name: "HouseKeySearch",
    methods: {
        goBack() {
            this.$router.go(-1);
        },
    },
};
</script>

<style scoped>
#list-card {
    height: 78vh;
}
</style>
