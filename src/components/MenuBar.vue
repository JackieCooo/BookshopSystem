<template>
  <el-menu active-text-color="#90b2f2" background-color="#ffffff" default-active="1" text-color="#000000">
    <el-scrollbar>
      <el-menu-item v-for="i in bookCategory" :key="i">
        <span>{{i.name}}</span>
      </el-menu-item>
    </el-scrollbar>
  </el-menu>
</template>

<script>
export default {
  name: "MenuBar"
}
</script>

<script setup>
const bookCategory = [
  {id: 1, name: "马列主义毛邓思想"},
  {id: 2, name: "哲学"},
  {id: 3, name: "社会科学总论"},
  {id: 4, name: "政治\\法律"},
  {id: 5, name: "军事"},
  {id: 6, name: "经济"},
  {id: 7, name: "文化\\科学\\教育\\体育"},
  {id: 8, name: "语言\\文字"},
  {id: 9, name: "文学"},
  {id: 10, name: "艺术"},
  {id: 11, name: "历史\\地理"},
  {id: 12, name: "自然科学总论"},
  {id: 13, name: "数理科学和化学"},
  {id: 14, name: "天文学\\地球科学"},
  {id: 15, name: "生物科学"},
  {id: 16, name: "医药\\卫生"},
  {id: 17, name: "农业科学"},
  {id: 18, name: "工业技术"},
  {id: 19, name: "交通运输"},
  {id: 20, name: "航空\\航天"},
  {id: 21, name: "环境科学\\安全科学"},
  {id: 22, name: "综合性图书"}
]

</script>

<style scoped>
.el-menu-item {
  height: 35px;
}
</style>