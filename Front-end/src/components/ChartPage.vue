<template>
  <div>
    <el-space direction="vertical">
      <DetailedChartBoard :info="hottestSeller"></DetailedChartBoard>
    </el-space>
  </div>
</template>

<script>
import DetailedChartBoard from "@/components/DetailedChartBoard";

export default {
  name: "ChartPage",
  components: {
    DetailedChartBoard
  },
  data() {
    return {
      hottestSeller: {
        title: '畅销榜',
        firstBookInfo: {
          id: 1,
          lastRank: 2,
          name: '从零开始的女性主义',
          author: '[日]上野千鹤子/[日]田房永子',
          picSrc: require('@/assets/bookChart/bestSeller.jpg'),
          time: '2021-9',
          publisher: '北京联合出版公司',
          price: 52.00,
          hasEBook: true
        },
        bookInfo: [
          {id: 2, lastRank: 1, name: '置身事内：中国政府与经济发展', author: '兰小欢'},
          {id: 3, lastRank: 3, name: '也许你该找个人聊聊', author: '[美]洛莉·戈特利布'},
          {id: 4, lastRank: -1, name: '克拉拉与太阳', author: '[英]石黑一雄'},
          {id: 5, lastRank: -1, name: '工作、消费主义和新穷人', author: '[英]齐格蒙特·鲍曼'}
        ]
      }
    }
  }
}
</script>

<style scoped>

</style>