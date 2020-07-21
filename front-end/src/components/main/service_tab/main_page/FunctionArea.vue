<template>
  <div class="function_list">
    <div class="function_item" v-for="func in functionBtn" v-bind:key="func.korName" v-on:click="btn(func)">
      <i v-bind:class="func.class"></i>
      <div class="function_label">{{ func.korName }}</div>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      functionBtn: [
        {
          idx: 0,
          korName: '태블릿',
          pageName: 'tabletPage',
          class: 'fas fa-tablet-alt blue',
        },
        {
          idx: 1,
          korName: '펜',
          pageName: 'penPage',
          class: 'fas fa-pen green',
        },
        {
          idx: 2,
          korName: '키보드',
          pageName: 'keyboardPage',
          class: 'fas fa-keyboard pink'
        }

      ]
    }
  },
  methods: {
    btn: function (func) {
      if(func.idx === 0) {
        this.$emit("funcSelected", func.idx);
      }
      else {
        this.$ons.notification.alert("준비중입니다.");
      }
    }
  }
}
</script>

<style>
.function_item {
  display: inline-block;
  position: relative;
  top: 20%;
  width: 17%;
  height: 55%;
  margin-left: 5%;
  margin-right: 5%;
  text-align: center;
}

.function_item:active i{
  filter: grayscale(80%);
}

.function_item:active .function_label {
  color: blue;
}

.function_item i {
  margin-left: auto;
  margin-right: auto;
  font-size: 3em;
}

.function_label {
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
  margin-top: 3px;
  font-size: 15px;
}

.blue {
  color: blue;
}

.green {
  color: green;
}

.pink {
  color: pink;
}

</style>