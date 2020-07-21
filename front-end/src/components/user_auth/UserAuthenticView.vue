<template>
  <div class="user_auth_view">
    <div class="banner_container">
      <div class="banner" v-on:click="next">
        BeBlet
      </div>
    </div>
    <div class="banner_desc_container">
      <div class="banner_desc" v-on:click="testWeb">
        태블릿 공유 플랫폼 >
      </div>  
    </div>
    <div class="kakao_login_btn_container">
      <div class="kakao_login_btn" v-on:click="kakaoLoginBtnAction">
        <img v-bind:src="'./src/assets/kakao_log.png'" class="kakao_logo" alt="">
        카카오로 로그인
      </div>
    </div>
    <div class="login_other_btn_container">
      <div class="login_other_btn" v-on:click="unlinkKakao">
        다른 계정으로 로그인
      </div>
    </div>
  </div>
</template>

<script>
import { sendAuthInfo } from '../../api/index';
import { kakaoLogin } from '../../api/index';

var authRes = {};

export default {
  data: function () {
    return {
    }
  },
  methods: {
    testWeb:function() {
      var ref = cordova.InAppBrowser.open('http://apache.org', '_blank', 'location=yes');
    },
		unlinkKakao: function(res) {
			let success = function() {
				window.alert(
					"id : " +
						JSON.stringify(res) +
						"님의 카카오톡과 어플리케이션 연결이 끊어졌습니다.",
				);
			};
			let failed = function(res) {
				window.alert(
					"카카오톡 연결 해제 실패\n에러코드 : " + JSON.stringify(res),
				);
			};
			KakaoCordovaSDK.unlinkApp(success, failed);
		},
		kakaoLoginBtnAction: function() {
      kakaoLogin();
		},
    next: function(){
      this.$emit("next");
    }
  }
}
</script>

<style>
.user_auth_view {
  background: linear-gradient(to top, #f2fcfe, #1c92d2); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  width: 100%;
  height: 100%;
}

.banner_container {
  position: absolute;
  width: 100%;
  text-align: center;
  top: 20%;
}

.banner_desc_container {
  position: absolute;
  width: 100%;
  text-align: center;
  top: 30%;
}

.banner_desc {
  color: white;
  display: inline-block;
  border: white solid 2px;
  border-radius: 20px;
  padding: 10px;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15px;
}

.kakao_logo {
  position: absolute;
  left: 15%;
  top : 8px;
}

.banner {
  color: white;
  font-size: 50px;
  font-weight: 900;
  font-family: 'Ubuntu', sans-serif;
  display: inline-block;
}

.kakao_login_btn_container {
  text-align: center;
  position: absolute;
  width: 100%;
  bottom: 230px;;
}
.kakao_login_btn {
  display: inline-block;
  width: 75%;
  height: 45px;
  line-height: 45px;
  background-color: #ffe500;
  font-family: 'Gothic A1', sans-serif;
  text-align: center;
  border-radius: 5px;
  color: black;
}
.login_other_btn_container {
  text-align: center;
  position: absolute;
  width: 100%;
  bottom: 175px;
  vertical-align: center;
}
.login_other_btn {
  display: inline-block;
  width: 75%;
  height: 45px;
  line-height: 45px;
  color: white;
  font-family: 'Gothic A1', sans-serif 10px;
  background: #1c92f5; /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  border-radius: 5px;
}
.btn_text {
  vertical-align: center;

}
</style>