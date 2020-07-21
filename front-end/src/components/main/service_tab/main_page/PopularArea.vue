<template>
  <div class="popular_list_container">
    <!-- <div class="popular_list">
      <div class="item" v-for="tablet in items" v-bind:key="tablet.korName">
        <img v-bind:src="'./src/assets/images/items/' + tablet.engName + '.png'" alt="">
        <p class="tablet_label">{{ tablet.korName }}</p>
      </div>
    </div> -->
    <carousel-3d
      v-bind:style="'margin:0'"
      v-bind:border="0" 
      v-bind:perspective="0" 
      v-bind:display="3"
      v-bind:width="180"
      v-bind:autoplay="true"
      v-bind:height="240"
      v-bind:space="170"
      v-bind:disable3d="true">
      <slide v-for="(tablet, i) in items" v-bind:key="i" v-bind:index="i" class="item" v-bind:class="tablet.maker" v-bind:style="'backgroundColor:transparent'">
        <div class="slide_container">
          <div class="item">
            <img v-bind:src="'./src/assets/images/tablets/' + tablet.engName + '.png'">
          </div>
        </div>
        <div class="tablet_label">{{ tablet.korName }}</div>
      </slide>
    </carousel-3d>
  </div>
</template>

<script>
export default {
  data: function () {
    return {

      carouselIndex: 0,
    }
  },
  methods: {

  },
  computed: {
    items: function () {
      return this.$store.state.items;
    },
    pageStackSize: function () {
      return this.$store.state.pageStack.length;
    }
  }
}
</script>

<style>

.popular_list_container {
  position: relative;
  margin-bottom: 20px;
}

.popular_list {
  position: relative;
  width: 300%;
}



.item {
  display: inline-block;
  margin: 15px;
  margin-bottom: 5px;
  text-align: center;
  width:150px;
  text-align:center;
}

.apple img {
  position: relative;
  bottom: 15px;  
}

.samsung img {
  position: relative;
  top: 10px;
}
/* 
.samsung .tablet_label {
  position: relative;
  top: 27px;
} */

.item img {
  max-width:100%;
  vertical-align:middle;
}

.slide_container {
  position: relative;
  text-align: center;
}

.tablet_label {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 15px;
  color: #cccccc;
  width: 100%;
  position: absolute;
  bottom: 25px;
  text-align: center;
}
</style>