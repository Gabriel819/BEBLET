<template>
    <div ref="map"></div>
</template>

<script>
/* global kakao */

export default {
    name: 'KakaoMap',
    props: {
        appkey: {
            type: String,
            required: true
        },
        width: {
            type: Number,
            default: 100
        },
        height: {
            type: Number,
            default: 100
        },
        center: {
            type: Array,
            required: true
        },
        level: {
            type: Number
        },
        isShowing: {
            type: Boolean
        },
        pinUpdated: {
            type: Boolean
        },
        availableCabinets: {
            type: Array
        },
        state: {
          type: Boolean,
        }
    },

    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement('script');
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a04f05666032a9ee976b653738fe4b63';
            document.head.appendChild(script);
        }
    },

    data() {
      return {
        map: null,
        mapCenter: this.center,
        cabinetSelected: [false, false],
        cabinetLatLng: [
          {},
          {
            cabinet_IDX: 1,
            korName: "1호관",
            cabinet_NAME: "1st",
            latitude: 37.449366,
            longitude: 126.654386
          },
          {
            cabinet_IDX: 2,
            korName: "2호관",
            cabinet_NAME: "2nd",
            latitude: 37.450340,
            longitude: 126.655694
          },
          {
            cabinet_IDX: 3,
            korName: "60주년 기념관",
            cabinet_NAME: "60th Anniversary",
            latitude: 37.450920,
            longitude: 126.654242
          },
          {
            cabinet_IDX: 4,
            korName: "4호관",
            cabinet_NAME: "4th",
            latitude: 37.450390,
            longitude: 126.655207
          },
          {
            cabinet_IDX: 5,
            korName: "5호관",
            cabinet_NAME: "5th",
            latitude: 37.451213,
            longitude: 126.653141
          },
          {
            cabinet_IDX: 6,
            korName: "6호관",
            cabinet_NAME: "6th",
            latitude: 37.447740,
            longitude: 126.655893
          },
          {
            cabinet_IDX: 7,
            korName: "비룡플라자",
            cabinet_NAME: "Bilyong",
            latitude: 37.449674,
            longitude: 126.656570
          },
          {
            cabinet_IDX: 8,
            korName: "9호관",
            cabinet_NAME: "9th",
            latitude: 37.447510,
            longitude: 126.655400
          },
          {
            cabinet_IDX: 9,
            korName: "하이테크",
            cabinet_NAME: "HITECH",
            latitude: 37.450620,
            longitude: 126.657353
          },
          {
            cabinet_IDX: 10,
            korName: "정석학술정보관",
            cabinet_NAME: "JeongSuk",
            latitude: 37.449461,
            longitude: 126.652536
          },
          {
            cabinet_IDX: 11,
            korName: "서호관",
            cabinet_NAME: "Seoho",
            latitude: 37.451284,
            longitude: 126.651431
          },
          {
            cabinet_IDX: 12,
            korName: "법학전문대학원",
            cabinet_NAME: "LawSchool",
            latitude: 37.448397,
            longitude: 126.651914
          },
        ],
        confirmVisibility: false,
        markers: [],
      };
    },
    watch: {
        isShowing(val) {
            if (val) {
              this.cabinetSelected[0] = false;
              this.cabinetSelected[1] = false;
              this.relayout();
            }
        },
        width(val) {
            this.$refs.map.style.width = val + '%';
            this.relayout();
        },
        height(val) {
            this.$refs.map.style.height = val + '%';
            this.relayout();
        },
        state(val) {
          if(!val) { // 대여
            this.createStartMarkers();
            this.$emit("pinUpdateComplete");
          } else if(val) {
            this.createEndMarkers();
            this.$emit("pinUpdateComplete");
          }
        },
        // async pinUpdated(val) {
        //   if(val) {
        //     await this.updateMarkers();
        //     this.$emit("pinUpdateComplete");
        //   }
        // }
    },

    methods: {
        initMap() {
          const container = this.$refs.map;
          container.style.width = this.width + '%';
          container.style.height = this.height + '%';

         const options = {
           center: new kakao.maps.LatLng(...this.mapCenter)
         };
         const map = new kakao.maps.Map(container, options);
          map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
          kakao.maps.event.addListener(map, 'center_changed', this.onCenterChanged);

          this.map = map;
          this.relayout();
        },
        relayout() {
            kakao.maps.event.removeListener(this.map, 'center_changed', this.onCenterChanged);
            this.map.relayout();
            const latlng = new kakao.maps.LatLng(...this.mapCenter);
            this.map.setCenter(latlng);
            kakao.maps.event.addListener(this.map, 'center_changed', this.onCenterChanged);
        },
        onCenterChanged() {
            const center = this.map.getCenter();
            this.mapCenter = [center.getLat(), center.getLng()];
        },
        createStartMarkers() {
          this.resetMarkers();
          var imageSrc = './src/assets/images/icons/star_pin.png', // 마커이미지의 주소입니다    
          imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
          imageOption = {offset: new kakao.maps.Point(32, 57)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          console.log(this.availableCabinets.length);

          for(let i = 0; i < this.availableCabinets.length - 1; i++) {
            var markerPosition = new kakao.maps.LatLng(this.availableCabinets[i].latitude, this.availableCabinets[i].longitude);
            if(i < this.availableCabinets.length - 2 && this.availableCabinets[i].cabinet_IDX !== this.availableCabinets[this.availableCabinets.length - 2].cabinet_IDX){
              var marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true,
              });
              marker.setMap(this.map);

              var selectedCabinent = this.cabinetLatLng[i];
              var thisComponent = this;
              kakao.maps.event.addListener(marker, 'click', function() {
                // console.log(thisComponent.confirmVisibility);
              if(!thisComponent.cabinetSelected[0] && !thisComponent.cabinetSelected[1]) {
                thisComponent.$emit("cabinetSelected", [thisComponent.availableCabinets[i], 0]);  // 대여 위치
                  thisComponent.cabinetSelected[0] = true;
                } else if(thisComponent.cabinetSelected[0] && !thisComponent.cabinetSelected[1]) {
                  thisComponent.$emit("cabinetSelected", [thisComponent.availableCabinets[i], 1]);  // 반납 위치
                  thisComponent.cabinetSelected[1] = true;
                }
              });
              this.markers.push(marker);
            } 
            else if(i < this.availableCabinets.length - 2 && this.availableCabinets[i].cabinet_IDX === this.availableCabinets[this.availableCabinets.length - 2].cabinet_IDX) {
              console.log("별" + i + "\n");
              var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
              var marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true,
                image: markerImage
              });
              marker.setMap(this.map);

              var selectedCabinent = this.cabinetLatLng[i];
              var thisComponent = this;
              kakao.maps.event.addListener(marker, 'click', function() {
                // console.log(thisComponent.confirmVisibility);
                if(!thisComponent.cabinetSelected[0] && !thisComponent.cabinetSelected[1]) {
                  thisComponent.$emit("cabinetSelected", [thisComponent.availableCabinets[i], 0]);  // 대여 위치
                  thisComponent.cabinetSelected[0] = true;
                } else if(thisComponent.cabinetSelected[0] && !thisComponent.cabinetSelected[1]) {
                  thisComponent.$emit("cabinetSelected", [thisComponent.availableCabinets[i], 1]);  // 반납 위치
                  thisComponent.cabinetSelected[1] = true;
                }
              });
              this.markers.push(marker);
            }
          }
          this.relayout();
        },
        createEndMarkers() {
          this.resetMarkers();
           var imageSrc = './src/assets/images/icons/star_pin.png', // 마커이미지의 주소입니다    
          imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
          imageOption = {offset: new kakao.maps.Point(32, 57)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          console.log(this.availableCabinets.length);

          for(let i = 1; i < this.cabinetLatLng.length; i++) {

            var markerPosition = new kakao.maps.LatLng(this.cabinetLatLng[i].latitude, this.cabinetLatLng[i].longitude);
            console.log("별" + i + "\n");
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

            if (this.cabinetLatLng[i].cabinet_IDX === this.availableCabinets[this.availableCabinets.length - 2].cabinet_IDX) {
              var marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true,
                image: markerImage
              });
            }
            else {
              var marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true
              })
            }

            marker.setMap(this.map);

            var selectedCabinent = this.cabinetLatLng[i];
            var thisComponent = this;
            kakao.maps.event.addListener(marker, 'click', function() {
              // console.log(thisComponent.confirmVisibility);
              if(!thisComponent.cabinetSelected[0] && !thisComponent.cabinetSelected[1]) {
                thisComponent.$emit("cabinetSelected", [thisComponent.cabinetLatLng[i], 0]);  // 대여 위치
                thisComponent.cabinetSelected[0] = true;
              } else if(thisComponent.cabinetSelected[0] && !thisComponent.cabinetSelected[1]) {
                thisComponent.$emit("cabinetSelected", [thisComponent.cabinetLatLng[i], 1]);  // 반납 위치
                thisComponent.cabinetSelected[1] = true;
              }
            });
            this.markers.push(marker);
          }
          this.relayout();
        },
        resetMarkers(map) {
          for (var i = 0; i < this.markers.length; i++) {
            this.markers[i].setMap(null);
          }            
      }
    }
}
</script>