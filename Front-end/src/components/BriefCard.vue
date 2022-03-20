<template>
  <div v-if="info != null">
    <el-card v-if="type === 'hot' || type === 'new'">
      <template #header>
        <el-row justify="space-between" class="card-header" :body-style="{ padding: '0px' }">
          <el-col :span="5"><span class="bold-title">{{title}}</span></el-col>
          <el-col :span="2"><el-button circle :icon="ArrowRight" class="card-btn" @click="gotoPage"></el-button></el-col>
        </el-row>
      </template>
      <el-carousel :autoplay="false" height="150px" arrow="never">
        <el-carousel-item v-for="i in 3" :key="i">
          <el-space alignment="start" :size="10">
            <el-image :src="loadPic(info[i-1].id)" class="card-img" fit="fill"></el-image>
            <el-space direction="vertical" :size="0" alignment="start">
              <span class="bold-title" @click="loadProduct(info[i-1].id)">{{info[i-1].name}}</span>
              <span class="card-content">{{contentFormatting(i-1)}}</span>
            </el-space>
          </el-space>
        </el-carousel-item>
      </el-carousel>
    </el-card>

    <el-card v-else-if="type === 'review'">
      <template #header>
        <el-row justify="space-between" class="card-header" :body-style="{ padding: '0px' }">
          <el-col :span="5"><span class="bold-title">{{title}}</span></el-col>
          <el-col :span="2"><el-button circle :icon="ArrowRight" class="card-btn" @click="gotoPage"></el-button></el-col>
        </el-row>
      </template>
      <el-carousel :autoplay="false" height="150px" arrow="never">
        <el-carousel-item v-for="i in 3" :key="i">
          <el-space alignment="start" :size="10">
            <el-image :src="loadPic(info[i-1].bookId)" class="card-img" fit="fill"></el-image>
            <el-space direction="vertical" :size="0" alignment="start">
              <span class="bold-title" @click="loadReview(info[i-1].id)">{{info[i-1].title}}</span>
              <span class="card-content">{{contentFormatting(i-1)}}</span>
            </el-space>
          </el-space>
        </el-carousel-item>
      </el-carousel>
    </el-card>

    <el-card v-else-if="type === 'news'">
      <template #header>
        <el-row justify="space-between" class="card-header" :body-style="{ padding: '0px' }">
          <el-col :span="5"><span class="bold-title">{{title}}</span></el-col>
          <el-col :span="2"><el-button circle :icon="ArrowRight" class="card-btn" @click="gotoPage"></el-button></el-col>
        </el-row>
      </template>
      <el-carousel :autoplay="false" height="150px" arrow="never">
        <el-carousel-item v-for="i in 3" :key="i">
          <el-space alignment="start" :size="10" direction="vertical">
            <span class="bold-title" @click="loadReview(info[i-1].id)">{{info[i-1].title}}</span>
            <span class="card-content">{{contentFormatting(i-1)}}</span>
          </el-space>
        </el-carousel-item>
      </el-carousel>
    </el-card>
  </div>
</template>

<script setup>
import {ArrowRight} from "@element-plus/icons-vue";
</script>

<script>
export default {
  name: "BriefCard",
  data() {
    return {
      info: null,
    }
  },
  props: {
    title: String,
    type: String,
  },
  methods: {
    // 页面跳转
    gotoPage() {
      switch (this.type) {
        case 'new':
          this.$router.replace('/latest')
          break
        case 'review':
          this.$router.replace('/review')
          break
        case 'news':
          this.$router.replace('/news')
          break
        case 'hot':
          this.$router.replace('/hottest')
          break
        default: break
      }
    },
    // 加载图片
    loadPic(id) {
      return this.$store.state.baseUrl + "api/book/pic/" + id.toString()
    },
    // 文本格式化
    contentFormatting(i) {
      let text = '';
      if (this.type === 'new' || this.type === 'hot') text = this.info[i].bookIntroduction
      else if (this.type === 'review') text = this.info[i].intro
      else if (this.type === 'news') text = this.info[i].content
      return text.length > 100 ? text.substr(0, 80) + '...' : text
    },
    // 跳转至产品页
    loadProduct(id) {
      this.$router.push('/book/' + id.toString())
    },
    // 跳转至书评页
    loadReview(id) {
      this.$router.push('/review/' + id.toString())
    },
  },
  // 组件创建时请求数据
  async created() {
    switch (this.type) {
      case 'new':
        await this.$http.get('api/chart/new?detail=5&num=3')
        .then((res) => {
          this.info = res.data.data
        })
        .catch((err) => {
          console.log(err)
        })
        break
      case 'review':
        await this.$http.get('api/review/chart?num=3')
        .then((res) => {
          this.info = res.data.data
        })
        .catch((err) => {
          console.log(err)
        })
        break
      case 'news':
        await this.$http.get('api/news?num=3')
        .then((res) => {
          this.info = res.data.data
        })
        .catch((err) => {
          console.log(err)
        })
        break
      case 'hot':
        await this.$http.get('api/chart/all?detail=5&num=3')
        .then((res) => {
          this.info = res.data.data
        })
        .catch((err) => {
          console.log(err)
        })
        break
      default: break
    }
  },
}
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
  width: 100px;
  height: 150px;
}

:deep(.el-carousel__button) {
  width: 18px;
  height: 8px;
  border-radius: 8px;
  background-color: #c8c9cc;
}

:deep(.is-active .el-carousel__button) {
  width: 18px;
  height: 8px;
  border-radius: 8px;
  background-color: #409EFF;
}

.bold-title {
  font-weight: bold;
  margin-bottom: 5px;
}

.card-content {
  text-align: start;
}
</style>