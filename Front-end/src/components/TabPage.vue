<template>
  <el-tabs v-model="firstPage" class="box">
    <el-tab-pane name="1">
      <template #label>
        <b>首页</b>
      </template>
      <FrontPage></FrontPage>
    </el-tab-pane>
    <el-tab-pane name="2">
      <template #label>
        <b>推荐</b>
      </template>
      <RecommendPage></RecommendPage>
    </el-tab-pane>
    <el-tab-pane name="3">
      <template #label>
        <b>排行榜</b>
      </template>
      <ChartPage></ChartPage>
    </el-tab-pane>
    <el-tab-pane name="4">
      <template #label>
        <b>新品上架</b>
      </template>
      <LatestBookPage></LatestBookPage>
    </el-tab-pane>
  </el-tabs>
</template>

<script setup>
import {ref} from 'vue'

const firstPage = ref('1')
</script>

<script>
import RecommendPage from "@/components/RecommendPage";
import ChartPage from "@/components/ChartPage";
import LatestBookPage from "@/components/LatestBookPage";
import FrontPage from "@/components/FrontPage";

export default {
  name: "TabPage",
  components : {
    RecommendPage,
    ChartPage,
    LatestBookPage,
    FrontPage
  }
}
</script>

<style scoped>
:deep(.el-tabs__active-bar) {
  background-color: #337ecc;
  height: 5px;
}

:deep(.el-tabs__item.is-active) {
  color: #409eff;
  font-family: "微软雅黑", serif;
  font-size: 20px;
}

:deep(.el-tabs__item) {
  color: black;
  font-family: "微软雅黑", serif;
  font-size: 15px;
}

:deep(.el-tabs__item:hover) {
  color: #409eff;
}

:deep(el-tabs__nav-wrap) {
  box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.15);
}

:deep(.el-tabs__nav-scroll){
  border-bottom: 1px solid #000000;
  box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.15);
}
</style>