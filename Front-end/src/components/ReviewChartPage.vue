<template>
  <div v-if="info != null">
    <el-space alignment="start">
      <el-space direction="vertical" :spacer="spacer">
        <el-space v-for="i in info" :key="i" class="box" :size="20">
          <el-image :src="loadPic(i.bookId)" fit="contain" style="width: 100px; height: 200px;"></el-image>
          <div>
            <div @click="loadReview(i.id)"><h3>{{i.title}}</h3></div>
            <div><p>{{i.intro}}</p></div>
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
  name: "ReviewChartPage",
  components: {
    Clock,
    RightSideBoard
  },
  data() {
    return {
      info: null,
    }
  },
  async created() {
    await this.$http.get('/api/review/chart')
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
    loadReview(id) {
      this.$router.push('/review/' + id.toString())
    }
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