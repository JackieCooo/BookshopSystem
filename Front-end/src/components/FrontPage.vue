<template>
  <el-row :gutter="20" justify="center">
    <el-col :span="4">
      <MenuBar class="rounded-border side-bar"></MenuBar>
    </el-col>
    <el-col :span="12">
      <el-carousel :interval="4000" height="420px" class="rounded-border">
        <el-carousel-item v-for="i in picUrl" v-bind:key="i.id">
          <el-image fit="fill" :src="i.url"></el-image>
        </el-carousel-item>
      </el-carousel>
    </el-col>
    <el-col :span="4">
      <AccountPanel class="rounded-border side-bar"></AccountPanel>
    </el-col>
  </el-row>
  <el-space :size="20">
    <el-space>
      <SideBoard></SideBoard>
    </el-space>
    <el-space direction="vertical" :size="20">
      <BriefCard title="新品推荐" class="rounded-border" type="new"></BriefCard>
      <BriefCard title="畅销推荐" class="rounded-border" type="hot"></BriefCard>
    </el-space>
    <el-space direction="vertical" :size="20">
      <BriefCard title="书评分享" class="rounded-border" type="review"></BriefCard>
      <BriefCard title="最新咨讯" class="rounded-border" type="news"></BriefCard>
    </el-space>
  </el-space>
</template>

<script>
import MenuBar from "@/components/MenuBar";
import AccountPanel from "@/components/AccountPanel";
import BriefCard from "@/components/BriefCard";
import SideBoard from "@/components/SideBoard";

export default {
  name: "FrontPage",
  components: {
    MenuBar,
    AccountPanel,
    BriefCard,
    SideBoard
  }
}
</script>

<script setup>
const picUrl = [
  {id: 1, url: require('@/assets/testFile/book1.jpeg')},
  {id: 2, url: require('@/assets/testFile/book2.jpeg')},
  {id: 3, url: require('@/assets/testFile/book3.jpeg')},
  {id: 4, url: require('@/assets/testFile/book4.jpeg')},
  {id: 5, url: require('@/assets/testFile/book5.jpeg')},
  {id: 6, url: require('@/assets/testFile/book6.jpeg')}
]
</script>

<style scoped>
.rounded-border {
  border-radius: 10px;
}

.side-bar {
  height: 420px;
  border: 1px solid #E4E7ED;
}

.el-row {
  margin-bottom: 20px;
}
</style>