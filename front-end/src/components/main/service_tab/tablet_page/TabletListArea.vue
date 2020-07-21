<template>
  <div class="item_area">
    <div class="item_card_container" v-for="item in items" v-bind:key="item.korName">
      <div class="item_card" v-on:click="pushItemPage(item)">
        <div class="item_list_name">
          {{ item.korName }}
        </div>
        <!-- <div class="remaining">
          잔여 수량 : {{ item.remaining[selectedIdx] }}
        </div> -->
        <div class="price">
          대여료 : {{ item.priceStr }}
        </div>
        <div class="thumb">
          <img v-bind:src="'./src/assets/images/tablets/' + item.engName + '.png'" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["selectedIdx"],
  data: function () {
    return {
    }
  },
  methods: {
    pushItemPage: function (item) {
      var payloadObj = {
        page: null,
        pageName: "태블릿 선택",
        selectedItemIdx: item.idx - 1
      };
      this.$emit("push-tablet-page", payloadObj);
    }
  },
  computed: {
    items: function () {
      return this.$store.state.items;
    }
  },

}
</script>

<style>
.item_area {
  overflow-y: auto;
  position: relative;
  top: 10%;
}
.item_card_container {
  height: 175px;
}

.item_card {
  width: 90%;
  height: 160px;
  border: solid 1px rgba(204, 204, 204, 0.7);
  border-radius: 15px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 5px;
  margin-bottom: 5px;
  position: relative;
}

.item_card:active {
  border: solid 3px #0076FF;
}

.item_card *{
  display: inline-block;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 15px;
}

.item_list_name {
  position: absolute;
  left: 2%;
  top: 18%;
  font-weight: bold;
  font-size: 23px;
}

.remaining {
  position: absolute;
  left: 2%;
  top: 40%;
}

.price {
  position: absolute;
  left: 2%;
  top: 55%;
}

.thumb {
  position: absolute;
  right: 3%;
  bottom: 15%;
  width: 30%;
}
.thumb img {
  width: 100%;
}
</style>