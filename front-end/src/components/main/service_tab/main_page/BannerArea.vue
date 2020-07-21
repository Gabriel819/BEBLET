<template>
  <div class="main_banner">
    <div class="banner_text">
      {{ nickName }}님,<br>
      환영합니다!
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      get nickName() {
				return window.localStorage.getItem("nickname");
			},
    }
  },
}
</script>

<style>

.banner_text {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 25px;
  position: absolute;
  left: 5%;
  top: 20%;
}
</style>