import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import router from './router';
import 'element-plus/dist/index.css'
import axios from 'axios';
import store from './store'

const app = createApp(App)
axios.defaults.baseURL = 'http://localhost:8001'
app.config.globalProperties.$http = axios;

app.use(ElementPlus)
app.use(router)
app.use(store)
app.mount('#app')
