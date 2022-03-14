<template>
  <div class="box-style">
    <el-card :body-style="{ padding: '0px' }" shadow="hover">
      <el-row justify="space-between">
        <div class="container">
          <el-image :src="info.coverUrl" fit="cover" class="chart-cover"></el-image>
          <div><h1 class="chart-title">{{title}}</h1></div>
          <div><p class="subtitle">查看全部 ></p></div>
        </div>
        <el-space direction="vertical" :size="0">
          <el-descriptions>
            <template #title>
              <el-space>
                <div :style="{color: 'red'}">1</div>
<!--                <RankChangeIcon :change="rankStatus(info.firstBookInfo.id, info.firstBookInfo.lastRank)"></RankChangeIcon>-->
                <div>《{{info.chart[0].name}}》</div>
              </el-space>
            </template>
            <el-descriptions-item label="作者">{{info.chart[0].author}}</el-descriptions-item>
            <el-descriptions-item label="出版时间">{{info.date}}</el-descriptions-item>
            <el-descriptions-item label="出版社">{{info.publisher}}</el-descriptions-item>
            <el-descriptions-item label="价格">{{info.price}}元</el-descriptions-item>
            <el-descriptions-item v-if="info.hasEBook || info.hasSecondhandBook">
              <el-space>
                <el-tag v-if="info.hasEBook" effect="dark">电子书</el-tag>
                <el-tag v-if="info.hasSecondhandBook" effect="dark">二手书</el-tag>
              </el-space>
            </el-descriptions-item>
          </el-descriptions>
          <el-menu>
            <el-menu-item v-for="i in num" :key="i">
              <el-space>
                <div :style="{'color': i === 2 ? 'orangered' : (i === 3 ? 'orange' : '')}">{{i}}</div>
<!--                <RankChangeIcon :change="rankStatus(i.id, i.lastRank)"></RankChangeIcon>-->
                <div>《{{info.chart[i].name}}》</div>
                <div :style="{color: '#606266'}">{{info.chart[i].author}}</div>
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
      num: [1, 2, 3, 4],
      info: {
        chart: [
          {id: 0, name: '', author: ''},
          {id: 0, name: '', author: ''},
          {id: 0, name: '', author: ''},
          {id: 0, name: '', author: ''},
          {id: 0, name: '', author: ''},
        ],
        coverUrl: '',
        date: '',
        publisher: '',
        price: 0,
        hasEBook: false,
        hasSecondhandBook: false
      },
    }
  },
  props: {
    title: String,
    type: String,
  },
  async created() {
    await this.$http.get('/api/board/' + this.type)
    .then((res) => {
      this.info = res.data.data
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
    }
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