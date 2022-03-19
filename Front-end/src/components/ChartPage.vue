<template>
  <div>
    <el-space direction="vertical" :size="20">
      <DetailedChartBoard title="畅销榜" type="all"></DetailedChartBoard>
      <DetailedChartBoard title="新书榜" type="new"></DetailedChartBoard>
      <el-space :size="50">
        <BriefChartBoard title="文学榜" type="literary"></BriefChartBoard>
        <BriefChartBoard title="小说榜" type="novel"></BriefChartBoard>
        <BriefChartBoard title="历史榜" type="history"></BriefChartBoard>
      </el-space>
      <el-space :size="50">
        <BriefChartBoard title="社会榜" type="social"></BriefChartBoard>
        <BriefChartBoard title="科学榜" type="tech"></BriefChartBoard>
        <BriefChartBoard title="艺术榜" type="art"></BriefChartBoard>
      </el-space>
    </el-space>
  </div>
</template>

<script>
import DetailedChartBoard from "@/components/DetailedChartBoard";
import BriefChartBoard from "@/components/BriefChartBoard";

export default {
  name: "ChartPage",
  components: {
    DetailedChartBoard,
    BriefChartBoard,
  },
  data() {
    return {
      isDataOk: true,
    }
  },
  methods: {

  },
}
</script>

<style scoped>

</style>