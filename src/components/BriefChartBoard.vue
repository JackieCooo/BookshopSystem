<template>
  <div class="container">
    <el-image :src="src" fit="cover"></el-image>
    <h1 class="chart-title">{{title}}</h1>
    <p class="subtitle">查看更多 ></p>
  </div>
</template>

<script>
export default {
  name: "BriefChartBoard",
  props: {
    title: String,
    src: String,
  }
}
</script>

<style scoped>
.el-image {
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
</style>