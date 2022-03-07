<template>
  <MainFrame></MainFrame>
</template>

<script>
import MainFrame from "@/components/MainFrame";

export default {
  name: 'App',
  components: {
    MainFrame
  }
}
</script>

<style>
html, body, #app {
  height:100%;
  margin: 0;
  padding: 0;
  background-color: #ffffff;
}
</style>
