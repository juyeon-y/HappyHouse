<template>
    <v-card class="mx-auto" height="85vh" width="400">
        <v-navigation-drawer
            absolute
            dark
            src="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
            width="100%"
            permanent
        >
            <v-list>
                <v-list-item
                    v-for="([icon, text, link], i) in items"
                    :key="i"
                    link
                    @click="$router.push({ path: link })"
                >
                    <v-list-item-icon>
                        <v-icon>{{ icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title>{{ text }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
    </v-card>
</template>

<script>
export default {
    name: "HouseMenu",
    data() {
        return {
            items: [
                ["mdi-map-search-outline", "매물 검색하기", "/house/list"],
                //["mdi-text-search-variant", "키워드로 검색하기", "/house/keysearch"],
                ["mdi-star-plus", "관심매물 보기", "/house/interestlist"],
            ],
        };
    },
};
</script>

<style></style>
