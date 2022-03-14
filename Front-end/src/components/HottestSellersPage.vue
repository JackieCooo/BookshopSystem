<template>
  <div>
    <el-space alignment="start">
      <el-tabs type="card" v-model="activeName" @tab-click="loadChart(activeName)">
        <el-tab-pane v-for="i in types" :key="i" :name="i.id" :label="i.title"></el-tab-pane>
        <router-view></router-view>
      </el-tabs>
      <RightSideBoard title="最新资讯"></RightSideBoard>
    </el-space>
  </div>
</template>

<script setup>
import {ref} from 'vue'

const activeName = ref(1);
</script>

<script>
import RightSideBoard from "@/components/RightSideBoard";

export default {
  name: "HottestSellersPage",
  data() {
    return {
      types: [
        {id: 1, title: '全部', type: 'all'},
        {id: 2, title: '文学', type: 'literary'},
        {id: 3, title: '小说', type: 'novel'},
        {id: 4, title: '历史', type: 'history'},
        {id: 5, title: '社会', type: 'social'},
        {id: 6, title: '科学', type: 'tech'},
        {id: 7, title: '艺术', type: 'art'},
      ],
    }
  },
  components: {
    RightSideBoard,
  },
  methods: {
    loadChart(val) {
      console.log(val, typeof val)
      switch (val) {
        case 1:
          this.$router.replace('/hottest/all')
          break
        case 2:
          this.$router.replace('/hottest/literary')
          break
        case 3:
          this.$router.replace('/hottest/novel')
          break
        case 4:
          this.$router.replace('/hottest/history')
          break
        case 5:
          this.$router.replace('/hottest/social')
          break
        case 6:
          this.$router.replace('/hottest/tech')
          break
        case 7:
          this.$router.replace('/hottest/art')
          break
      }
    }
  }
}
</script>

<style scoped>
.el-tabs {
  margin-top: 30px;
}
</style>