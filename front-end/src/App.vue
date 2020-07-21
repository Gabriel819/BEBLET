<template>
  <div class="app">
    <transition name="mode-change" mode="out-in">
      <user-authentication-view v-if="viewMode === 0" v-on:login-success="loginSuccess" v-on:next="next"></user-authentication-view>
      <main-view v-if="viewMode === 2"></main-view>
      <!-- <test-module v-if="viewMode === -1"></test-module> -->
    </transition>
  </div>
</template>

<script>
import MainView from './components/main/MainView.vue';
import UserAuthenticationView from './components/user_auth/UserAuthenticView.vue';
// import TestModule from './components/TestModule.vue'


export default {
  name: 'app',
  components: {
    MainView,
    UserAuthenticationView,
    // TestModule
  },
  data: function() {
    return {
      viewMode: -1,

			get id() {
				return window.localStorage.getItem("id");
			},
			get pubKey() {
				return window.localStorage.getItem("pubKey");
			},
			get nickName() {
				return window.localStorage.getItem("nickname");
			},
    }
  },
  methods: {
    changeMode: function(targetAppMode) {
			clearTimeout(this.loadingTimeout);
			this.viewMode = targetAppMode - 1;
			this.loadingTimeout = setTimeout(() => (this.viewMode += 1), 700);
    },
    loginSuccess: function() {
			if (this.id === null) {
				return;
			} else {
				this.changeMode(2);
			}
    },
    next: function() {
      this.changeMode(2);
    }
  },
  created() {
    if (this.id === null) {
			this.changeMode(0);
    } 
    else if (this.id !== null) {
			this.changeMode(2);
		}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
@import url('https://fonts.googleapis.com/css2?family=Gothic+A1:wght@800&family=Noto+Sans+KR&family=Ubuntu:wght@700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');


.center {
  text-align: center;
}
.app {
  width: 100%;
  height: 100%;
}


.page__background {
  background-color: #ffffff;
}

.tabbar {
  border-top: solid 0.5px rgba(204, 204, 204, 0.5);
}

.tabbar__button {
  background-color: #ffffff;
}

:checked + .tabbar__button {
  background-color: #ffffff;
}

.toolbar {
  background-color: #ffffff;
  font-weight: 700;
  font-family: 'Ubuntu', sans-serif;
}



/*
  date Picker
 */
.datepicker__month-day--first-day-selected, .datepicker__month-day--last-day-selected, .datepicker__month-day--selected {
  background-color: #0076FF;
}
.datepicker__close-button, .-hide-on-desktop {
  color: #0076FF;
}

.datepicker__month-day, .datepicker__month-day--disabled, .datepicker__month-day--first-day-selected {
  color: #0076FF;
}

.datepicker__dummy-wrapper--is-active {
  border: #0076FF;
}

.datepicker__input {
  color: #0076FF;
}

.datepicker__month-day--first-day-selected, .datepicker__month-day--last-day-selected {
  background-color: #0076ff;
}

.datepicker__clear-button {
  margin-top: 9px;
  right: 0.5%;
}

.datepicker__dummy-wrapper {
  border: none;
}

.datepicker__input:first-child {
  background: transparent url(http://localhost:8080/src/assets/images/dateImg/ic-arrow-right-datepicker.regular.svg) no-repeat 100%/8px;
}

@media (-webkit-min-device-pixel-ratio: 2), (min-resolution: 192dpi), (min-resolution: 2dppx){
  .toolbar{
    background-image: linear-gradient(0deg, #b2b2b2, #b2b2b2 0%, transparent 0%);
  }
}

.mode-change-enter-active, .mode-change-leave-active {
  transition: opacity .3s;
}
.mode-change-enter, .mode-change-leave-to {
  opacity: 0;
}
</style>
