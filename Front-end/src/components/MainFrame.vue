<template>
  <el-container>
    <el-header>
      <WindowHeader></WindowHeader>
    </el-header>
    <el-scrollbar>
      <el-container>
        <el-main>
          <TapBar></TapBar>
          <TabPage></TabPage>
        </el-main>
      </el-container>
    </el-scrollbar>
  </el-container>
</template>

<script>
import TapBar from "@/components/TopBar";
import TabPage from "@/components/TabPage";
import WindowHeader from "@/components/WindowHeader";

export default {
  name: 'MainFrame',
  components : {
    TapBar,
    TabPage,
    WindowHeader,
  }
}
</script>

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
  /*margin: 0;*/
  /*padding: 0;*/
}

</style>
