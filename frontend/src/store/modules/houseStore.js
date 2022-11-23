import { sidoList, gugunList, dongList, yearList, monthList, houseList } from "@/api/house.js";

const houseStore = {
    namespaced: true,
    state: {
        sidos: [{ value: null, text: "시도" }],
        guguns: [{ value: null, text: "시군구" }],
        dongs: [{ value: null, text: "읍면동" }],
        years: [{ value: null, text: "년도" }],
        months: [{ value: null, text: "월" }],
        houses: [],
        house: null,
        zoomLevel: 3,
        currCategory: "",
        searchBarChk: true,
        resetMapChk: false,
    },
    getters: {},
    mutations: {
        CLEAR_SIDO_LIST(state) {
            state.sidos = [{ value: null, text: "시도" }];
        },
        CLEAR_GUGUN_LIST(state) {
            state.guguns = [{ value: null, text: "시군구" }];
        },
        CLEAR_DONG_LIST(state) {
            state.dongs = [{ value: null, text: "읍면동" }];
        },
        CLEAR_YEAR_LIST(state) {
            state.years = [{ value: null, text: "년도" }];
        },
        CLEAR_MONTH_LIST(state) {
            state.months = [{ value: null, text: "월" }];
        },
        CLEAR_APT_LIST(state) {
            state.houses = [];
            state.house = null;
        },
        SET_SIDO_LIST(state, sidos) {
            sidos.forEach((sido) => {
                state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
            });
        },
        SET_GUGUN_LIST(state, guguns) {
            guguns.forEach((gugun) => {
                state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
            });
        },
        SET_DONG_LIST(state, dongs) {
            dongs.forEach((dong) => {
                state.dongs.push({ value: dong.dongCode, text: dong.dongName });
            });
        },
        SET_YEAR_LIST(state, years) {
            years.forEach((year) => {
                state.years.push({ value: year, text: year });
            });
        },
        SET_MONTH_LIST(state, months) {
            months.forEach((month) => {
                state.months.push({ value: month, text: month });
            });
        },
        SET_HOUSE_LIST(state, houses) {
            state.houses = houses;
        },
        SET_DETAIL_HOUSE(state, house) {
            state.house = house;
        },
        UPDATE_ZOOM_LEVEL(state, level) {
            state.zoomLevel = level;
        },
        UPDATE_CURR_CATEGORY(state, currCategory) {
            state.currCategory = currCategory;
        },
        UPDATE_SEARCHBAR_CHK(state, searchBarChk) {
            state.searchBarChk = searchBarChk;
        },
        UPDATE_RESET_MAP_CHK(state, resetMapChk) {
            state.resetMapChk = resetMapChk;
        },
    },
    actions: {
        getSido: ({ commit }) => {
            sidoList(
                ({ data }) => {
                    commit("SET_SIDO_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getGugun: ({ commit }, sidoCode) => {
            const params = { sido: sidoCode };
            gugunList(
                params,
                ({ data }) => {
                    commit("SET_GUGUN_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getDong: ({ commit }, gugunCode) => {
            const params = { gugun: gugunCode };
            dongList(
                params,
                ({ data }) => {
                    commit("SET_DONG_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getYear: ({ commit }, dongCode) => {
            const params = { dong: dongCode };
            yearList(
                params,
                ({ data }) => {
                    commit("SET_YEAR_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getMonth: ({ commit }, param) => {
            const params = {
                dong: param[0],
                year: param[1],
            };
            monthList(
                params,
                ({ data }) => {
                    commit("SET_MONTH_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getHouseList: ({ commit }, param) => {
            const params = {
                dong: param[0],
                year: param[1],
                month: param[2],
            };
            houseList(
                params,
                ({ data }) => {
                    console.log(data);
                    commit("SET_HOUSE_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        detailHouse: ({ commit }, house) => {
            commit("SET_DETAIL_HOUSE", house);
        },
    },
};

export default houseStore;
