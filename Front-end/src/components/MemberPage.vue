<template>
  <div>
    <el-space :size="20" alignment="start">
      <el-menu :router="true">
        <el-menu-item index="/member"><h1>会员中心</h1></el-menu-item>
        <el-sub-menu index="account-center">
          <template #title>
            <h1>账户中心</h1>
          </template>
          <el-menu-item index="/member/account">账户信息</el-menu-item>
          <el-menu-item index="/member/password">修改密码</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="transaction-management">
          <template #title>
            <h1>交易管理</h1>
          </template>
          <el-menu-item index="/member/collection">我的收藏</el-menu-item>
          <el-menu-item index="/member/address">收货地址</el-menu-item>
          <el-menu-item index="/member/order">订单信息</el-menu-item>
        </el-sub-menu>
      </el-menu>

      <div class="content-page">
        <router-view></router-view>
      </div>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "MemberPage",
  data() {
    return {

    }
  }
}
</script>

<style lang="less" scoped>
.el-menu {
  width: 200px;
  height: 800px;
}
</style>