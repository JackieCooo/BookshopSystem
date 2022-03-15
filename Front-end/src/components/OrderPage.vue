<template>
  <div>
    <el-space>
      <PageHeader>
        <template #title>
          <span class="title">订单管理</span>
        </template>
      </PageHeader>
      <el-table>
        <el-table-column type="expend">
          <template #default="props">
            <span>《{{props.row.name}}》/{{props.row.author}}/{{props.row.publisher}}/{{props.row.date}}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-space>
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader";

export default {
  name: "OrderPage",
  components: {
    PageHeader,
  },
  data() {
    return {
      info: null,
    }
  }
}
</script>

<style scoped>
.title {
  font-weight: bold;
}
</style>