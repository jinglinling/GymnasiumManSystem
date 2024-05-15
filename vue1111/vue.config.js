const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
/*module.exports={
  devServer:{
    open:true,
    host:'localhost',
    port:8080,
    proxy:{
      '/test':{
        
      }
    }
  }
}*/
