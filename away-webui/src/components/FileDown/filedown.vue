<template>
  <el-carousel :interval="4000" type="card" height="200px">
    <el-carousel-item v-for="item in files" :key="item.getUrl()">
      <el-image v-if="['image/jpeg','image/png','image/gif'].includes(item.getType())" :src="item.getUrl()" :preview-src-list="[item.getUrl()]">
      </el-image>
      <div v-else  @click="item.fileDown()">{{ item.getFileName() }}</div>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name:"filedown",
  props: ['files'] //passvalue是父组件传递给子组件的参数
}
</script>

<style>

</style>
