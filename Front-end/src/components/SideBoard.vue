<template>
  <div class="box-border" v-if="info != null">
    <el-space direction="vertical" :size="0">
      <h3>热销TOP榜</h3>
      <el-menu active-text-color="#000000">
        <el-menu-item v-for="i in 10" :key="i" @click="loadProduct(info[i-1].id)">
          <el-space>
            <div :style="{'color': i === 1 ? 'red' : (i === 2 ? 'orangered' : (i === 3 ? 'orange' : ''))}"><b>{{i}}</b></div>
            <div>{{titleFormatting(info[i-1].name)}}</div>
          </el-space>
        </el-menu-item>
      </el-menu>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "SideBoard",
  data() {
    return {
      info: null,
    }
  },
  methods: {
    titleFormatting(str) {
      return str.length > 10 ? str.substr(0, 10) + '...' : str
    },
    loadProduct(id) {
      this.$router.push('/book/' + id.toString())
    }
  },
  async created() {
    await this.$http.get('/api/chart/all?detail=3')
    .then((res) => {
      this.info = res.data.data
    })
    .catch((err) => {
      console.log(err)
    })
  }
}
</script>

<style scoped>
.box-border {
  border-radius: 10px;
  width: 250px;
  border: 1px solid #E4E7ED;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}

.el-menu-item {
  height: 45px;
  width: 250px;
}

.el-menu-item:hover {
  color: #409EFF;
}

.el-menu:last-child {
  margin-bottom: 20px;
}
</style>