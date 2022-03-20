import {createStore} from 'vuex';

const store = createStore({
    state: {
        isLogin: false,
        baseUrl: 'http://192.168.3.154:8001/',
        user: {
            id: null,
            name: null,
            phone: null,
            mail: null,
            gender: null,
            password: null,
        },
    },
    mutations: {
        login(state, user) {
            state.user.id = user.id
            state.user.name = user.name
            state.user.phone = user.phone
            state.user.gender = user.gender
            state.user.mail = user.mail
            state.user.password = user.password
            state.isLogin = true
        },
        logout(state) {
            state.user.id = null
            state.user.name = null
            state.user.phone = null
            state.user.gender = null
            state.user.mail = null
            state.user.password = null
            state.isLogin = false
        }
    },
})

export default store
