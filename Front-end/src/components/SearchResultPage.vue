<template>
  <div v-if="info != null">
    <el-space direction="vertical" class="container">
      <el-space v-for="i in info" :key="i" alignment="start" :size="20">
        <el-image fit="fill" :src="loadPic(i.id)"></el-image>
        <el-space direction="vertical" alignment="start">
          <span class="title" @click="loadProduct(i.id)">{{i.name}}</span>
          <span class="brief">{{i.author}} / {{i.publisher}} / {{i.date}} / {{i.price}}元</span>
        </el-space>
      </el-space>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "SearchResultPage",
  data() {
    return {
      info: null,
    }
  },
  async created() {
    const content = this.$route.params.content
    await this.$http.get(encodeURI('api/search?text=' + content.toString()))
    .then((res) => {
      this.info = res.data.data
    })
    .catch((err) => {
      console.log(err)
    })
  },
  methods: {
    loadPic(id) {
      return "http://localhost:8001/api/book/pic/" + id.toString()
    },
    loadProduct(id) {
      this.$router.push('/book/' + id.toString())
    },
  },
}
</script>

<style lang="less" scoped>
.el-image {
  width: 150px;
  height: 200px;
}

.container {
  width: 800px;
  margin-top: 20px;
}

.title {
  font-weight: bold;
  font-size: 20px;
  margin-top: 10px;
}

.brief {
  color: #909399;
  margin-top: 20px;
}
</style>