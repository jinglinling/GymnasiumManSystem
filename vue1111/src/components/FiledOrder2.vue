<template>
<div class="common-latout">
  <el-table :data="orderList" style="width: 100%">
    <el-table-column prop="SiteName" label="场地名称" />
    <el-table-column prop="Category" label="场地种类" />
    <el-table-column prop="End" label="订单剩余时间" />
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
  status:"进行中"
})
function formatSeconds(value) {
  let secondTime = parseInt(value);
  let minuteTime = 0;
  let hourTime = 0;
  if (secondTime >= 60) {
    minuteTime = parseInt(secondTime / 60);
    secondTime = parseInt(secondTime % 60);
    if (minuteTime >= 60) {
      hourTime = parseInt(minuteTime / 60);
      minuteTime = parseInt(minuteTime % 60);
    }
  }
  hourTime = hourTime < 10 ? "0" + hourTime : hourTime;
  minuteTime = minuteTime < 10 ? "0" + minuteTime : minuteTime;
  secondTime = secondTime < 10 ? "0" + secondTime : secondTime;
  let res = hourTime + ":" + minuteTime + ":" + secondTime;
  return res;
}//将秒数转换为HH:MM:SS格式
function formatHMS(temp){
  const [hours, minutes, seconds] = temp.split(':');
 temp = Number(hours) * 60 * 60 + Number(minutes) * 60 + Number(seconds);
 return temp;
}//将HH:MM:SS转换为秒数
function cutdown2(){
  for(var i=0;i<orderList.value.length;i++){
    if(orderList.value[i].End!="已结束"){
  const temp=formatHMS(orderList.value[i].End);
  if(temp==0){
   orderList.value[i].End="已结束";
  }else if(temp>0){
   orderList.value[i].End=formatSeconds(temp-1);
  }
  }
  }
}
setInterval(cutdown2,1000);
async  function test3(){
  await axios.get('http://localhost:8080/FiledOrder/getOrderDelay',param.value)
  .then(response=>{
  for(var i=0;i<response.data.length;i++){
    response.data[i].End=formatSeconds(response,data[i].End);
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