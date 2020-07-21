<template>
  <v-ons-page>
    <div class="category_area">
      <category-area v-on:selected="selectedCategory"></category-area>
      <content-area v-bind:selectedCategory="category"></content-area>
    </div>
  </v-ons-page>
</template>

<script>
import CategoryArea from './CategoryArea.vue'
import AreaHeader from './AreaHeader.vue'
import ContentArea from './ContentArea.vue'


export default {
  data: function () {
    return {
      homeArea: ['카테고리', '배달 위치 확인'],
      category: ''
    }
  },
  methods: {
    selectedCategory: function (payload) {
      this.category = payload;
    }
  },
  components: {
    CategoryArea,
    AreaHeader,
    ContentArea,
  }
}
</script>

<style>

</style>