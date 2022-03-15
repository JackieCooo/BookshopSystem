<template>
  <div>
    <el-space direction="vertical" alignment="start" :size="20">
      <PageHeader>
        <template #title>
          <h4>修改个人账户资料</h4>
        </template>
      </PageHeader>
      <el-space direction="vertical" :size="30" class="content" alignment="start">
        <el-descriptions :column="1">
          <el-descriptions-item label="用户名:">
            <el-input v-model="username" class="input-box"></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="手机号:">
            <el-input v-model="phone" class="input-box"></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="邮箱:">
            <el-input v-model="mailbox" class="input-box"></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="性别:">
            <el-radio-group v-model="gender">
              <el-radio :label="1">男</el-radio>
              <el-radio :label="2">女</el-radio>
            </el-radio-group>
          </el-descriptions-item>
        </el-descriptions>
        <el-button type="primary">保存</el-button>
      </el-space>
    </el-space>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";

export default {
  name: "AccountInfoPage",
  components: {
    PageHeader,
  },
  data() {
    return {
      username: '',
      phone: '',
      mailbox: '',
      gender: 1,
    }
  }
}
</script>

<style lang="less" scoped>
.input-box {
  width: 250px;
}

.content {
  border: 1px solid #E4E7ED;
  width: 800px;
  padding: 20px 20px 20px 20px;
}
</style>