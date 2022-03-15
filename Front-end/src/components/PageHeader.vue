<template>
  <div>
    <el-row justify="space-between" class="container">
      <slot name="title"></slot>
      <el-space>
        <slot name="button"></slot>
      </el-space>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "PageHeader"
}
</script>

<style lang="less" scoped>
.container {
  background-color: #E4E7ED;
  width: 800px;
  height: 50px;
  padding-left: 20px;
  padding-right: 20px;
  align-items: center;
  text-align: center;
}
</style>