<template>
  <div>
    <el-row justify="space-between">
      <el-space spacer="|" :size="15">
        <el-button type="text" @click="gotoPage('/home')">首页</el-button>
        <el-button type="text" @click="gotoPage('/review')">精选书评</el-button>
        <el-button type="text" @click="gotoPage('/chart')">排行榜</el-button>
        <el-button type="text" @click="gotoPage('/latest')">新品上架</el-button>
        <el-button type="text" @click="gotoPage('/hottest')">畅销推荐</el-button>
      </el-space>
      <el-space v-if="isLogin" spacer="|" :size="15">
        <div>您好，{{getName}}</div>
        <el-dropdown>
          <div class="drop-down">会员中心<el-icon class="el-icon--right"><arrow-down /></el-icon></div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="gotoPage('/member/order')">我的订单</el-dropdown-item>
              <el-dropdown-item @click="gotoPage('/member/collection')">我的收藏</el-dropdown-item>
              <el-dropdown-item @click="gotoPage('/member/address')">收货地址</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <el-badge type="text" :is-dot="true">消息</el-badge>
        <el-button type="text" @click="logout">登出</el-button>
      </el-space>
      <el-space v-else spacer="|" :size="15">
        <el-button type="text" @click="showLoginPage = true">登录</el-button>
        <el-button type="text" @click="showRegisterPage = true">注册</el-button>
      </el-space>
    </el-row>

    <el-dialog v-model="showLoginPage" :destroy-on-close="true" :width="400" top="200px" class="login-dialog">
      <el-space direction="vertical">
        <h1 class="login-title">欢迎登录</h1>
        <el-input placeholder="输入用户名" v-model="username" :prefix-icon="Avatar" class="input-box"></el-input>
        <el-input placeholder="输入密码" v-model="password" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
        <el-space :size="30">
          <el-checkbox v-model="rememberPassword" label="记住密码" size="large"></el-checkbox>
          <el-checkbox v-model="autoLogin" label="自动登录" size="large"></el-checkbox>
        </el-space>
        <el-button round type="primary" class="login-btn" @click="login">登录</el-button>
      </el-space>
    </el-dialog>

    <el-dialog v-model="showRegisterPage" :destroy-on-close="true" :width="400" top="200px" class="login-dialog">
      <el-space direction="vertical">
        <h1 class="login-title">用户注册</h1>
        <el-input placeholder="输入用户名" v-model="username" :prefix-icon="Avatar" class="input-box"></el-input>
        <el-input placeholder="输入密码" v-model="password" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
        <el-input placeholder="确认密码" v-model="confirmPassword" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
        <el-button round type="primary" class="login-btn" @click="register">注册</el-button>
      </el-space>
    </el-dialog>

  </div>
</template>

<script setup>
import {Avatar, Lock} from "@element-plus/icons-vue";
</script>

<script>
import {ArrowDown} from "@element-plus/icons-vue";

export default {
  name: "WindowHeader",
  data() {
    return {
      showLoginPage: false,
      showRegisterPage: false,
      rememberPassword: false,
      autoLogin: false,
      username: '',
      password: '',
      confirmPassword: ''
    }
  },
  components: {
    ArrowDown,
  },
  methods: {
    // 登录操作
    async login() {
      await this.$http.get('/user/login?username=' + this.username + '&password=' + this.password)
          .then((res) => {
            const user = res.data.data
            console.log(user)
            if (res.data.code === '0') {  // 登录成功
              this.$store.commit('login', user)
              this.showLoginPage = false
            }
          })
          .catch((err) => {
            console.log(err)
          })
    },
    gotoPage(site) {
      this.$router.push(site)
    },
    logout() {
      this.$store.commit('logout')
    },
    // 注册操作
    async register() {
      await this.$http.get('/user/reg?username=' + this.username + '&password=' + this.password)
      .then((res) => {
        const code = res.data.code
        if (code === '0') {
          const data = res.data.data
          this.$store.commit('login', data)
        } else if (code === '102') {
          console.log(code)
        }
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },
  computed: {
    isLogin() {
      return this.$store.state.isLogin
    },
    getName() {
      return this.$store.state.user.name
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