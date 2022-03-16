<template>
  <div>
    <el-row justify="space-between">
      <el-space spacer="|" :size="15">
        <el-button type="text" @click="gotoPage('/front')">首页</el-button>
        <el-button type="text" @click="gotoPage('/recommend')">精选书评</el-button>
        <el-button type="text" @click="gotoPage('/chart')">排行榜</el-button>
        <el-button type="text" @click="gotoPage('/latest')">新品上架</el-button>
        <el-button type="text" @click="gotoPage('/hottest')">畅销推荐</el-button>
      </el-space>
      <el-space v-if="isLogin" spacer="|" :size="15">
        <div>您好，{{userName}}</div>
        <el-dropdown>
          <div class="drop-down">会员中心<el-icon class="el-icon--right"><arrow-down /></el-icon></div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>我的订单</el-dropdown-item>
              <el-dropdown-item>我的收藏</el-dropdown-item>
              <el-dropdown-item>收货地址</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <el-badge type="text" :is-dot="true">消息</el-badge>
        <el-button type="text" @click="logout">登出</el-button>
      </el-space>
      <el-space v-else spacer="|" :size="15">
        <el-button type="text">登录</el-button>
        <el-button type="text">注册</el-button>
      </el-space>
    </el-row>
  </div>
</template>

<script>
import {ArrowDown} from "@element-plus/icons-vue";

export default {
  name: "WindowHeader",
  data() {
    return {
      userName: 'Jackie',
    }
  },
  components: {
    ArrowDown,
  },
  methods: {
    gotoPage(site) {
      this.$router.push(site)
    },
    logout() {
      this.$store.commit('changeLoginState')
    }
  },
  computed: {
    isLogin() {
      return this.$store.state.isLogin
    }
  }
}
</script>

<style lang="less" scoped>
.el-space {
  color: #909399;
}

.el-button {
  margin-top: 5px;
  color: #909399;
}

.el-button:hover {
  color: white;
}

.el-row {
  margin-left: 50px;
  margin-right: 50px;
}

.el-badge:hover {
  color: white;
}

.drop-down {
  color: #909399;
}

.drop-down:hover {
  color: white;
}
</style>