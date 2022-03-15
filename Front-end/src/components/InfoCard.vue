<template>
  <el-card shadow="hover" class="box">
    <el-space direction="vertical">
      <el-icon v-if="icon === 'cart'" :size="iconSize"><shopping-cart /></el-icon>
      <el-icon v-else-if="icon === 'box'" :size="iconSize"><box /></el-icon>
      <el-icon v-else-if="icon === 'star'" :size="iconSize"><star /></el-icon>
      <el-icon v-else-if="icon === 'wallet'" :size="iconSize"><wallet /></el-icon>
      <el-icon v-else-if="icon === 'message'" :size="iconSize"><message /></el-icon>
      <span>{{title}}</span>
      <h1>{{data}}</h1>
    </el-space>
  </el-card>
</template>

<script>
import {ShoppingCart, Box, Star, Wallet, Message} from "@element-plus/icons-vue";

export default {
  name: "InfoCard",
  components: {Message, Wallet, Star, Box, ShoppingCart},
  props: {
    icon: String,
    title: String,
    data: {
      type: Number,
      default: 0,
    }
  },
  data() {
    return {
      iconSize: 40,
    }
  }
}
</script>

<style lang="less" scoped>
.box {
  width: 150px;
  height: 150px;
  border-radius: 10px;
}
</style>