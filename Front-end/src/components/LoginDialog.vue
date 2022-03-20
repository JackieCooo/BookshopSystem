<template>
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
</template>

<script setup>
import {Avatar, Lock} from "@element-plus/icons-vue";

</script>

<script>
import {ElMessageBox} from 'element-plus'

export default {
  name: "LoginDialog",
  data() {
    return {
      showLoginPage: false,
      rememberPassword: false,
      autoLogin: false,
      username: '',
      password: '',
    }
  },
  methods: {
    // 登录操作
    async login() {
      await this.$http.get('/user/login?username=' + this.username + '&password=' + this.password)
      .then((res) => {
        const user = res.data.data
        const code = user.data.code
        // 登录成功
        if (code === '0') {
          this.$store.commit('login', user)
          this.showLoginPage = false
        }
        // 用户名错误
        else if (code === '101') {
          ElMessageBox.alert('用户名不存在', '提示', {
            confirmButtonText: '确定',
            showClose: false,
            roundButton: true,
          })
        }
        // 密码错误
        else if (code === '100') {
          ElMessageBox.alert('密码错误', '提示', {
            confirmButtonText: '确定',
            showClose: false,
            roundButton: true,
          })
        }
      })
      .catch((err) => {
        console.log(err)
      })
    },
  }
}
</script>

<style lang="less" scoped>
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