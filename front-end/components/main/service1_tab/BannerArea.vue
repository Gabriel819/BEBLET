<template>
  <div class="banner">
    <v-ons-carousel fullscreen swipeable auto-scroll overscrollable auto-scroll-ratio="0.1"
      :index.sync="carouselIndex"
    >
      <v-ons-carousel-item v-for="(value, key) in items" :style="{backgroundColor: value}" v-bind:key="key">
        <div style="text-align: center; font-size: 30px; margin-top: 20px; color: #fff;">배너 {{ carouselIndex + 1}}</div>
      </v-ons-carousel-item>
    </v-ons-carousel>
    <div class="dots">
      <span :index="dotIndex - 1" v-for="dotIndex in Object.keys(items).length" :key="dotIndex" style="cursor: pointer" @click="carouselIndex = dotIndex - 1">
        {{ carouselIndex === dotIndex - 1 ? '\u25CF' : '\u25CB' }}
      </span>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      carouselIndex: 0,
      items: {
        BLUE: '#085078',
        DARK: '#373B44',
        ORANGE: '#D38312'
      },
    }
  }
}
</script>

<style>
.banner {
  width: 100%;
  height: 230px;
}

.banner > img {
  width: 100%;
  height: 240px;
}

.dots {
  text-align: center;
  font-size: 30px;
  color: lightgrey;
  position: relative;
  bottom: 40px;
  left: 0;
  right: 0;
}
</style>