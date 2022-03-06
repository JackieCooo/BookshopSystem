<template>
  <div>
    <el-space alignment="start">
      <el-tabs type="card" v-model="activeName">
        <el-tab-pane label="全部" name="1">
          <ChartBoard :info="allChart"></ChartBoard>
        </el-tab-pane>
        <el-tab-pane label="文学" name="2">
          <ChartBoard :info="literatureChart"></ChartBoard>
        </el-tab-pane>
        <el-tab-pane label="小说" name="3">

        </el-tab-pane>
        <el-tab-pane label="历史文化" name="4">

        </el-tab-pane>
        <el-tab-pane label="社会纪实" name="5">

        </el-tab-pane>
        <el-tab-pane label="科学" name="6">

        </el-tab-pane>
        <el-tab-pane label="艺术" name="7">

        </el-tab-pane>
      </el-tabs>
      <RightSideBoard title="最新资讯"></RightSideBoard>
    </el-space>
  </div>
</template>

<script setup>
import {ref} from 'vue'

const activeName = ref('1');
</script>

<script>
import ChartBoard from "@/components/ChartBoard";
import RightSideBoard from "@/components/RightSideBoard";

export default {
  name: "HottestSellersPage",
  data() {
    return {
      allChart: {
        bookInfo: [
          {id: 1, name: '从零开始的女性主义', pic: require('@/assets/hottestSellers/all/01.jpg'), author: '[日]上野千鹤子/[日]田房永子', time: '2021-9', publisher: '北京联合出版公司', price: 52.00, hasEBook: true, hasSecondhandBook: false},
          {id: 2, name: '置身事内：中国政府与经济发展', pic: require('@/assets/hottestSellers/all/02.jpg'), author: '兰小欢', time: '2021-8', publisher: '上海人民出版社', price: 65.00, hasEBook: true, hasSecondhandBook: false},
          {id: 3, name: '也许你该找个人聊聊', pic: require('@/assets/hottestSellers/all/03.jpg'), author: '[美]洛莉·戈特利布', time: '2021-7', publisher: ' 果麦文化/上海文化出版社', price: 68.00, hasEBook: true, hasSecondhandBook: false},
          {id: 4, name: '克拉拉与太阳', pic: require('@/assets/hottestSellers/all/04.jpg'), author: '[英]石黑一雄', time: '2021-3', publisher: '上海译文出版社', price: 68.00, hasEBook: true, hasSecondhandBook: false},
          {id: 5, name: '工作、消费主义和新穷人', pic: require('@/assets/hottestSellers/all/05.jpg'), author: '[英]齐格蒙特·鲍曼', time: '2021-9', publisher: '上海社会科学院出版社', price: 45.00, hasEBook: false, hasSecondhandBook: false},
          {id: 6, name: '素食者', pic: require('@/assets/hottestSellers/all/06.jpg'), author: '[韩]韩江', time: '2021-9', publisher: '四川文艺出版社', price: 48.00, hasEBook: true, hasSecondhandBook: false},
          {id: 7, name: '鹿川有许多粪', pic: require('@/assets/hottestSellers/all/07.jpg'), author: '李沧东', time: '2021-8', publisher: '武汉大学出版社', price: 58.00, hasEBook: false, hasSecondhandBook: false},
          {id: 8, name: '索拉里斯星', pic: require('@/assets/hottestSellers/all/08.jpg'), author: '[波]斯坦尼斯瓦夫·莱姆', time: '2021-8', publisher: '译林出版社', price: 49.00, hasEBook: true, hasSecondhandBook: false},
          {id: 9, name: '生死疲劳', pic: require('@/assets/hottestSellers/all/09.jpg'), author: '莫言', time: '2022-1', publisher: '浙江文艺出版社', price: 69.90, hasEBook: true, hasSecondhandBook: false},
          {id: 10, name: '文城', pic: require('@/assets/hottestSellers/all/10.jpg'), author: '余华', time: '2021-3', publisher: '北京十月文艺出版社', price: 59.00, hasEBook: false, hasSecondhandBook: false}
        ]
      },
      literatureChart: {
        bookInfo: [
          {id: 1, name: '成为波伏瓦', pic: require('@/assets/hottestSellers/literature/01.jpg'), author: '[英]凯特·柯克帕特里克', time: '2021-3', publisher: '中信出版社', price: 78.00, hasEBook: true, hasSecondhandBook: false},
          {id: 2, name: '重走：在公路、河流和驿道上寻找西南联大', pic: require('@/assets/hottestSellers/literature/02.jpg'), author: '杨潇', time: '2021-5', publisher: '上海文艺出版社', price: 98.00, hasEBook: false, hasSecondhandBook: false},
          {id: 3, name: '微尘', pic: require('@/assets/hottestSellers/literature/03.jpg'), author: '陈年喜', time: '2021-5', publisher: ' 天津人民出版社', price: 58.00, hasEBook: true, hasSecondhandBook: false},
          {id: 4, name: '午夜降临前抵达', pic: require('@/assets/hottestSellers/literature/04.jpg'), author: '刘子超', time: '2021-8', publisher: '文汇出版社', price: 59.00, hasEBook: true, hasSecondhandBook: false},
          {id: 5, name: '读库2201', pic: require('@/assets/hottestSellers/literature/05.jpg'), author: '张立宪', time: '2021-12', publisher: '新星出版社', price: 42.00, hasEBook: false, hasSecondhandBook: false},
          {id: 6, name: '读库2200', pic: require('@/assets/hottestSellers/literature/06.jpg'), author: '张立宪', time: '2021-12', publisher: '新星出版社', price: 42.00, hasEBook: false, hasSecondhandBook: false},
          {id: 7, name: '读水浒：人性的十三种刻度', pic: require('@/assets/hottestSellers/literature/07.jpg'), author: '押沙龙', time: '2022-1', publisher: '三秦出版社', price: 49.80, hasEBook: false, hasSecondhandBook: false},
          {id: 8, name: '俗女养成记', pic: require('@/assets/hottestSellers/literature/08.jpg'), author: '江鹅', time: '2021-10', publisher: '北京日报出版社', price: 48.00, hasEBook: true, hasSecondhandBook: false},
          {id: 9, name: '一切境', pic: require('@/assets/hottestSellers/literature/09.jpg'), author: '庆山', time: '2021-11', publisher: '人民文学出版社', price: 66.00, hasEBook: true, hasSecondhandBook: false},
          {id: 10, name: '我将宇宙随身携带：佩索阿诗集：佩索阿诗集', pic: require('@/assets/hottestSellers/literature/10.jpg'), author: '[葡]费尔南多·佩索阿', time: '2021-6', publisher: '雅众文化｜北京联合出版公司', price: 58.00, hasEBook: false, hasSecondhandBook: false}
        ]
      }
    }
  },
  components: {
    ChartBoard,
    RightSideBoard,
  }
}
</script>

<style scoped>
.el-tabs {
  margin-top: 30px;
}
</style>