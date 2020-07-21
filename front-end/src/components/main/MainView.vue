<template>
  <v-ons-page>
    <top-tool-bar></top-tool-bar>
    <v-ons-tabbar position="auto" v-on:postchange="tabbarChange"
      :tabs="tabs"
      :visible="true"
      :index.sync="activeIndex"
    >
    </v-ons-tabbar>
  </v-ons-page>
</template>

<script>
import ServiceView from './service_tab/ServiceView.vue'
import MypageView from './mypage_tab/MypageView.vue'
import SettingView from './setting_tab/SettingView.vue'
import TopToolBar from '../common/TopToolBar.vue';
let listCount = 0;
import { rentListReq } from '../../api/index'

export default {
  data: function() {
    return {
      activeIndex: 0,
      get id() {
        return window.localStorage.getItem("id");
      },
      tabs: [
        {
          icon: 'fa-link',
          label: '서비스',
          page: ServiceView,
          key: "ServiceView",
        },        
        {
          icon: 'fa-user',
          label: '마이페이지',
          badge: 3,
          page: MypageView,
          key: "myPageView"
        },
        {
          icon: 'fa-cog',
          label: '설정',
          page: SettingView,
          key: "settingView"
        },
      ],

    };
  },
  methods : {
    tabbarChange: function($event) {
      this.$store.commit('pageIndexChange', this.activeIndex);
      if(this.activeIndex === 1) {
        rentListReq(this.id)
          .then((res) => {
          this.$store.commit("rentItemListUpdate", res);
          this.loading = false;
        })
      }
    }
  },
  beforeCreate() {
    if(window.sessionStorage.getItem("tablet") !== null) {
      listCount += 1;
    }
    if(window.sessionStorage.getItem("pen") !== null) {
      listCount += 1;
    }
    if(window.sessionStorage.getItem("keyboard") !== null) {
      listCount += 1;
    }
  },
  components: {
    ServiceView,
    SettingView,
    MypageView,
    TopToolBar,
  }
}
</script>

<style>

</style>