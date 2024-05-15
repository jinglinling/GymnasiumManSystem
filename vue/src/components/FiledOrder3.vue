<template>
    <div class="common-latout">
      <el-table :data="orderList" style="width: 100%">
        <el-table-column prop="SiteName" label="场地名称" />
        <el-table-column prop="Category" label="场地种类" />
        <el-table-column prop="End" label="订单状态" />
        <el-table-column label="使用">
            <template v-slot="scope">
                <el-button type="primary" v-on="postOrder(scope.$index)"/>
            </template>
        </el-table-column>
      </el-table>
    </div>
    </template>
    <style>
    .el-row {
      margin-bottom: 20px;
    }
    .el-row:last-child {
      margin-bottom: 0;
    }
    .el-col {
      border-radius: 4px;
    }
    
    .grid-content {
      border-radius: 4px;
      min-height: 36px;
    }
    </style>
    <script setup>
    import axios from 'axios'
    import{ref} from 'vue'
    const orderList=ref([{
      SiteName:"",
      Category:"",
      UserName:"",
      Date:"",
      rent:0,
      Id:"",
      End:""
    }])
    const param=ref({
      UserName:"test",
      status:"尚未开始"
    })
    async function postOrder(index){
        await axios.post('http://localhost:8080/FiledOrder/addOrder',orderList.value[index]).then(
            response=>{
                alert(response.data);
            }
        ).catch(error=>{console.error(error);})
    }
    async  function test3(){
      await axios.get('http://localhost:8080/FiledOrder/getOrderDelay',param.value)
      .then(response=>{
      for(var i=0;i<response.data.length;i++){
        orderList.value.push(response.data[i]);
      }
      }).catch(error=>{console.error(error);})
    }
    window.onload=test3()
    const debound=(fn,delay)=>{
    let time;
    return (...args)=>{
      if(time){
        clearTimeout(time)
      }
      time=setTimeout(()=>{fn(...args)},delay)
    }
    }
    const _m=window.ResizeObserver;
    window.ResizeObserver=class ResizeObserver extends _m{
    constructor(callback){
    callback=debound(callback,200);
    super(callback);
    }
    }
    </script>