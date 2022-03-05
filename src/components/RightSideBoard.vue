<template>
  <div>
    <div>
      <h3>{{title}}</h3>
    </div>
    <el-menu text-color="#000000" active-text-color="#000000">
      <el-menu-item v-for="i in newsContent" :key="i">
        <el-icon><caret-right /></el-icon>
        <p>{{i}}</p>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import {CaretRight} from "@element-plus/icons-vue";

export default {
  name: "RightSideBoard",
  components: {
    CaretRight
  },
  props: {
    title: String
  },
  data() {
    return {
      newsContent: [
        '《天津百座建筑中的百年党史》出版',
        '茅奖大家们解决你这个春天的“书荒”',
        '日新无已 望如朝曙',
        '《新时代提案工作丛书》出版',
        '《中国藏医药影印古籍珍本》（61卷—70卷）即将面世',
        '2021年《环球科学》最美科学阅读',
        '《辞海》缩印本获特别奖　17种沪版图书解读上海城市精神',
        '中央党校出版社推出《大别山红色故事》',
      ]
    }
  }
}
</script>

<style scoped>
div > h3 {
  color: #337ecc;
  text-align: left;
  margin-left: 30px;
}

.el-menu-item:hover {
  color: #409EFF;
}

.el-menu-item {
  height: 35px;
}

.el-menu {
  border: none;
}
</style>