<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
      ps: null,
      placeMarkers: [],
      placeOverlay: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "zoomLevel", "currCategory"]),
  },
  watch: {
    houses: function () {
      this.setMarkers();
    },
    house: function () {
      if (this.zoomLevel == 1) this.setMapPosition();
      else this.UPDATE_ZOOM_LEVEL(1);
    },
    zoomLevel: function () {
      this.map.setLevel(this.zoomLevel);
      this.setMapPosition();
    },
    currCategory: function () {
      this.placeOverlay.setMap(null);
      this.removeMarker();
      if (this.currCategory) {
        //place마커들 저장하고, 그리기
        this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
          useMapBounds: true,
        });
      }
    },
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    ...mapMutations(houseStore, ["UPDATE_ZOOM_LEVEL"]),
    initMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      this.ps = new kakao.maps.services.Places(this.map); // 장소 검색 객체를 생성합니다
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
    },
    setClickEvent(marker, house) {
      kakao.maps.event.addListener(marker, "click", () => {
        this.detailHouse(house);
        if (this.$route.path !== "/house/detail") this.$router.push({ name: "housedetail" });
      });
    },
    setMarkers() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => {
          marker.setMap(null);
        });
      }
      let imageSrc = require("@/assets/home_marker.png"), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(30, 30), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      this.houses.forEach((house) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(house.lat, house.lng),
          title: house.aptName,
          image: markerImage, // 마커이미지 설정
        });
        this.setClickEvent(marker, house);
        this.markers.push(marker);
      });
      const bounds = this.houses.reduce(
        (bounds, house) => bounds.extend(new kakao.maps.LatLng(house.lat, house.lng)),
        new kakao.maps.LatLngBounds()
      );
      this.map.setBounds(bounds);
    },
    setMapPosition() {
      const moveLatLng = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      this.map.panTo(moveLatLng);
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }
      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);
      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, { useMapBounds: true });
    },
    displayPlaces(places) {
      for (let i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        let marker = this.addPlaceMarker(new kakao.maps.LatLng(places[i].y, places[i].x));
        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", () => {
          this.displayPlaceInfo(places[i]);
        });
      }
    },
    addPlaceMarker(position) {
      let imageSrc = require("@/assets/place_marker.png"), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      let marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage, // 마커이미지 설정
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.placeMarkers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeMarker() {
      for (var i = 0; i < this.placeMarkers.length; i++) {
        this.placeMarkers[i].setMap(null);
      }
      this.placeMarkers = [];
    },
    displayPlaceInfo(place) {
      let contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = "placeinfo_wrap";
      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(contentNode);
      let content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";
      if (place.road_address_name) {
        content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + "</span>";
      } else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
      }

      content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';
      contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(+place.y + 0.00016, +place.x - 0.0004));
      this.placeOverlay.setMap(this.map);
    },
  },
  mounted() {
    //로딩되어있지 않을 때만 로딩.
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&autoload=false&libraries=services`;
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

<style>
#map {
  width: 100%;
  height: 100%;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: 0px;
  width: 130px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 2px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 11px !important;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 5px;
  color: white;
  background: #d95050;
  background: #e82313 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 5px
    center;
}
.placeinfo .tel {
  font-size: 1px;
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 1px !important;
  margin-top: 0;
}
</style>
