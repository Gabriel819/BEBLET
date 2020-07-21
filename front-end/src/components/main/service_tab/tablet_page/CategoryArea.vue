<template>
  <div class="category_list_container">
    <div class="category_list">
      <div class="category_item"
        v-for="category in categories" 
        v-bind:key="category.idx" 
        v-on:click="select(category.idx)" 
        v-bind:class="{'selected' : category.idx === selectedIdx}">
        {{ category.korName }}
      </div>
    </div>

  </div>
</template>

<script>
export default {
  props:["categories", "selectedIdx"],
  data: function () {
    return {

    }
  },
  methods: {
    select: function(idx) {
      this.$emit("select-category", idx);
    }
  }
}
</script>

<style>
.category_list_container {
  position: relative;
  width: 100%;
  overflow-x: scroll;
  top: 3%;
}

.category_list {
  width: 1000px;
  position: relative;
}

.selected {
  background-color: #0076FF;
  color: #ffffff;
  border: 0;
  border-radius: 3px;
}



.category_item {
  display: inline-block;
  color: rgb(190, 190, 190);
  border: solid 1px rgb(190, 190, 190);
  border-radius: 3px;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 15px;
  margin-left: 7px;
  margin-right: 7px;
  padding: 2px;
}
</style>