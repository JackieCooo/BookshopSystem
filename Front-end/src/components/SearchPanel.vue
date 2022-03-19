<template>
  <el-row :gutter = "20" justify = "space-between">
    <el-col :span="18">
      <el-input placeholder="请输入书本名称" v-model="input" size="large">
        <template #append>
          <el-button><el-icon><Search /></el-icon></el-button>
        </template>
      </el-input>
    </el-col>
    <el-col :span = "6">
      <el-dropdown>
        <el-button type="primary" class="shop-cart-btn">
          <el-icon class="el-icon--left"><ShoppingCart /></el-icon>
          购物车
          <el-icon class="el-icon--right"><ArrowDownBold /></el-icon>
        </el-button>
        <template #dropdown>
          <div v-if="isCartEmpty" class="empty-cart">
            <el-empty description="购物车为空" :image-size="100"></el-empty>
          </div>
          <div v-else>
            <el-dropdown-menu>
              <el-dropdown-item>Action 1</el-dropdown-item>
              <el-dropdown-item>Action 2</el-dropdown-item>
              <el-dropdown-item>Action 3</el-dropdown-item>
              <el-dropdown-item>Action 4</el-dropdown-item>
              <el-dropdown-item>Action 5</el-dropdown-item>
            </el-dropdown-menu>
          </div>
        </template>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script>
import {Search, ArrowDownBold, ShoppingCart} from "@element-plus/icons-vue"

export default {
  name: "SearchPanel",
  data() {
    return {
      input : '',
      isCartEmpty: true,
    }
  },
  components: {
    Search,
    ArrowDownBold,
    ShoppingCart,
  }
}
</script>

<style lang="less" scoped>
.el-row {
  margin-top: 20px;
  height: 75px;
}

:deep(.el-input__inner) {
  border-color: var(--el-color-primary);
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  border-width: 3px;
}

:deep(.el-input__inner:hover) {
  border-color: var(--el-color-primary);
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  border-width: 3px;
}

:deep(.el-input-group__append) {
  border-color: var(--el-color-primary);
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
  border-width: 3px;
  background-color: var(--el-color-primary);
  color: white;
}

.shop-cart-btn {
  width: 120px;
  height: 40px;
  border-radius: 8px;
}

.empty-cart {
  width: 300px;
  height: 250px;
}
</style>