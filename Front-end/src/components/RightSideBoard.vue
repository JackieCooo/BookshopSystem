<template>
  <div v-if="info != null">
    <div>
      <h3>{{title}}</h3>
    </div>
    <el-menu text-color="#000000" active-text-color="#000000">
      <el-menu-item v-for="i in info" :key="i">
        <el-icon><caret-right /></el-icon>
        <p>{{textFormatting(i.title)}}</p>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import {CaretRight} from "@element-plus/icons-vue";

export default {
  name: "RightSideBoard",
  components: {
    CaretRight
  },
  props: {
    title: String
  },
  data() {
    return {
      info: null,
    }
  },
  async created() {
    await this.$http.get('api/news?num=10')
    .then((res) => {
      this.info = res.data.data
    })
    .catch((err) => {
      console.log(err)
    })
  },
  methods: {
    textFormatting(text) {
      return text.length > 15 ? text.substr(0, 20) + '...' : text
    }
  }
}
</script>

<style lang="less" scoped>
div > h3 {
  color: #337ecc;
  text-align: left;
  margin-left: 30px;
}

.el-menu-item:hover {
  color: #409EFF;
}

.el-menu-item {
  height: 35px;
}

.el-menu {
  border: none;
}
</style>