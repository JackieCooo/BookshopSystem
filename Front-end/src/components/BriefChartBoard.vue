<template>
  <div class="container">
    <el-image :src="picUrl" fit="cover" class="chart-cover"></el-image>
    <div><h1 class="chart-title">{{title}}</h1></div>
    <div><p class="subtitle">查看更多 ></p></div>
  </div>
</template>

<script>
export default {
  name: "BriefChartBoard",
  data() {
    return {
      picUrl: '',
    }
  },
  props: {
    title: String,
    type: String,
  },
  async created() {
    await this.$http.get('/api/chart/' + this.type + '/?detailed=0')
    .then((res) => {
      this.picUrl = res.data.data.coverUrl
    })
    .catch((err) => {
      console.log(err)
    })
  },
}
</script>

<style scoped>
.chart-cover {
  height: 250px;
  width: 250px;
  border-radius: 10px;
  filter: brightness(30%);
}

.chart-title {
  position: absolute;
  top: 35px;
  width: 100%;
  text-align: center;
  font-size: 50px;
  color: white;
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

.container:hover .subtitle{
  opacity: 1;
}

.container {
  position: relative;
}
</style>