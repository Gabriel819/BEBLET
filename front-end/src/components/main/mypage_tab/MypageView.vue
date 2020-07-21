<template>
  <v-ons-page>
    <v-ons-navigator class="content"
      v-bind:page-stack="this.$store.state.myPageStack"
      v-on:push-page="pushPage"
      v-on:pop-page="popPage">
    </v-ons-navigator>
  </v-ons-page>
</template>

<script>
import ContentArea from './ContentArea.vue'

export default {
  components: {
    ContentArea
  },
  methods: {
    pushPage: function ($event) {
      switch($event.pageName) {
      case '반납':
        $event.page = TabletPage;
        break;
      }
      this.$store.commit("myPageStackPush", $event);
    },
    popPage: function ($event) {
      this.$store.commit("myPageStackPop");
    }
  }

}
</script>

<style>

</style>