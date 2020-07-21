import Vue from 'vue'
import Vuex from 'vuex'

import ServiceViewMainPage from './components/main/service_tab/main_page/ServiceViewMainPage.vue';
import MyPageViewMainPage from './components/main/mypage_tab/MyPageViewMainPage.vue';
import SettingViewMainPage from './components/main/setting_tab/SettingMainPage.vue';



Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    pageStack: [ServiceViewMainPage],
    myPageStack: [MyPageViewMainPage],
    settingPageStack: [SettingViewMainPage],
    kakaoMap: null,
    tabbarIdx: 0,
    items: [
      {
        idx: 1,
        korName: '아이패드 프로 11"',
        engName: "ipad_pro_11",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 800,
        priceStr: "800원 / 시간"
      },
      {
        idx: 2,
        korName: '아이패드 프로 12.9"',
        engName: "ipad_pro_12",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 700,
        priceStr: "700원 / 시간"
      },
      {
        idx: 3,
        korName: "아이패드 에어",
        engName: "ipad_air",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 600,
        priceStr: "600원 / 시간"
      },
      {
        idx: 4,
        korName: "아이패드 7세대",
        engName: "ipad_air",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 500,
        priceStr: "500원 / 시간"
      },
      {
        idx: 5,
        korName: "갤럭시탭 S6",
        engName: "galaxy_tab_s6",
        maker: "samsung",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 600,
        priceStr: "600원 / 시간"
      },
      {
        idx: 6,
        korName: "갤럭시탭 S6 Lite",
        engName: "galaxy_tab_s6",
        maker: "samsung",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 500,
        priceStr: "500원 / 시간"
      }
    ],
    selectedItemIdx: null,
    rentItemList: [],
  },
  mutations: {
    setKakaoMap: function(state, payload) {
      state.kakaoMap = payload;
    },
    pageStackPush: function(state, payload) {
      state.pageStack.push(payload.page);
      state.selectedItemIdx = payload.selectedItemIdx;
    },
    pageStackPop(state) {
      if (state.pageStack.length > 1) {
        state.pageStack.pop();
      }
      // var currentPageName = state.pageStack[state.pageStack.length - 1].name;
      if (state.pageStack.length < 2) {
        state.selectedItemIdx = null;
      }
    },
    myPageStackPush: function(state, payload) {
      state.myPageStack.push(payload.page);
      state.selectedItemIdx = payload.selectedItemIdx;
    },
    myPageStackPop(state) {
      if (state.myPageStack.length > 1) {
        state.myPageStack.pop();
      }
      // var currentPageName = state.myPageStack[state.pageStack.length - 1].name;
      if (state.myPageStack.length < 2) {
        state.selectedItemIdx = null;
      }
    },
    settingPageStackPush: function(state, payload) {
      state.settingPageStack.push(payload.page);
      state.selectedItemIdx = payload.selectedItemIdx;
    },
    settingPageStackPop(state) {
      if (state.settingPageStack.length > 1) {
        state.settingPageStack.pop();
      }
      // var currentPageName = state.settingPageStack[state.pageStack.length - 1].name;
      if (state.settingPageStack.length < 2) {
        state.selectedItemIdx = null;
      }
    },
    pageIndexChange(state, payload) {
      state.tabbarIdx = payload;
    },
    goToMain(state) {
      switch(state.tabbarIdx) {
        case 0:
          while(state.pageStack.length > 1) {
            this.pageStackPop();
          }
          break;
        case 1:
          while(state.myPageStack.length > 1) {
            this.myPageStackPop();
          }
          break;
        case 2:
          while(state.settingPageStack.length > 1) {
            this.settingPageStackPop();
          }
          break;
       }
    },
    rentItemListUpdate(state, payload) {
      state.rentItemList = payload;
    }
  },
  getters: {
    selectedItem: function(state) {
      return state.items[state.selectedItemIdx];
    }
  }
});
