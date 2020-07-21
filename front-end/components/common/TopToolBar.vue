<template>
  <v-ons-toolbar class="center">
    <p class="search_area center">
      <v-ons-search-input placeholder="검색어를 입력하세요." v-model="query"></v-ons-search-input>
    </p>
  </v-ons-toolbar>
</template>

<script>
export default {
  data: function () {
    return {
      query: ''
    }
  }
}
</script>

<style>
.search_area {
  margin-top: 10px;
}
</style>