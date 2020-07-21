<template>
  <v-ons-page class="tablet_page" v-on:deviceBackButton="popPage($event)">
    <category-area 
      v-bind:categories="categories" 
      v-bind:selectedIdx="selectedIdx"
      v-on:select-category="selectCategory"
      ></category-area>
    <tablet-list-area
      v-bind:selectedIdx="selectedIdx"
      v-on:push-tablet-page="pushPageReq($event)"
      >
    </tablet-list-area>
  </v-ons-page>
</template>

<script>
import CategoryArea from './CategoryArea.vue';
import TabletListArea from './TabletListArea.vue';

var popProcess = async function() {

}

export default {
  key: "TabletPage",
  name: "Tablet",
  toolBarName: "태블릿",
  data: function () {
    return {
      categories: [
        {
          idx: 0,
          korName: '전체'
        },
        {
          idx: 1,
          korName: '1호관',
        },
        {
          idx: 2,
          korName: '2호관',
        },
        {
          idx: 3,
          korName: '3호관',
        },
        {
          idx: 4,
          korName: '4호관',
        },
        {
          idx: 5,
          korName: '5호관',
        },
        {
          idx: 6,
          korName: '6호관',
        },
        {
          idx: 7,
          korName: '7호관',
        },
        {
          idx: 8,
          korName: '8호관',
        },
        {
          idx: 9,
          korName: '9호관',
        },
        {
          idx: 10,
          korName: '하이테크 관',
        },
        {
          idx: 11,
          korName: '정석학술정보관',
        },
        {
          idx: 12,
          korName: '서호관',
        },
        {
          idx: 13,
          korName: '법학전문대학원',
        },
      ],
      selectedIdx: 0,
 
    }
  },
  methods: {
    selectCategory: function(idx) {
      this.selectedIdx = idx;
    },
    popPage: function ($event) {
      $event.preventDefault();
      this.$store.commit("pageStackPop");
    },
    pushPageReq($event) {
      this.$emit("push-page", $event);
    }
  },
  components: {
    CategoryArea,
    TabletListArea
  }

}
</script>

<style>
.tablet_page {
  width: 100%;
}

</style>