<template>
  <div>
    <el-space direction="vertical" alignment="start" :size="20">
      <PageHeader>
        <template #title>
          <span class="title">收货地址</span>
        </template>
        <template #button>
          <el-button type="text" @click="showAddPage = true"><el-icon class="el-icon--left"><plus /></el-icon>新增</el-button>
        </template>
      </PageHeader>

      <el-dialog v-model="showAddPage" :width="400" top="200px">
        <el-space direction="vertical" :size="20">
          <span>修改/增加收件地址</span>
          <el-space>
            <span>收件人: </span>
            <el-input></el-input>
          </el-space>
          <el-space>
            <span>地址: </span>
            <el-input></el-input>
          </el-space>
          <el-space>
            <span>电话: </span>
            <el-input></el-input>
          </el-space>
          <el-button type="primary" class="save-btn">保存</el-button>
        </el-space>
      </el-dialog>

      <el-table border :data="info">
        <el-table-column prop="receiver" label="收件人" :width="100"></el-table-column>
        <el-table-column prop="address" label="收件地址" :width="350"></el-table-column>
        <el-table-column prop="phone" label="电话" :width="150"></el-table-column>
        <el-table-column label="操作" :width="200">
          <template #default>
            <el-button @click="showAddPage = true">修改</el-button>
            <el-button type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-space>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";
import {Plus} from "@element-plus/icons-vue";

export default {
  name: "AddressPage",
  components: {
    Plus,
    PageHeader,
  },
  data() {
    return {
      showAddPage: true,
      info: [
        {receiver: 'Tom', address: 'none', phone: '10000000000'}
      ]
    }
  }
}
</script>

<style scoped>
.title {
  font-weight: bold;
}

.save-btn {
  width: 200px;
}
</style>