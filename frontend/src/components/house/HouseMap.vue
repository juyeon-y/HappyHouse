<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house"]),
  },
  watch: {
    houses: function () {
      this.setMarkers();
    },
    house: function () {
      this.setMapPosition();
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    },
    setMarkers() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => {
          marker.setMap(null);
        });
      }
      this.houses.forEach((house) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(house.lat, house.lng),
          title: house.aptName,
        });
        this.markers.push(marker);
      });
      const bounds = this.houses.reduce(
        (bounds, house) => bounds.extend(new kakao.maps.LatLng(house.lat, house.lng)),
        new kakao.maps.LatLngBounds()
      );
      this.map.setBounds(bounds);
    },
    setMapPosition() {
      this.map.setLevel(1);
      const moveLatLng = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      this.map.panTo(moveLatLng);
    },
  },
  mounted() {
    //로딩되어있지 않을 때만 로딩.
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&autoload=false`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      //이미 로딩되어 있음.
      this.initMap();
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}
</style>
