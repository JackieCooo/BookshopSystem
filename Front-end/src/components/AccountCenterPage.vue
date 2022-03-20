<template>
  <div>
    <el-space direction="vertical" alignment="start" :size="20">
      <el-space class="block border header" :size="50">
        <el-avatar :size="80" class="margin-left"></el-avatar>
        <h1>您好，{{getName}}</h1>
      </el-space>
      <el-space class="block" :size="10">
        <InfoCard icon="cart" title="购物车"></InfoCard>
        <InfoCard icon="wallet" title="待支付"></InfoCard>
        <InfoCard icon="box" title="未发货"></InfoCard>
        <InfoCard icon="star" title="已收藏"></InfoCard>
        <InfoCard icon="message" title="消息"></InfoCard>
      </el-space>
      <el-card class="block">
        <template #header>
          <el-row justify="space-between">
            <h3>基本信息</h3>
            <el-button type="text" >设置<el-icon class="el-icon--right"><setting /></el-icon></el-button>
          </el-row>
        </template>
        <el-descriptions :column="1">
          <el-descriptions-item label="1">1</el-descriptions-item>
          <el-descriptions-item label="1">1</el-descriptions-item>
          <el-descriptions-item label="1">1</el-descriptions-item>
          <el-descriptions-item label="1">1</el-descriptions-item>
          <el-descriptions-item label="1">1</el-descriptions-item>
        </el-descriptions>
      </el-card>
    </el-space>
  </div>
</template>

<script>
import InfoCard from "@/components/InfoCard";
import {Setting} from "@element-plus/icons-vue";

export default {
  name: "AccountCenterPage",
  components: {
    Setting,
    InfoCard,
  },
  data() {
    return {
      accountInfo: null,
    }
  },
  computed: {
    getName() {
      return this.$store.state.user.name
    }
  }
}
</script>

<style lang="less" scoped>
.block {
  width: 800px;
}

.border {
  border: 1px solid #E4E7ED;
  border-radius: 10px;
}

.header {
  height: 150px;
}

.margin-left {
  margin-left: 20px;
}
</style>