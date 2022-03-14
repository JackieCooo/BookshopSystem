<template>
  <div class="container">
    <el-space alignment="start">
      <ChartBoard type="new"></ChartBoard>
      <RightSideBoard title="最新资讯"></RightSideBoard>
    </el-space>
  </div>
</template>

<script>
import RightSideBoard from "@/components/RightSideBoard";
import ChartBoard from "@/components/ChartBoard";

export default {
  name: "LatestBookPage",
  data() {
    return {

    }
  },
  components: {
    RightSideBoard,
    ChartBoard
  }
}
</script>

<style scoped>
.container {
  margin-top: 50px;
}
</style>