<template>
  <div v-if="info != null">
    <el-space direction="vertical" :spacer="spacer">
      <el-space v-for="i in 10" :key="i" class="chart-box" alignment="start">
        <el-tag type="success">{{i}}</el-tag>
        <el-image :src="loadPic(info[i-1].id)" fit="fill" class="img-box"></el-image>
        <el-descriptions>
          <template #title>
            <div @click="loadProductPage(info[i-1].id)">《{{info[i-1].name}}》</div>
          </template>
          <el-descriptions-item label="作者">{{info[i-1].author}}</el-descriptions-item>
          <el-descriptions-item label="出版时间">{{info[i-1].date}}</el-descriptions-item>
          <el-descriptions-item label="出版社">{{info[i-1].publisher}}</el-descriptions-item>
          <el-descriptions-item label="价格">{{info[i-1].price}}元</el-descriptions-item>
          <el-descriptions-item v-if="info[i-1].hasEBook || info[i-1].hasSecondhandBook">
            <el-space>
              <el-button v-if="info[i-1].hasEBook" type="primary" plain>电子书</el-button>
              <el-button v-if="info[i-1].hasSecondhandBook" type="primary" plain>二手书</el-button>
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
  data() {
    return {
      info: null,
    }
  },
  props: {
    type: String,
  },
  async created() {  // 组件创建时请求数据
    console.log(this.type + '组件创建')
    await this.$http.get('/api/chart/' + this.type)
    .then((res) => {
      this.info = res.data.data
      console.log(this.info)
    })
    .catch((err) => {
      console.log(err)
    })
  },
  watch: {
    // 用户切换tab时重新请求数据
    type: async function (val, oldVal) {
      console.log(val, oldVal)
      await this.$http.get('/api/chart/' + this.type)
      .then((res) => {
        this.info = res.data.data
        console.log(this.info)
      })
      .catch((err) => {
        console.log(err)
      })
    }
  },
  methods: {
    // 跳转到图书详情页
    loadProductPage(id) {
      this.$router.replace('/book/' + id.toString())
    },
    // 加载封面图
    loadPic(id) {
      return this.$store.state.baseUrl + "api/book/pic/" + id.toString()
    }
  },
}
</script>

<style scoped>
.img-box {
  width: 100px;
  height: 150px;
}

.chart-box {
  height: 150px;
  width: 800px;
}

.el-descriptions {
  width: 600px;
}
</style>