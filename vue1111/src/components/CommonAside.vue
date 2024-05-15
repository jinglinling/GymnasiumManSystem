<template>
    <el-menu
      default-active="1-4-1"
      class="el-menu-vertical-demo"               
      :collapse="isCollapse"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :router= "true">
      <h3>{{isCollapse?'标题':'后台管理系统'}}</h3>
      <!-- 没有下拉列表的菜单选项 -->
      <el-menu-item v-for="item in noChildren" :key="item.path" :index="item.path+''">
        <span slot="title">{{item.label}}</span>
      </el-menu-item>
    <!-- 有下拉列表的菜单选项 -->
      <el-submenu v-for="item in hasChildren" :key="item.path" :index="item.path+''">
        <template slot="title">
          <span slot="title">{{item.label}}</span>
        </template>
        <el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
          <el-menu-item :index="subItem.path">{{subItem.label}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

    </el-menu>
  </template>
   
  <script>
  export default {
    name: "HelloWorld",
    props: {
      msg: String,
    },
    data() {
      return {
        menu: [
          {
            path: '/', 
            // 组件的路由
            name: 'home',
             //组件的名称
            label: '首页',
            //选项的名字
            url: 'Home/Home'
            //地址栏
          },
          {
            label: '其他',
            //以下为下拉列表的选项
            children: [
              {
                path: '/page01',
                name: 'page01',
                label: '页面1',
                url: 'Other/PageOne'
              },
              {
                path: '/page02',
                name: 'page02',
                label: '页面2',
                url: 'Other/PageTwo'
              }]
          }
        ]
      }
    },
    computed: {
      noChildren(){
        return this.menu.filter(item => !item.children)
      },
      hasChildren(){
        return this.menu.filter(item => item.children)
      },
      isCollapse(){
        return this.$store.state.isCollapse;
      }
    }
  }
  </script>
   
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style lang="less" scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 100vh;
    border: none;
  }
  .el-menu {
     border: none;
  }
  .el-menu-item-group__title {
      padding: 0; 
  }
   
  h3{
      color: aliceblue;
      line-height: 30px; 
  }
   
  span,.el-menu-item{
    font-size: 16px;
  }
   
  </style>