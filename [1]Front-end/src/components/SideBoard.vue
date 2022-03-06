<template>
  <div class="box-border">
    <el-space direction="vertical" :size="0">
      <h3>热销TOP榜</h3>
      <el-menu active-text-color="#000000">
        <el-menu-item v-for="i in bookList" :key="i">
          <el-space>
            <div :style="{'color': i.id === 1 ? 'red' : (i.id === 2 ? 'orangered' : (i.id === 3 ? 'orange' : ''))}"><b>{{i.id}}</b></div>
            <div>{{titleFormatting(i.name)}}</div>
          </el-space>
        </el-menu-item>
      </el-menu>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "SideBoard",
  data() {
    return {
      bookList: [
        {id: 1, name: "《自在独行：贾平凹的独行世界》"},
        {id: 2, name: "《岛上书店》"},
        {id: 3, name: "《这么慢，那么美》"},
        {id: 4, name: "《解忧杂货店》"},
        {id: 5, name: "《丝绸之路：一部全新的世界史》"},
        {id: 6, name: "《无声告白》"},
        {id: 7, name: "《天才在左 疯子在右》"},
        {id: 8, name: "《巨人的陨落》"},
        {id: 9, name: "《月亮和六便士》"},
        {id: 10, name: "《你心柔软,却有力量》"}
      ]
    }
  },
  methods: {
    titleFormatting(str) {
      return str.length > 10 ? str.substr(0, 10) + '...' : str
    }
  },
}
</script>

<style scoped>
.box-border {
  border-radius: 10px;
  border: 1px solid var(--el-border-color-base);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 250px;
}

.el-menu-item {
  height: 45px;
  width: 250px;
}

.el-menu-item:hover {
  color: #409EFF;
}

.el-menu:last-child {
  margin-bottom: 20px;
}
</style>