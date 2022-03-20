<template>
  <el-dialog v-model="showRegisterPage" :destroy-on-close="true" :width="400" top="200px" class="login-dialog">
    <el-space direction="vertical">
      <h1 class="login-title">用户注册</h1>
      <el-input placeholder="输入用户名" v-model="username" :prefix-icon="Avatar" class="input-box"></el-input>
      <el-input placeholder="输入密码" v-model="password" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
      <el-input placeholder="确认密码" v-model="confirmPassword" :show-password="true" :prefix-icon="Lock" class="input-box"></el-input>
      <el-button round type="primary" class="login-btn" @click="register">注册</el-button>
    </el-space>
  </el-dialog>
</template>

<script>
export default {
  name: "RegisterDialog",
  data() {
    return {
      showRegisterPage: false,
      rememberPassword: false,
      autoLogin: false,
      username: '',
      password: '',
      confirmPassword: ''
    }
  }
}
</script>

<style scoped>

</style>