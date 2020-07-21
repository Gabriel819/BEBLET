<template>
  <div class="category_container">
    <div class="category">
      <div v-for="(category, i) in categories" v-bind:key="i" class="category_btn center" v-bind:class="{selected : category.selected}" v-on:click="changeCategory(i)">
        {{ category.korName }}
      </div>
    </div>
  </div>
</template>

<script>
import AreaHeader from './AreaHeader.vue'

export default {
  data: function () {
    return {
      selectedIdx: 0,
      categories: [
        {
          engName: 'book',
          korName: '서적',
          selected: true
        },
        {
          engName: 'experiment',
          korName: '실험 도구',
          selected: false
        },
        {
          engName: 'excercise',
          korName: '운동 용품',
          selected: false
        },
        {
          engName: 'art',
          korName: '예술 용품',
          selected: false
        }
      ]
    }
  },
  methods: {
    changeCategory: function (i) {
      this.categories[this.selectedIdx].selected = false;
      this.categories[i].selected = true;
      this.selectedIdx = i;

      this.$emit('selected', this.categories[this.selectedIdx].korName);
    }
  },
  components: {
    AreaHeader
  }
}
</script>

<style>
.category_container {
  overflow: scroll;
  height: 60px;
  margin-top: 5%;
}

.category {
  width: 410px;
}

.category_btn {
  width: 80px;
  height: 30px;
  display: inline-block;
  font-size: 20px;
  margin: 10px;
  margin-bottom: 0;
  background-color: white;
  border: solid 1px;
}

.category_btn > img {
  width: 100%;
}

.category_title {
  margin-top: 5px;
  font-size: 15px;
}

.selected {
  background-color: darkgray;
  color: white;
}

</style>