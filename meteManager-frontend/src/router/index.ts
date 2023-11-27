import { createRouter, createWebHashHistory ,RouteRecordRaw } from 'vue-router'

const routes:Array<RouteRecordRaw> = [
     {
        path: "/",
        component: ()=> import("../components/Header.vue"),
        children: [
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
                component: () => import("../components/CloudShow.vue")
            }
        ]
    }
    
]



const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router