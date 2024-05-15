<template>
     <Transition name="fade">
    <span>
    {{ user.username }}
    </span>
</Transition>
<Transition name="fade">
    <span>
    {{ user.password }}
    </span>
</Transition>
<Transition name="fade">
    <span>
        {{route.params.method}}
    </span>
</Transition>
</template>
<script setup>
import {onMounted, ref, watchEffect}from'vue'
import axios from 'axios'
import { useRoute} from 'vue-router'
const route=useRoute();
const user=ref({
    username:"default",
    password:12312321
})
async function fetch(){
     await axios.get('http://localhost:8080/test/user').then(response=>{
        user.value=response.data
    }).catch(error=>{console.error(error);})
}
async function post(){
    user.value.username=route.params.username;
    user.value.password=route.params.password;
     await axios.post('http://localhost:8080/test/test2',user.value).then(response=>{
       alert(response.data)
    }).catch(error=>{console.error(error);})
}

 onMounted(()=>{
    watchEffect(async ()=>{
if(route.params.method=="get"){
    fetch();
    console.log('获取数据');
}
if(route.params.method=="post"){
    post();
    console.log('传输数据');
}
})})
</script>
<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>