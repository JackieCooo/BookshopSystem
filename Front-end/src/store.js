import {createStore} from 'vuex';

const store = createStore({
    state: {
        isLogin: false,
    },
    mutations: {
        changeLoginState(state) {
            state.isLogin = !state.isLogin
        }
    },
})

export default store
