<template>
  <p class="home-header">
    {{ areaName }}
  </p>
</template>

<script>
export default {
  props: ['area-name']
}
</script>

<style>
.home-header {
  display: block;
  font-size: 25px;
  font-weight: 900;
  margin-bottom: 0; 
}
</style>