import {createRouter, createWebHashHistory} from "vue-router";

import FrontPage from "@/components/FrontPage";
import ChartPage from "@/components/ChartPage";
import ReviewChartPage from "@/components/ReviewChartPage";
import LatestBookPage from "@/components/LatestBookPage";
import HottestSellersPage from "@/components/HottestSellersPage";
import ChartBoard from "@/components/ChartBoard";
import ProductPage from "@/components/ProductPage";
import MemberPage from "@/components/MemberPage";
import AccountCenterPage from "@/components/AccountCenterPage";
import AccountInfoPage from "@/components/AccountInfoPage";
import PasswordChangingPage from "@/components/PasswordChangingPage";
import AddressPage from "@/components/AddressPage";
import CollectionPage from "@/components/CollectionPage";
import OrderPage from "@/components/OrderPage";
import ReviewPage from "@/components/ReviewPage";
import SearchResultPage from "@/components/SearchResultPage";

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
        component: FrontPage,
    },
    {
        path: '/chart',
        component: ChartPage,
    },
    {
        path: '/review',
        component: ReviewChartPage,
    },
    {
        path: '/review/:id',
        component: ReviewPage,
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
    {
        path: '/member',
        redirect: '/member/home',
        component: MemberPage,
        children: [
            {path: 'home', component: AccountCenterPage},
            {path: 'account', component: AccountInfoPage},
            {path: 'password', component: PasswordChangingPage},
            {path: 'address', component: AddressPage},
            {path: 'collection', component: CollectionPage},
            {path: 'order', component: OrderPage},
        ]
    },
    {
        path: '/search/:content',
        component: SearchResultPage,
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
