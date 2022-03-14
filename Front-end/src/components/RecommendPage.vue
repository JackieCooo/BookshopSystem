<template>
  <div v-if="isDataOk">
    <el-space alignment="start">
      <el-space direction="vertical" :spacer="spacer">
        <el-space v-for="i in info" :key="i" class="box" :size="20">
          <el-image :src="''" fit="contain" style="width: 100px; height: 200px;"></el-image>
          <div>
            <div><h3>{{i.title}}</h3></div>
            <div><p>{{i.content}}</p></div>
            <el-space>
              <el-icon class="color-gray"><clock /></el-icon>
              <p class="color-gray space">{{i.date}}</p>
              <p class="color-gray">评论者：{{i.author}}</p>
            </el-space>
          </div>
        </el-space>
      </el-space>
      <RightSideBoard title="最新资讯"></RightSideBoard>
    </el-space>
  </div>
</template>

<script setup>
import {ElDivider} from "element-plus";
import {h} from "vue"

const spacer = h(ElDivider, { direction: 'horizontal' });
</script>

<script>
import {Clock} from "@element-plus/icons-vue";
import RightSideBoard from "@/components/RightSideBoard";

export default {
  name: "RecommendPage",
  components: {
    Clock,
    RightSideBoard
  },
  data() {
    return {
      isDataOk: false,
      info: null,
    }
  },
  async created() {
    await this.$http.get('/api/rev/list/')
    .then((res) => {
      this.info = res.data.data.list
      this.isDataOk = true
    })
    .catch((err) => {
      console.log(err)
    })
  }
}
</script>

<style scoped>
.box {
  border-radius: 10px;
  border: black;
  width: 700px;
}

div > h3, p {
  text-align: left;
}

.color-gray {
  color: #909399;
}

.space {
  margin-right: 20px;
}
</style>