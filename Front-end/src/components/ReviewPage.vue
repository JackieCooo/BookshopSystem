<template>
  <div v-if="bookInfo != null && reviewInfo != null">
    <el-space alignment="start" :size="50">
      <el-space direction="vertical" alignment="start" class="main-content">
        <h2 class="align-left">{{reviewInfo.title}}</h2>
        <el-space class="subtitle">
          <span>{{reviewInfo.author}}</span>
          <span class="gray-text">评论</span>
          <span>《{{bookInfo.name}}》</span>
          <span class="gray-text date">{{reviewInfo.date}}</span>
        </el-space>
        <span class="align-left review-content">{{reviewInfo.content}}</span>
      </el-space>
      <el-space direction="vertical" alignment="start">
        <el-button type="text">> {{bookInfo.name}}</el-button>
        <el-image fit="fill" :src="loadPic(bookInfo.id)" class="book-cover"></el-image>
        <el-descriptions :column="1">
          <el-descriptions-item>
            <template #label><span class="gray-text">作者:</span></template>
            <span class="item-text">{{bookInfo.author}}</span>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label><span class="gray-text">出版社:</span></template>
            <span class="item-text">{{bookInfo.publisher}}</span>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label><span class="gray-text">价格:</span></template>
            <span class="item-text">{{bookInfo.price}}</span>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label><span class="gray-text">出版时间:</span></template>
            <span class="item-text">{{bookInfo.date}}</span>
          </el-descriptions-item>
        </el-descriptions>
      </el-space>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "ReviewPage",
  data() {
    return {
      reviewInfo: null,
      bookInfo: null,
    }
  },
  async created() {
    await this.$http.get('api/review/' + this.$route.params.id.toString())
    .then((res) => {
      this.reviewInfo = res.data.data
    })
    .catch((err) => {
      console.log(err)
    })
    await this.$http.get('api/book/' + this.reviewInfo.bookId.toString())
    .then((res) => {
      this.bookInfo = res.data.data
    })
    .catch((err) => {
      console.log(err)
    })
  },
  methods: {
    loadPic(id) {
      return "http://localhost:8001/api/book/pic/" + id.toString()
    }
  },
}
</script>

<style lang="less" scoped>
.gray-text {
  color: #909399;
}

.book-cover {
  width: 150px;
  height: 200px;
}

.main-content {
  width: 800px;
}

.item-text {
  color: black;
}

.align-left {
  text-align: start;
}

.date {
  margin-left: 50px;
}

.subtitle {
  margin-bottom: 20px;
}

.review-content {
  line-height: 35px;
}
</style>