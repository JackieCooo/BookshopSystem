<template>
  <div>
    <el-row justify="space-between">
      <el-space spacer="|" :size="15">
        <el-button type="text">首页</el-button>
        <el-button type="text">精选书评</el-button>
        <el-button type="text">排行榜</el-button>
        <el-button type="text">新品上架</el-button>
        <el-button type="text">畅销推荐</el-button>
      </el-space>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "WindowHeader",
}
</script>

<style scoped>
.el-space {
  color: white;
}

.el-button {
  margin-top: 5px;
  color: white;
}
</style>