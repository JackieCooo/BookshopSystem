<template>
  <el-tabs v-model="firstPage">
    <el-tab-pane name="1">
      <template #label>
        <b>首页</b>
      </template>
      <FrontPage></FrontPage>
    </el-tab-pane>
    <el-tab-pane name="2">
      <template #label>
        <b>精品书评</b>
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
    <el-tab-pane name="5">
      <template #label>
        <b>畅销推荐</b>
      </template>
      <HottestSellersPage></HottestSellersPage>
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
import HottestSellersPage from "@/components/HottestSellersPage";

export default {
  name: "TabPage",
  components : {
    RecommendPage,
    ChartPage,
    LatestBookPage,
    FrontPage,
    HottestSellersPage,
  }
}
</script>

<style scoped>
.el-tabs__active-bar {
  background-color: #337ecc;
  height: 5px;
}

.el-tabs__item.is-active {
  color: #409eff;
  font-family: "微软雅黑", serif;
  font-size: 20px;
}

.el-tabs__item {
  color: black;
  font-family: "微软雅黑", serif;
  font-size: 15px;
}

.el-tabs__item:hover {
  color: #409eff;
}
</style>