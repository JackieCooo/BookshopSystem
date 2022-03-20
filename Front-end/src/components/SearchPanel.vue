<template>
  <div>
    <el-space :size="50">
      <el-input placeholder="请输入书本名称" v-model="input" class="search-box">
        <template #append>
          <el-button @click="search"><el-icon><Search /></el-icon></el-button>
        </template>
      </el-input>
      <el-dropdown @visible-change="showCart">
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
              <el-dropdown-item v-for="i in cartInfo" :key="i" class="list-item">
                <el-space alignment="start">
                  <el-image fit="fill" :src="loadPic(i.id)" class="item-img"></el-image>
                  <el-space alignment="start" direction="vertical">
                    <span class="title">{{i.name}}</span>
                    <span class="brief">{{i.author}} / {{i.publisher}} / {{i.date}}</span>
                    <span class="price-quantity">¥ {{i.price}}    x{{i.quantity}}</span>
                  </el-space>
                </el-space>
              </el-dropdown-item>
            </el-dropdown-menu>
          </div>
        </template>
      </el-dropdown>
    </el-space>
  </div>
</template>

<script>
import {Search, ArrowDownBold, ShoppingCart} from "@element-plus/icons-vue"

export default {
  name: "SearchPanel",
  data() {
    return {
      input : '',
      cartInfo: null,
    }
  },
  components: {
    Search,
    ArrowDownBold,
    ShoppingCart,
  },
  methods: {
    async showCart(e) {
      if (e === true && this.isLogin) {
        await this.$http.get('user/cart/' + this.$store.state.user.id.toString())
        .then((res) => {
          this.cartInfo = res.data.data
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },
    loadPic(id) {
      return "http://localhost:8001/api/book/pic/" + id.toString()
    },
    search() {
      this.$router.push('/search/' + this.input)
    },
  },
  computed: {
    isCartEmpty() {
      return this.cartInfo === null || this.cartInfo.length === 0
    },
    isLogin() {
      return this.$store.state.isLogin
    },
  },
}
</script>

<style lang="less" scoped>
.search-box {
  width: 500px;
  height: 40px;
}

:deep(.el-input__inner) {
  border-color: var(--el-color-primary);
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  border-width: 3px;
  height: 40px;
}

:deep(.el-input__inner:hover) {
  border-color: var(--el-color-primary);
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  border-width: 3px;
  height: 40px;
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

.item-img {
  width: 80px;
  height: 100px;
}

.title {
  color: #303133;
  font-weight: bold;
}

.brief {
  color: #909399;
}

.price-quantity {
  color: #303133;
  font-size: 20px;
}

.list-item:hover {
  background-color: #409EFF;
}
</style>