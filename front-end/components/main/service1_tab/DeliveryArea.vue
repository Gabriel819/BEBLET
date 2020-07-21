<template>
  <div class="delivery">
    <area-header v-bind:area-name="headerName"></area-header>
    <div class="delivery_content center">
      <div class="delivery_btn">
        <v-ons-button v-on:click="showDelivery">배달 현황보기</v-ons-button>
      </div>
      <v-ons-dialog
        cancelable
        :visible.sync="deliveryModal"
      >
        <p style="text-align: center">배달 조회 기능 추가 부분</p>
      </v-ons-dialog>
    </div>
  </div>
</template>

<script>
import AreaHeader from './AreaHeader.vue'

export default {
  data: function () {
    return {
      headerName: '주문 배달 확인',
      deliveryModal: false
    }
  },
  methods: {
    showDelivery: function () {
      this.deliveryModal = true;
    }
  },
  components: {
    AreaHeader
  }
}
</script>

<style scoped>
.delivery {
  background-color: gray;
  color: white;
  height: 220px;
  padding-top: 25px;
}

.delivery > p {
  margin-top: 0;
}

.delivery ons-button {
  margin-top: 30px;
}
</style>