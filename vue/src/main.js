import { createApp } from 'vue'
import {createRouter} from 'vue-router'
import { createWebHistory } from 'vue-router'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import moment from 'moment'
import UserSelect from'./components/UserSelect.vue'
const routes=[{path:'/user/:username/:password/:method',component:UserSelect}]
const router=createRouter({
    history:createWebHistory(),
    routes:routes
})
moment.locale('zh-cn')
const app=createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
