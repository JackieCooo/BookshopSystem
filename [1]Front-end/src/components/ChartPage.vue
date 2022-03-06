<template>
  <div>
    <el-space direction="vertical" :size="20">
      <DetailedChartBoard :info="hottestSeller"></DetailedChartBoard>
      <DetailedChartBoard :info="newestSeller"></DetailedChartBoard>
      <el-space :size="50">
        <BriefChartBoard :title="literatureChart.title" :src="literatureChart.src"></BriefChartBoard>
        <BriefChartBoard :title="historyChart.title" :src="historyChart.src"></BriefChartBoard>
        <BriefChartBoard :title="societyChart.title" :src="societyChart.src"></BriefChartBoard>
      </el-space>
      <el-space :size="50">
        <BriefChartBoard :title="novelChart.title" :src="novelChart.src"></BriefChartBoard>
        <BriefChartBoard :title="scienceChart.title" :src="scienceChart.src"></BriefChartBoard>
        <BriefChartBoard :title="artChart.title" :src="artChart.src"></BriefChartBoard>
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
          hasEBook: true,
          hasSecondhandBook: false,
        },
        bookInfo: [
          {id: 2, lastRank: 1, name: '置身事内：中国政府与经济发展', author: '兰小欢'},
          {id: 3, lastRank: 3, name: '也许你该找个人聊聊', author: '[美]洛莉·戈特利布'},
          {id: 4, lastRank: -1, name: '克拉拉与太阳', author: '[英]石黑一雄'},
          {id: 5, lastRank: -1, name: '工作、消费主义和新穷人', author: '[英]齐格蒙特·鲍曼'}
        ]
      },
      newestSeller: {
        title: '新书榜',
        firstBookInfo: {
          id: 1,
          lastRank: 2,
          name: '审美教育书简',
          author: '[德] 弗里德里希•席勒',
          picSrc: require('@/assets/bookChart/newestSeller.jpg'),
          time: '2022-1',
          publisher: '上海人民出版社',
          price: 56.00,
          hasEBook: true,
          hasSecondhandBook: true,
        },
        bookInfo: [
          {id: 2, lastRank: 1, name: '民族的神话：欧洲的中世纪起源', author: '[美]帕特里克·格里'},
          {id: 3, lastRank: 3, name: '乐园之丘：权力诞生与被剥夺的历史', author: '[荷]米尼克•希珀'},
          {id: 4, lastRank: -1, name: '崩溃：社会如何选择成败兴亡', author: '[美]贾雷德·戴蒙德'},
          {id: 5, lastRank: -1, name: '妇女、家庭与法律实践：清代以来的法律社会史', author: '赵刘洋'}
        ]
      },
      literatureChart: {
        title: '文学榜',
        src: require('@/assets/bookChart/literatureCover.jpg'),
      },
      novelChart: {
        title: '小说榜',
        src: require('@/assets/bookChart/novelCover.jpg'),
      },
      historyChart: {
        title: '历史榜',
        src: require('@/assets/bookChart/historyCover.jpg'),
      },
      societyChart: {
        title: '社会榜',
        src: require('@/assets/bookChart/societyCover.jpg'),
      },
      scienceChart: {
        title: '科学榜',
        src: require('@/assets/bookChart/scienceCover.jpg'),
      },
      artChart: {
        title: '艺术榜',
        src: require('@/assets/bookChart/artCover.jpg'),
      },
    }
  }
}
</script>

<style scoped>

</style>