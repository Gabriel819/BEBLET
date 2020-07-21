<template>
  <v-ons-navigator swipeable   
    options.animation="slide-ios"
    :page-stack="this.$store.state.settingPageStack"
  ></v-ons-navigator>
</template>

<script>
import DetailSettingPage from './DetailSettingPage.vue'
import SettingMainPage from './SettingMainPage.vue'

export default {
  data: function () {
    return {
      pageStack: [SettingMainPage],
    }
  },
  methods: {
    changeTopToolbar: function(payload) {
      if(this.pageStack.length === 1) {
        this.$store.commit('changePageName', payload);
      }
    },
    updateStack: function(payload) {
      this.$store.commit('pushStack', payload);
      this.pageStack.push(payload);
    }
  },
  components: {
    DetailSettingPage,
    SettingMainPage
  }
}
</script>

<style>

</style>