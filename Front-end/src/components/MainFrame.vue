<template>
  <el-container>
    <el-header>
    </el-header>
    <el-container>
      <el-main>
        <TapBar class="top-bar"></TapBar>
        <TabPage></TabPage>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import TapBar from "@/components/TopBar";
import TabPage from "@/components/TabPage";

export default {
  name: 'MainFrame',
  components : {
    TapBar,
    TabPage
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-container {
  height: 100%;
}

.el-header {
  background-color: #1f1f1f;
  height: 40px;
}

.el-main {
  text-align: center;
  /*height: 100%;*/
  /*margin: 0;*/
  /*padding: 0;*/
}

.top-bar {
  /*background-color: gray;*/
}
</style>
