const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  runtimeCompiler: true,  // 运行时编译
  devServer: {
    proxy: 'http://localhost:8001'
  },
})
