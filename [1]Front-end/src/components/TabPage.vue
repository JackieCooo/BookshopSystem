<template>
  <div class="test">
    <el-row :gutter="0" class="tab-bar" justify="space-around">
      <el-button type="text" @click="gotoPage(1)" class="tab-btn"><h3>首页</h3></el-button>
      <el-button type="text" @click="gotoPage(2)" class="tab-btn"><h3>热门书评</h3></el-button>
      <el-button type="text" @click="gotoPage(3)" class="tab-btn"><h3>排行榜</h3></el-button>
      <el-button type="text" @click="gotoPage(4)" class="tab-btn"><h3>畅销推荐</h3></el-button>
      <el-button type="text" @click="gotoPage(5)" class="tab-btn"><h3>新品上市</h3></el-button>
    </el-row>
    <div class="view">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "TabPage",
  components : {
  },
  data() {
    return {
      thisPage: 1,
    }
  },
/*
  created() {
    this.$router.replace('/')
  },
*/
  methods: {
    gotoPage(name) {
      switch (name) {
        case 1:
          this.$router.push('/')
            this.thisPage = 1
          break
        case 2:
          this.$router.push('/recommend')
          this.thisPage = 2
          break
        case 3:
          this.$router.push('/chart')
          this.thisPage = 3
          break
        case 4:
          this.$router.push('/hottest')
          this.thisPage = 4
          break
        case 5:
          this.$router.push('/latest')
          this.thisPage = 5
          break
      }
    }
  }
}
</script>

<style scoped>
.tab-btn {
  color: black;
  font-family: "PingFang SC", sans-serif;
  font-size: 15px;
  margin-top: 10px;
}

.tab-bar {
  border-bottom: 3px solid #DCDFE6;
  padding-left: 350px;
  padding-right: 350px;
}

.view {
  padding-top: 30px;
}

</style>