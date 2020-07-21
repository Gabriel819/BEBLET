<template>
  <v-ons-page>
    <div class="content_area">
      <v-ons-list>
        <v-ons-list-header>
          빌린 물건
        </v-ons-list-header>
        <item-row v-for="item in rentItemList" :Item="item" v-bind:key="item.idx" v-on:cancelOrReturn="openAlert($event)"></item-row>
      </v-ons-list>
    </div>

    <v-ons-alert-dialog modifier="rowfooter"
      :title="alertName"
      :footer="{
        Cancel: () => returnOrCancelOpen = false,
        Ok: () => returnOrCancelApiCall()
      }"
      :visible.sync="returnOrCancelOpen"
    >
      {{ alertName }} 하시겠습니까?
    </v-ons-alert-dialog>

  </v-ons-page>
</template>

<script>
import { returnItem } from '../../../api/index'
import { kakaoCancel } from '../../../api/index'
import { rentListReq } from "../../../api/index";


import ItemRow from './ItemRow.vue';

export default {
  key: "MyPage",
  name: "MyPage",
  toolBarName: "BeBlet",
  data: function() {
    return {
      returnOrCancelOpen: false,
      get id() {
        return window.localStorage.getItem("id");
      },
      alertName: "",
      selectedIdx: -1,
    }
  },
  methods:{
    openAlert($event) {
      if($event.state === "return") {
        this.alertName = "반납";
        this.selectedIdx = $event.item.item_IDX;
      } 
      else if($event.state === "cancel") {
        this.alertName = "결제 취소";
        this.selectedIdx = $event.item.idx;
      }
      this.returnOrCancelOpen = true;
    },
    returnOrCancelApiCall() {
      console.log('호출');
      var thisComponent = this;
      if(this.alertName === "반납") {
        returnItem(this.id, this.selectedIdx).then((res) => {
          thisComponent.$ons.notification.alert("반납이 완료되었습니다.");
          rentListReq(this.id)
            .then((res) => {
            this.$store.commit("rentItemListUpdate", res);
          })
          thisComponent.returnOrCancelOpen = false;
        })

      } else if(this.alertName === "결제 취소") {
        console.log(this.selectedIdx);
        kakaoCancel(this.id, this.selectedIdx).then((res) => {
          thisComponent.$ons.notification.alert("결제 취소가 완료되었습니다.");
          rentListReq(this.id)
            .then((res) => {
            this.$store.commit("rentItemListUpdate", res);
          })
          thisComponent.returnOrCancelOpen = false;
        })
      }
    }
  },
  components: {
    ItemRow
  },
  computed: {
    rentItemList() {
      return this.$store.state.rentItemList;
    },
  }
}
</script>

<style>

</style>