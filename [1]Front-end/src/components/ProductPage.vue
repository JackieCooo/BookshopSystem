<template>
  <div>
    <el-space direction="vertical" alignment="start" :size="0">
      <h1 class="title">{{name}}</h1>
      <el-space alignment="start" size="large">
        <el-image :src="pic" fit="contain"></el-image>
        <el-space direction="vertical" alignment="start" class="description" size="large">
          <div><span class="gray-text">作者: </span>{{author}}</div>
          <div><span class="gray-text">出版社: </span>{{publisher}}</div>
          <div><span class="gray-text">出版时间: </span>{{time}}</div>
          <div><span class="gray-text">价格: </span>{{price}}</div>
          <div><span class="gray-text">ISBN: </span>{{isbnCode}}</div>
        </el-space>
      </el-space>
      <el-space class="purchase-bar">
        <span class="gray-text">选择类型: </span>
        <el-select v-model="bookType">
          <el-option label="全新整书" :value="1"></el-option>
          <el-option label="二手书" :value="2" :disabled="!hasSecondhandBook"></el-option>
          <el-option label="电子书" :value="3" :disabled="!hasEBook"></el-option>
        </el-select>
        <span class="gray-text">选择数量: </span>
        <el-input-number v-model="bookNum" :min="1"></el-input-number>
      </el-space>
      <el-space class="purchase-bar">
        <div class="price-text">¥ {{getPrice}}</div>
        <el-button type="warning" class="purchase-btn"><h3>加入购物车</h3></el-button>
        <el-button type="danger"  class="purchase-btn"><h3>立即购买</h3></el-button>
      </el-space>
      <el-collapse v-model="collapseNum" class="collapse-box">
        <el-collapse-item :name="1">
          <template #title>
            <h3 class="collapse-title">内容简介</h3>
          </template>
          <div>{{bookIntroduction}}</div>
        </el-collapse-item>
        <el-collapse-item :name="2">
          <template #title>
            <h3 class="collapse-title">作者简介</h3>
          </template>
          <div>{{authorIntroduction}}</div>
        </el-collapse-item>
        <el-collapse-item :name="3">
          <template #title>
            <h3 class="collapse-title">目录</h3>
          </template>
          <div>{{bookCatalogue}}</div>
        </el-collapse-item>
        <el-collapse-item :name="4">
          <template #title>
            <h3 class="collapse-title">评论</h3>
          </template>
          <div></div>
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
/*
  props: {
    info: {
      name: String,
      pic: String,
      author: String,
      time: String,
      publisher: String,
      price: String,
      hasEBook: Boolean,
      isbnCode: Number,
      hasSecondhandBook: Boolean,
    }
  }
*/
  data() {
    return {
      name: '全沉浸末日脚本',
      pic: require('@/assets/testFile/product.jpg'),
      author: '翟永明',
      time: '2022-3',
      publisher: '辽宁人民出版社',
      price: 52.00,
      isbnCode: 9787205103064,
      hasEBook: true,
      hasSecondhandBook: true,
      bookIntroduction: '在著名诗人翟永明的最新一部诗集中，她以巨大的激情和坚硬的灵魂质地，在持续创作中找寻全新的冲突，找寻诗歌新的白夜，将世界变形为一颗巨大的灵魂。全书共收录56首诗，诗人从壮丽宇宙、科幻剧集，写到戏剧、艺术家，再到出游、阅读，由个人经历推及人类困境，在星空与大地、日常与哲思之间轻盈游走，用文字织出一幅当代生活的深邃图景。',
      authorIntroduction: '1981年开始发表诗歌作品，1984年完成组诗《女人》，被誉为“女性诗歌”在中国的发轫与代表作。1998年在成都开“白夜”酒吧，策划、举办一系列跨领域文化活动，经营至今。著有诗集《女人》《在一切玫瑰之上》《称之为一切》《终于使我周转不灵》《十四首素歌》《随黄公望游富春山》等九种，诗文集数种。作品被译为英语、法语、荷兰语、意大利语、西班牙语、德语并出版。',
      bookCatalogue: '03　全沉浸末日脚本\n' +
          '07　德洛丽丝的梦\n' +
          '11　永生是什么\n' +
          '14　奇点临近\n' +
          '16　一个无边的路由器',
      totalPrice: 0,
      bookNum: 1,
      bookType: '全新整书',
    }
  },
  methods: {
  },
  computed: {
    getPrice() {
      switch (this.bookType) {
        case '全新整书':
          return this.bookNum * this.price
        case '二手书':
          return this.bookNum * this.price
        case '电子书':
          return this.bookNum * this.price
        default: return 0
      }
    }
  },
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