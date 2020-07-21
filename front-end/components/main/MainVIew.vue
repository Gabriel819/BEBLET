<template>
  <v-ons-tabbar position="auto"
    :tabs="tabs"
    :visible="true"
    :index.sync="activeIndex"
  >
  </v-ons-tabbar>
</template>

<script>
import ServiceView1 from './service1_tab/ServiceView1.vue'
import ServiceView2 from './service2_tab/ServiceView2.vue'
import MypageView from './mypage_tab/MypageView.vue'
import SettingView from './setting_tab/SettingView.vue'

export default {
  data: function() {
    return {
      activeIndex: 0,
      tabs: [
        {
          icon: 'fa-book',
          label: '서비스 1',
          page: ServiceView1,
          key: "ServiceView1"
        },
        {
          icon: 'fa-link',
          label: '서비스 2',
          page: ServiceView2,
          key: "ServiceView2"
        },
        {
          icon: 'fa-user',
          label: '마이페이지',
          badge: 7,
          page: MypageView,
          key: "myPageView"
        },
        {
          icon: 'fa-cog',
          label: '설정',
          page: SettingView,
          key: "settingView"
        }
      ]
    }
  },
  components: {
    ServiceView1,
    ServiceView2,
    SettingView,
    MypageView
  }
}
</script>

<style>

</style>