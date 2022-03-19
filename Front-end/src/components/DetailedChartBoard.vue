<template>
  <div class="box-style" v-if="info != null && firstBook != null">
    <el-card :body-style="{ padding: '0px' }" shadow="hover">
      <el-row justify="space-between">
        <div class="container">
          <el-image :src="loadPic(firstBook[0].id)" fit="cover" class="chart-cover"></el-image>
          <div><h1 class="chart-title">{{title}}</h1></div>
          <div><p class="subtitle">查看全部 ></p></div>
        </div>
        <el-space direction="vertical" :size="0">
          <el-descriptions>
            <template #title>
              <el-space>
                <div :style="{color: 'red'}">1</div>
<!--                <RankChangeIcon :change="rankStatus(info.firstBookInfo.id, info.firstBookInfo.lastRank)"></RankChangeIcon>-->
                <div>《{{firstBook[0].name}}》</div>
              </el-space>
            </template>
            <el-descriptions-item label="作者">{{firstBook[0].author}}</el-descriptions-item>
            <el-descriptions-item label="出版时间">{{firstBook[0].date}}</el-descriptions-item>
            <el-descriptions-item label="出版社">{{firstBook[0].publisher}}</el-descriptions-item>
            <el-descriptions-item label="价格">{{firstBook[0].price}}元</el-descriptions-item>
            <el-descriptions-item v-if="firstBook[0].hasEBook || firstBook[0].hasSecondhandBook">
              <el-space>
                <el-tag v-if="firstBook[0].hasEBook" effect="dark">电子书</el-tag>
                <el-tag v-if="firstBook[0].hasSecondhandBook" effect="dark">二手书</el-tag>
              </el-space>
            </el-descriptions-item>
          </el-descriptions>
          <el-menu>
            <el-menu-item v-for="i in 4" :key="i">
              <el-space>
                <div :style="{'color': i+1 === 2 ? 'orangered' : (i+1 === 3 ? 'orange' : '')}">{{i+1}}</div>
<!--                <RankChangeIcon :change="rankStatus(i.id, i.lastRank)"></RankChangeIcon>-->
                <div>《{{info[i].name}}》</div>
                <div :style="{color: '#606266'}">{{info[i].author}}</div>
              </el-space>
            </el-menu-item>
          </el-menu>
        </el-space>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "DetailedChartBoard",
  components: {
  },
  data() {
    return {
      info: null,
      firstBook: null,
    }
  },
  props: {
    title: String,
    type: String,
  },
  // 组件创建时请求数据
  async created() {
    await this.$http.get('/api/chart/' + this.type + '?detail=2&num=5')
    .then((res) => {
      this.info = res.data.data
      console.log(this.info)
    })
    .catch((err) => {
      console.log(err)
    })
    await this.$http.get('/api/chart/' + this.type + '?num=1')
    .then((res) => {
      this.firstBook = res.data.data
      console.log(this.firstBook)
    })
    .catch((err) => {
      console.log(err)
    })
  },
  methods: {
    rankStatus(now, pre) {
      if (pre === -1) return 'new'
      let val = pre - now
      if (val > 0) return 'up'
      else if (val < 0) return 'down'
      else if (val === 0) return 'none'
    },
    // 加载封面图
    loadPic(id) {
      return "http://localhost:8001/api/book/pic/" + id.toString()
    },
  },
}
</script>

<style lang="less" scoped>
.el-menu-item {
  height: 30px;
  border-top: 1px solid #E4E7ED;
}

.el-menu {
  border: none;
  width: 640px;
  margin-right: 5px;
}

.chart-cover {
  height: 250px;
  width: 250px;
  border-radius: 10px;
  filter: brightness(30%);
}

.el-card {
  border-radius: 10px;
  width: 900px;
}

.el-descriptions {
  width: 640px;
  margin-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
}

.el-descriptions:hover {
  background-color: #EBEEF5;

  :deep(.el-descriptions__body) {
    background-color: #EBEEF5;
  }
}

.chart-title {
  position: absolute;
  top: 35px;
  width: 100%;
  text-align: center;
  font-size: 50px;
  color: white;
}

.container {
  position: relative;
}

.container:hover .subtitle{
  opacity: 1;
}

.subtitle {
  position: absolute;
  bottom: 10px;
  width: 100%;
  text-align: center;
  color: white;
  opacity: 0;
  transition: .5s ease;
}

</style>