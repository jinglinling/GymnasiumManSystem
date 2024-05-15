<template>
 <div class="demo-datetime-picker">
    <div class="block">
      <span class="demonstration">场地预定</span>
      <el-date-picker
        v-model="value1"
        type="datetime"
        format="YYYY/MM/DD HH:mm:ss"
        time-format="HH"
        date-format="YYYY/MM/DD"
        placeholder="Select date and time"
      />
    </div>
    </div>
   
</template>
<script setup>
import axios from 'axios'
import{ref} from 'vue'
import moment from 'moment'
const filed=ref([
    {
        SiteName:"",
        Category:"",
        Usage:"",
        rent:""
    }
])
const FiledOrder=ref([{
 SiteName:"",
  Category:"",
  UserName:"",
  Date:"",
  rent:0,
  Id:"",
  End:""}
])
const value1 = ref('')
async function get(){
    await axios.get('http://localhost:8080/Filed/getAllorder',).then(response=>{
        for(var i=0;i<response.data.length;i++){
        const date1=moment();
        const date2=moment(convertDate(response.data[i].Date),"YYYY-MM-DD");
        if(date2.isAfter(date1)||date2.isSame(date1,"day")){
        FiledOrder.value.push(response.data[i]);}
  }
    }).catch(error=>{console.log(error)});
}
function convertDate(inputDate) {
    // 将输入日期字符串解析为 Date 对象
    const date = new Date(inputDate);

    // 检查是否成功解析日期
    if (!isNaN(date.getTime())) {
        // 获取年、月、日
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, "0");
        const day = date.getDate().toString().padStart(2, "0");

        // 拼接成 "YYYY-MM-DD" 格式
        const formattedDate = `${year}-${month}-${day}`;
        return formattedDate;
    } else {
        return "无效的日期格式";
    }
}
window.onload=get();
</script>
<style scoped lang="scss">
.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.demo-datetime-picker .block:last-child {
  border-right: none;
}
.demo-datetime-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
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