import {createRouter, createWebHashHistory} from "vue-router";

import FrontPage from "@/components/FrontPage";
import ChartPage from "@/components/ChartPage";
import RecommendPage from "@/components/RecommendPage";
import LatestBookPage from "@/components/LatestBookPage";
import HottestSellersPage from "@/components/HottestSellersPage";
import ChartBoard from "@/components/ChartBoard";
import ProductPage from "@/components/ProductPage";

const StyleChartBoard = {
    template: '<ChartBoard :type="$route.params.type"></ChartBoard>',
    components: {
        ChartBoard,
    }
}

const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        component: FrontPage
    },
    {
        path: '/chart',
        component: ChartPage
    },
    {
        path: '/recommend',
        component: RecommendPage
    },
    {
        path: '/latest',
        component: LatestBookPage
    },
    {
        path: '/hottest',
        redirect: '/hottest/all',
        component: HottestSellersPage,
        children: [
            {path: ':type', component: StyleChartBoard},
        ],
    },
    {
      path: '/book/:id',
      component: ProductPage,
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
