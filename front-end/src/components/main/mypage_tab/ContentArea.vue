<template>
  <div class="content_area">
    <v-ons-list>
      <v-ons-list-header>
        반납 임박 물건
      </v-ons-list-header>
      <item-row :Item="testApplePencil"></item-row>

      <v-ons-list-header>
        빌린 물건
      </v-ons-list-header>
      <item-row :Item="testApplePencil"></item-row>
      <item-row :Item="testIpad7"></item-row>
    </v-ons-list>
  </div>
</template>

<script>
import ItemRow from './ItemRow.vue';

export default {
  data: function() {
    return {
      testIpad7: {
        user: 'hyuk',
        engName: 'ipad_7th',
        korName: '아이패드 7세대',
        dueDate: '2020-05-04',
        dueTime: '오후 07:00'
      },
      testApplePencil: {
        user: 'hyuk',
        engName: 'apple_pencil_1th',
        korName: '애플펜슬 1세대',
        dueDate: '2020-05-02',
        dueTime: '오후 02:00'
      }
    }
  },
  components: {
    ItemRow
  }
}
</script>

<style>

</style>