<template>
  <!--排名上升图标-->
  <div v-if="change === 'up'">
    <el-icon :style="{color: '#F56C6C'}"><caret-top /></el-icon>
  </div>
  <!--排名下降图标-->
  <div v-else-if="change === 'down'">
    <el-icon :style="{color: '#409EFF'}"><caret-bottom /></el-icon>
  </div>
  <!--排名持平图标-->
  <div v-else-if="change === 'none'">
    <el-icon :style="{color: '#909399'}"><semi-select /></el-icon>
  </div>
  <!--新入榜图标-->
  <div v-else-if="change === 'new'">
    <p class="new-text">new</p>
  </div>
</template>

<script>
import {CaretTop} from "@element-plus/icons-vue";
import {CaretBottom} from "@element-plus/icons-vue";
import {SemiSelect} from "@element-plus/icons-vue";

export default {
  name: "RankChangeIcon",
  components: {
    CaretTop,
    CaretBottom,
    SemiSelect,
  },
  props: {
    change: {
      type: String,
      default: 'none',
      validator(value) {
        return ['up', 'down', 'none', 'new'].includes(value)
      }
    },
  }
}
</script>

<style scoped>
.new-text {
  color: #67C23A;
  font-family: Arial, serif;
  font-size: 5px;
}
</style>