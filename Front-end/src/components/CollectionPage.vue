<template>
  <div>
    <el-space direction="vertical" alignment="start" :size="20">
      <PageHeader>
        <template #title>
          <span class="title">我的收藏</span>
        </template>
      </PageHeader>
      <el-space v-for="i in info" :key="i">
        <el-image :src="i.pic"></el-image>
        <span>《{{i.name}}》/{{i.author}}/{{i.publisher}}/{{i.date}}</span>
        <el-space>
          <el-button plain type="warning">取消收藏</el-button>
          <el-button plain type="warning">加入购物车</el-button>
        </el-space>
      </el-space>
    </el-space>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";

export default {
  name: "CollectionPage",
  components: {
    PageHeader,
  },
  data() {
    return {
      info: null,
    }
  }
}
</script>

<style lang="less" scoped>
.title {
  font-weight: bold;
}
</style>