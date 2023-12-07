import { createRouter, createWebHashHistory ,RouteRecordRaw } from 'vue-router'

const routes:Array<RouteRecordRaw> = [
    //  {
    //     path: "/",
    //     component: ()=> import("../components/Header.vue"),
    // },
    {
        path: "/login",
        component: () => import("../components/Login.vue")
    },
    {
        path: "/register",
        component: ()=> import("../components/Register.vue")
    },
    {
        path: "/index",
        component: () => import("../components/Index.vue")
    },
    {
        path: "/",
        component: () => import("../components/Index.vue")
    },
    {
        path: "/header",
        component: ()=> import("../components/Header.vue")
    }
    
]



const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router