<template>
  <div v-if="info != null">
    <el-space direction="vertical" alignment="start" :size="0">
      <h1 class="title">{{info.name}}</h1>
      <el-space alignment="start" size="large">
        <el-image :src="loadPic(info.id)" fit="contain"></el-image>
        <el-space direction="vertical" alignment="start" class="description" size="large">
          <div><span class="gray-text">作者: </span>{{info.author}}</div>
          <div><span class="gray-text">出版社: </span>{{info.publisher}}</div>
          <div><span class="gray-text">出版时间: </span>{{info.date}}</div>
          <div><span class="gray-text">价格: </span>{{info.price}}</div>
          <div><span class="gray-text">ISBN: </span>{{info.isbn}}</div>
        </el-space>
      </el-space>
      <el-space class="purchase-bar">
        <span class="gray-text">选择类型: </span>
        <el-select v-model="bookType">
          <el-option label="全新整书" :value="1"></el-option>
          <el-option label="二手书" :value="2" :disabled="!info.hasSecondhandBook"></el-option>
          <el-option label="电子书" :value="3" :disabled="!info.hasEBook"></el-option>
        </el-select>
        <span class="gray-text">选择数量: </span>
        <el-input-number v-model="quantity" :min="1"></el-input-number>
      </el-space>
      <el-space class="purchase-bar">
        <div class="price-text">¥ {{getPrice}}</div>
        <el-button type="warning" class="purchase-btn" @click="addToCart"><h3>加入购物车</h3></el-button>
        <el-button type="danger"  class="purchase-btn"><h3>立即购买</h3></el-button>
      </el-space>
      <el-collapse v-model="collapseNum" class="collapse-box">
        <el-collapse-item :name="1">
          <template #title>
            <h3 class="collapse-title">内容简介</h3>
          </template>
          <div>{{info.bookIntroduction}}</div>
        </el-collapse-item>
        <el-collapse-item :name="2">
          <template #title>
            <h3 class="collapse-title">作者简介</h3>
          </template>
          <div>{{info.authorIntroduction}}</div>
        </el-collapse-item>
        <el-collapse-item :name="3">
          <template #title>
            <h3 class="collapse-title">目录</h3>
          </template>
          <div>{{info.directory}}</div>
        </el-collapse-item>
      </el-collapse>
    </el-space>
  </div>
</template>

<script setup>
import {ref} from 'vue'

const collapseNum = ref(1)
</script>

<script>
export default {
  name: "ProductPage",
  data() {
    return {
      bookType: '全新整书',
      quantity: 1,
      info: null,
    }
  },
  methods: {
    // 加载封面图
    loadPic(id) {
      return this.$store.state.baseUrl + "api/book/pic/" + id.toString()
    },
    // 加入购物车
    async addToCart() {
      await this.$http({
        method: 'post',
        url: 'user/cart',
        data: {
          bookId: this.info.id,
          userId: this.$store.state.user.userId,
          quantity: this.quantity
        }
      })
      .then((res) => {
        console.log(res.data)
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },
  computed: {
    getPrice() {
      switch (this.bookType) {
        case '全新整书':
          return this.quantity * this.info.price
        case '二手书':
          return this.quantity * this.info.price
        case '电子书':
          return this.quantity * this.info.price
        default: return 0
      }
    }
  },
  async created() {
    console.log(this.$route.params.id)
    await this.$http.get('/api/book/' + this.$route.params.id)
    .then((res) => {
      this.info = res.data.data
      this.isDataOk = true
    })
    .catch((err) => {
      console.log(err)
    })
  }
}
</script>

<style scoped>
.el-image {
  width: 200px;
  height: 300px;
}

.title {
  font-size: 30px;
}

.purchase-bar {
  margin-top: 50px;
}

.gray-text {
  color: #909399;
}

.collapse-box {
  width: 900px;
  margin-top: 100px;
}

.el-select {
  margin-right: 50px;
}

.purchase-btn {
  height: 50px;
  width: 150px;
  margin-left: 50px;
}

.price-text {
  font-weight: bold;
  font-size: 30px;
  color: #F56C6C;
  width: 150px;
}

.collapse-title {
  color: #337ecc;
}
</style>