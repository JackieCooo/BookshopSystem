<template>
  <div>
    <el-space direction="vertical" :spacer="spacer">
      <el-space v-for="i in info.bookInfo" :key="i" class="chart-box" alignment="start">
        <el-tag type="success">{{i.id}}</el-tag>
        <el-image :src="i.pic" fit="fill" class="img-box"></el-image>
        <el-descriptions>
          <template #title>
            <div>《{{i.name}}》</div>
          </template>
          <el-descriptions-item label="作者">{{i.author}}</el-descriptions-item>
          <el-descriptions-item label="出版时间">{{i.time}}</el-descriptions-item>
          <el-descriptions-item label="出版社">{{i.publisher}}</el-descriptions-item>
          <el-descriptions-item label="价格">{{i.price}}元</el-descriptions-item>
          <el-descriptions-item v-if="i.hasEBook || i.hasSecondhandBook">
            <el-space>
              <el-button v-if="i.hasEBook" type="primary" plain>电子书</el-button>
              <el-button v-if="i.hasSecondhandBook" type="primary" plain>二手书</el-button>
            </el-space>
          </el-descriptions-item>
        </el-descriptions>
      </el-space>
    </el-space>
  </div>
</template>

<script setup>
import {h} from 'vue'
import {ElDivider} from 'element-plus'

const spacer = h(ElDivider, { direction: 'horizontal' })
</script>

<script>
export default {
  name: "ChartBoard",
  props: {
    info: {
      bookInfo: [
        {
          id: Number,
          name: String,
          pic: String,
          author: String,
          time: String,
          publisher: String,
          price: Number,
          hasEBook: Boolean,
          hasSecondhandBook: Boolean,
        }
      ]
    }
  }
}
</script>

<style scoped>
.img-box {
  width: 100px;
  height: 150px;
}

.chart-box {
  height: 150px;
  width: 700px;
}

.el-descriptions {
  width: 600px;
}
</style>