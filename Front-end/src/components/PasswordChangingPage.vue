<template>
  <div>
    <el-space direction="vertical" alignment="start" :size="20">
      <PageHeader>
        <template #title>
          <h4>修改密码</h4>
        </template>
      </PageHeader>
      <el-space direction="vertical" :size="20" alignment="start" class="content">
        <el-space>
          <span>原密码: </span>
          <el-input v-model="oldPassword"></el-input>
        </el-space>
        <el-space>
          <span>新密码: </span>
          <el-input v-model="newPassword"></el-input>
        </el-space>
        <el-space>
          <span>确认新密码: </span>
          <el-input v-model="confirmPassword"></el-input>
        </el-space>
        <el-button type="primary" class="btn">确认修改</el-button>
      </el-space>
    </el-space>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";

export default {
  name: "PasswordChangingPage",
  components: {
    PageHeader,
  },
  data() {
    return {
      oldPassword: '',
      newPassword: '',
      confirmPassword: '',
    }
  }
}
</script>

<style lang="less" scoped>
.content {
  width: 800px;
  border: 1px solid #E4E7ED;
  padding: 20px 20px 20px 20px;
}
</style>