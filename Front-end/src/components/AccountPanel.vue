<template>
  <div v-if="isLogin" class="account-panel">
    <el-space direction="vertical" :size="0" :spacer="spacer">
      <div>
        <p>欢迎回来</p>
        <el-avatar :size="50" fit="fill" shape="circle" :icon="Avatar"></el-avatar>
      </div>
      <el-button round :icon="SwitchButton" class="normal-btn" @click="logout">登出</el-button>
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
              <el-icon :size="30" color="#409eff"><user-filled /></el-icon>
              <p>会员中心</p>
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
      <el-button round class="normal-btn" @click="showLoginPage = true">登录</el-button>

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

      <el-button round class="normal-btn" @click="showRegisterPage = true">注册</el-button>

      <el-dialog v-model="showRegisterPage" :destroy-on-close="true" :width="400" top="200px" class="login-dialog">
        <el-space direction="vertical">
          <h1 class="login-title">用户注册</h1>
          <el-input placeholder="输入用户名" v-model="username" :prefix-icon="Avatar" class="input-box"></el-input>
          <el-input placeholder="输入密码" v-model="password" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
          <el-input placeholder="确认密码" v-model="confirmPassword" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
          <el-button round type="primary" class="login-btn">注册</el-button>
        </el-space>
      </el-dialog>

    </el-space>
  </div>
</template>

<script setup>
import {Avatar, SwitchButton, Tickets, Star, UserFilled, Clock, Lock} from "@element-plus/icons-vue";
import {h} from 'vue'
import {ElDivider} from 'element-plus'

const spacer = h(ElDivider, {direction: 'horizontal'})
</script>

<script>
export default {
  name: "AccountPanel",
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
  methods: {
    async login() {
      await this.$http.get('/user/login?username=' + this.username + '&password=' + this.password)
      .then((res) => {
        console.log(res.data.code, typeof res.data.code)
        if (res.data.code === '0') {  // 登录成功
          this.$store.commit('changeLoginState')
          this.showLoginPage = false
        }
      })
      .catch((err) => {
        console.log(err)
      })
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
.rounded-button {
  height: 80px;
  width: 80px;
}

.normal-btn {
  width: 150px;
}

.login-dialog {
  border-radius: 50px;
}

.el-input {
  width: 250px;
  margin-bottom: 10px;
}

:deep(.el-input__inner) {
  border-radius: 20px;
}

.login-title {
  font-size: 25px;
}

.login-btn {
  width: 250px;
  font-weight: bold;
}
</style>