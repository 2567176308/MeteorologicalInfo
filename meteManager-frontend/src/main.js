import './assets/main.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createApp } from 'vue'
import Index from './components/Index.vue'
const index = createApp(Index);
index.use(ElementPlus);
index.mount('#index');
