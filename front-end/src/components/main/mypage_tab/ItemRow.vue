<template>
  <v-ons-list-item tappable v-on:click="returnOrCancel">
    <div class="left">
      <img class="list-item__thumbnail" v-bind:src="thumbRoot + itemInfo.engName + '.png'">
    </div>
    <div class="center">
      <span class="list-item__title">{{ itemInfo.korName }}</span><span class="list-item__subtitle">{{ Item.end_TIME }} 까지</span>
    </div>
  </v-ons-list-item>
</template>

<script>
import { cancelOrReturn } from '../../../api/index'

export default {
  data: function() {
    return {
      thumbRoot: './src/assets/images/tablets/',
      get id() {
        return window.localStorage.getItem("id");
      }
    }
  },
  props: ['Item'],
  methods: {
    returnOrCancel() {
      var thisComponent = this;
      cancelOrReturn(this.id, this.Item.idx).then((res) => {
        var payload = {
          state: res.data,
          item: this.Item
        }
        thisComponent.$emit("cancelOrReturn", payload);
        // alert("hello");
      })
    }
  },
  computed: {
    totalItemList() {
      return this.$store.state.items;
    },
    itemInfo() {
      return this.$store.state.items[this.Item.category_IDX - 1];
    }
  }
}
</script>

<style>

</style>