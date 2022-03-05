<template>
  <el-row :gutter = "20" justify = "space-between">
    <el-col :span="18">
      <el-input placeholder="请输入书本名称" v-model="input" size="large">
        <template #append>
          <el-button :icon="Search" type="primary" class="search-btn"></el-button>
        </template>
      </el-input>
    </el-col>
    <el-col :span = "6">
      <el-dropdown size="large">
        <el-button type="primary">
          购物车
          <el-icon class="el-icon--right"><ArrowDownBold /></el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>Action 1</el-dropdown-item>
            <el-dropdown-item>Action 2</el-dropdown-item>
            <el-dropdown-item>Action 3</el-dropdown-item>
            <el-dropdown-item>Action 4</el-dropdown-item>
            <el-dropdown-item>Action 5</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script setup>
import {Search, ArrowDownBold} from "@element-plus/icons-vue"
</script>

<script>
export default {
  name: "SearchPanel",
  data() {
    return {
      input : ''
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-top: 20px;
  height: 75px;
}

.search-btn {
  background-color: #409eff;
}
</style>