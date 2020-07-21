<template>
  <v-ons-page class="app">
    <top-tool-bar></top-tool-bar>
    <main-view></main-view>  
  </v-ons-page>
</template>

<script>
import MainView from './components/main/MainVIew.vue'
import TopToolBar from './components/common/TopToolBar.vue'

export default {
  name: 'app',
  components: {
    MainView,
    TopToolBar
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.center {
  text-align: center;
}
</style>
