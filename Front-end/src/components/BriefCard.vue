<template>
  <el-card>
    <template #header>
      <el-row justify="space-between" class="card-header" :body-style="{ padding: '0px' }">
        <el-col :span="5"><b>{{title}}</b></el-col>
        <el-col :span="2"><el-button circle :icon="ArrowRight" class="card-btn" @click="gotoPage"></el-button></el-col>
      </el-row>
    </template>
    <el-carousel :autoplay="false" height="150px" arrow="never">
      <el-carousel-item v-for="i in testContent" :key="i">
        <el-row justify="space-between">
          <el-col :span="6">
            <el-image :src="i.src" class="card-img" fit="fill"></el-image>
          </el-col>
          <el-col :span="12">
            <p><b>{{i.title}}</b></p>
            <p>{{i.content}}</p>
          </el-col>
        </el-row>
      </el-carousel-item>
    </el-carousel>
  </el-card>
</template>

<script>

export default {
  name: "BriefCard",
  props: {
    title: String,
    type: String,
  },
  methods: {
    gotoPage() {
      switch (this.type) {
        case 'product':
          this.$router.replace('/latest')
          break
        case 'review':
          this.$router.replace('/recommend')
          break
        case 'news':
          // this.$router.replace('/latest')
          break
        case 'shops':
          // this.$router.replace('/latest')
          break
        default: break
      }
    }
  }
}
</script>

<script setup>
import {ArrowRight} from "@element-plus/icons-vue";

const testContent = [
  {src: "", title: "1", content: "test1"},
  {src: "", title: "2", content: "test2"},
  {src: "", title: "3", content: "test3"}
]
</script>

<style scoped>
.el-card {
  width: 470px;
  height: 250px;
}

.card-btn {
  height: 20px;
  width: 20px;
}

.card-img {
  margin-top: 65px;
}

:deep(.el-carousel__button) {
  width: 18px;
  height: 8px;
  border-radius: 8px;
  background-color: white;
}

:deep(.is-active .el-carousel__button) {
  width: 18px;
  height: 8px;
  border-radius: 8px;
  background-color: #409EFF;
}
</style>