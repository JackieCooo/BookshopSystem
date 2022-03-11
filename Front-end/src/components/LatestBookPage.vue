<template>
  <div class="container">
    <el-space alignment="start">
      <ChartBoard :info="latestBooks"></ChartBoard>
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
      latestBooks:{
        bookInfo: [
          {id: 1, name: '审美教育书简', pic: require('@/assets/newestBooks/01.jpg'), author: '[德] 弗里德里希•席勒', time: '2022-1', publisher: '上海人民出版社', price: 56.00, hasEBook: true, hasSecondhandBook: false},
          {id: 2, name: '民族的神话：欧洲的中世纪起源', pic: require('@/assets/newestBooks/02.jpg'), author: '[美]帕特里克·格里', time: '2022-2', publisher: '广西师范大学出版社', price: 64.00, hasEBook: false, hasSecondhandBook: false},
          {id: 3, name: '乐园之丘：权力诞生与被剥夺的历史', pic: require('@/assets/newestBooks/03.jpg'), author: '[荷]米尼克•希珀', time: '2022-3', publisher: '广西师范大学出版社', price: 68.00, hasEBook: false, hasSecondhandBook: false},
          {id: 4, name: '崩溃：社会如何选择成败兴亡', pic: require('@/assets/newestBooks/04.jpg'), author: '[美]贾雷德·戴蒙德', time: '2022-1', publisher: '中信出版集团', price: 118.00, hasEBook: true, hasSecondhandBook: false},
          {id: 5, name: '妇女、家庭与法律实践：清代以来的法律社会史', pic: require('@/assets/newestBooks/05.jpg'), author: '赵刘洋', time: '2022-2', publisher: '广西师范大学出版社', price: 68.00, hasEBook: false, hasSecondhandBook: false},
          {id: 6, name: '潮汐图', pic: require('@/assets/newestBooks/06.jpg'), author: '林棹', time: '2022-1', publisher: '上海文艺出版社', price: 58.00, hasEBook: false, hasSecondhandBook: false},
          {id: 7, name: '德勒兹论文学', pic: require('@/assets/newestBooks/07.jpg'), author: '（美）罗纳德·博格', time: '2022-1', publisher: '南京大学出版社', price: 66.00, hasEBook: false, hasSecondhandBook: false},
          {id: 8, name: '本巴', pic: require('@/assets/newestBooks/08.jpg'), author: '刘亮程', time: '2022-1', publisher: '译林出版社', price: 59.00, hasEBook: false, hasSecondhandBook: false},
          {id: 9, name: '不止读书', pic: require('@/assets/newestBooks/09.jpg'), author: '魏小河', time: '2022-1', publisher: '上海文艺出版社', price: 58.00, hasEBook: true, hasSecondhandBook: false},
          {id: 10, name: '熊会滑雪吗？', pic: require('@/assets/newestBooks/10.jpg'), author: '[英] 雷蒙德·安特罗伯斯 著 / [英] 波莉·邓巴 绘', time: '2022-2', publisher: '成都时代出版社', price: 52.00, hasEBook: false, hasSecondhandBook: false}
        ]
      }
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