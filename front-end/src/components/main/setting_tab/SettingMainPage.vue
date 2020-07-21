<template id="page1">
  <v-ons-page>
    <v-ons-list>
      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item tappable>
        <i class="fas fa-bullhorn"></i> 공지사항
      </v-ons-list-item>
      <v-ons-list-item tappable>
        <i class="fas fa-headset"></i> 문의하기
      </v-ons-list-item>
      <v-ons-list-item tappable>
        <i class="fas fa-won-sign"></i> 결제내역
      </v-ons-list-item>

      <v-ons-list-item tappable v-on:click="showStamp">
        <i class="fas fa-won-sign"></i> 스탬프
      </v-ons-list-item>

      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item modifier="chevron" tappable>앱 설정</v-ons-list-item>


      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item tappable v-on:click="logout" title="로그아웃">
        <i class="fas fa-sign-out-alt"></i> 로그아웃
      </v-ons-list-item>

    </v-ons-list>
  </v-ons-page>
</template>

<script>
import {getUserInfo} from '../../../api/index'

import DetailSettingPage from './DetailSettingPage.vue';

export default {
  key: "SettingPage",
  name: "Setting",
  toolBarName: "BeBlet",
  data() {
    return {
      get id() {
        return window.localStorage.getItem('id');
      }
    }
  },
  methods: {
    push: function() {
      this.$emit('push-page', DetailSettingPage);
    },
    logout: function () {
      var options = {
        title: '로그아웃'
      };
      var logoutCallBack = function () {
        window.localStorage.removeItem("id");
        window.localStorage.removeItem("pubKey");
        window.localStorage.removeItem("nickname");
        window.location.reload();
      }

      this.$ons.notification.confirm('정말로 로그아웃 하시겠습니까?', options).then(logoutCallBack);
    },
    showStamp() {
      getUserInfo(this.id).then((res) => {
        console.log(res);
        this.$ons.notification.alert(JSON.stringify(res.data));
      })
    }
  }
}
</script>

<style>
i {
  margin-right: 10px;
}
</style>