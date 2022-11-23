<template>
    <div id="main-root"></div>
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
#main-root {
    height: 100%;
    background-image: url("@/assets/main_back.jpg");
    background-size: cover;
}
</style>
