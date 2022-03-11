<template>
  <div v-if="isLogin" class="account-panel">
    <el-space direction="vertical" :size="0" :spacer="spacer">
      <div>
        <p>欢迎回来</p>
        <el-avatar :size="50" fit="fill" shape="circle" :icon="Avatar"></el-avatar>
      </div>
      <el-button round :icon="SwitchButton" class="normal-btn">登出</el-button>
      <el-space :size="10" direction="vertical">
        <el-space :size="10">
          <el-button round class="rounded-button">
            <el-space direction="vertical" :size="0">
              <el-icon :size="30" color="#409eff"><tickets /></el-icon>
              <p>我的订单</p>
            </el-space>
          </el-button>
          <el-button round class="rounded-button">
            <el-space direction="vertical" :size="0">
              <el-icon :size="30" color="#409eff"><star /></el-icon>
              <p>我的收藏</p>
            </el-space>
          </el-button>
        </el-space>
        <el-space :size="10">
          <el-button round class="rounded-button">
            <el-space direction="vertical" :size="0">
              <el-icon :size="30" color="#409eff"><shop /></el-icon>
              <p>收藏店铺</p>
            </el-space>
          </el-button>
          <el-button round class="rounded-button">
            <el-space direction="vertical" :size="0">
              <el-icon :size="30" color="#409eff"><clock /></el-icon>
              <p>我的足迹</p>
            </el-space>
          </el-button>
        </el-space>
      </el-space>
    </el-space>
  </div>
  <div v-else>
    <el-space direction="vertical" :size="10">
      <div>
        <p>Hi, 你好</p>
        <el-avatar :size="50" fit="fill" shape="circle" :icon="Avatar"></el-avatar>
      </div>
      <el-button round class="normal-btn">登录</el-button>
      <el-button round class="normal-btn">注册</el-button>
    </el-space>
  </div>
</template>

<script setup>
import {Avatar, SwitchButton, Tickets, Star, Shop, Clock} from "@element-plus/icons-vue";
import {h} from 'vue'
import {ElDivider} from 'element-plus'

const isLogin = false;
const spacer = h(ElDivider, {direction: 'horizontal'})
</script>

<script>
export default {
  name: "AccountPanel"
}
</script>

<style scoped>
.rounded-button {
  height: 80px;
  width: 80px;
}

.normal-btn {
  width: 150px;
}
</style>