<template>
  <el-row :gutter="20" justify="center">
    <el-col :span="4" class="bg-gray" :pull="3">
      <el-image class="logo" fit="fill" :src="require('@/assets/Logo_50px.png')"></el-image>
    </el-col>
    <el-col :span="12" class="bg-gray">
      <SearchPanel></SearchPanel>
    </el-col>
  </el-row>
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

<script setup>

</script>

<style scoped>
.bg-gray {
  /*background-color: gray;*/
}

.el-col {
  height: 75px;
}

.el-image {
  margin-top: 10px;
  margin-right: 100px;
}
</style>