<template>
  <v-ons-page class="service_view_main_page">
    <banner-area></banner-area>
    <main-header v-bind:areaName="'이용가능 태블릿'"></main-header>
    <popular-area></popular-area>
    <main-header v-bind:areaName="'바로 빌려보세요.'"></main-header>
    <function-area v-on:funcSelected="pushPage"></function-area>
  </v-ons-page>
</template>

<script>
import BannerArea from './BannerArea.vue';
import PopularArea from './PopularArea.vue';
import MainHeader from './MainHeader.vue';
import FunctionArea from './FunctionArea.vue';


export default {
  key: "ServiceViewMainPage",
  name: "BeBlet",
  toolBarName: "BeBlet",
  data: function () {
    return {

    }
  },
  methods: {
    pushPage: function (funcIdx) {
      var payload = {
        page: null,
        pageName: '',
        selectedItemIdx: null,
      };
      switch(funcIdx) {
      case 0: 
        payload.pageName = "태블릿";
        break;
      case 1: 
        payload.pageName = "펜";
        break;
      case 2:
        payload.pageName = "키보드";
        break;
      default:
        break;
      }
      this.$emit("push-page", payload);
    }
  },
  computed: {
    tablets: function () {
      return this.$store.state.tablets;
    }
  },
  components: {
    BannerArea,
    PopularArea,
    MainHeader,
    FunctionArea,
  }
}
</script>

<style>
.service_view_main_page {
  width: 100%;
  height: 100%;
}
.main_banner {
  position: relative;
  height: 25%;
}
.popular_area {
  position: relative;
  height: 30%;
}

.header {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 15px;
  position: relative;
  left: 5%;
  display: inline-block;
}

.function_list {
  height: 25%;
  text-align: center;
}
</style>