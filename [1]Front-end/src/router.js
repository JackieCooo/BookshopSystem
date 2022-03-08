import {createRouter, createWebHashHistory} from "vue-router";

import FrontPage from "@/components/FrontPage";
import ChartPage from "@/components/ChartPage";
import RecommendPage from "@/components/RecommendPage";
import LatestBookPage from "@/components/LatestBookPage";
import HottestSellersPage from "@/components/HottestSellersPage";
import ProductPage from "@/components/ProductPage";

const routes = [
    {path: '/front', component: FrontPage},
    {path: '/', component: ProductPage},
    {path: '/chart', component: ChartPage},
    {path: '/recommend', component: RecommendPage},
    {path: '/latest', component: LatestBookPage},
    {path: '/hottest', component: HottestSellersPage},
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
