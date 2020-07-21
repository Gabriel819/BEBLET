<template>
  <v-ons-page>
    <v-ons-navigator class="content"
      v-bind:page-stack="this.$store.state.pageStack"
      v-on:push-page="pushPage"
      v-on:pop-page="popPage">
    </v-ons-navigator>
  </v-ons-page>
</template>

<script>
import ServiceViewMainPage from './main_page/ServiceViewMainPage.vue';
import TabletPage from './tablet_page/TabletPage.vue';
import TabletSelectPage from './tablet_page/TabletSelectPage.vue';

export default {
  data: function () {
    return {
    }
  },
  methods: {
    pushPage: function ($event) {
      switch($event.pageName) {
      case '태블릿':
        $event.page = TabletPage;
        break;
      case '태블릿 선택':
        $event.page = TabletSelectPage;
        break;
      }
      this.$store.commit("pageStackPush", $event);
    },
    popPage: function ($event) {
      this.$store.commit("pageStackPop");
    }
  },
  components: {
    ServiceViewMainPage,
    TabletPage,
    TabletSelectPage
  }

}
</script>

<style>
.content {
  overflow: scroll;
}
</style>