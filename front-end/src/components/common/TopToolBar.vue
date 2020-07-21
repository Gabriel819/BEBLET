<template>
  <v-ons-toolbar class="top_tool_bar">
    <div class="left logo">
      <i class="fas fa-arrow-left" v-if="this.pageStack.length > 1" v-on:click="popPage"></i>
      {{ pageName }}
    </div>
  </v-ons-toolbar>
</template>

<script>
export default {
  data: function () {
    return {
      query: ''
    }
  },
  methods:{
    popPage: function () {
    switch(this.$store.state.tabbarIdx) {
      case 0:
        return this.$store.commit("pageStackPop");
      case 1:
        return this.$store.commit("myPageStackPop");
      case 2:
        return this.$store.commit("settingPageStackPop");
      }
    }
  },
  computed: {
    index: function () {
    },
    pageStack: function () {
      switch(this.$store.state.tabbarIdx) {
      case 0:
        return this.$store.state.pageStack;
      case 1:
        return this.$store.state.myPageStack;
      case 2:
        return this.$store.state.settingPageStack;
      }
    },
    pageName: function () {
      return this.pageStack[this.pageStack.length - 1].toolBarName;
    }
  }
}
</script>

<style>
.center {
  text-align: center;
}

.logo {
  position: absolute;
  left: 5%;
  top: 10%;
}

i.fa-arrow-left:active {
  color: #0076FF;
}

.search_area {
  margin-top: 10px;
}
</style>