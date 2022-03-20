<template>
  <div>
    <el-space class="top-bar">
      <el-image class="logo" fit="fill" :src="require('@/assets/Logo.png')"></el-image>
      <SearchPanel></SearchPanel>
    </el-space>
  </div>
</template>

<script>
import SearchPanel from "@/components/SearchPanel";

export default {
  name: "TapBar",
  components : {
    SearchPanel,
  }
}
</script>

<style lang="less" scoped>
.top-bar {
  height: 75px;
}

.el-image {
  margin-right: 100px;
}
</style>