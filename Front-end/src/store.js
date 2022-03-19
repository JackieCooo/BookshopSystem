import {createStore} from 'vuex';

const store = createStore({
    state: {
        isLogin: false,
        user: {
            userId: null,
            name: null,
            phone: null,
            mail: null,
            gender: null,
            password: null,
        },
    },
    mutations: {
        changeLoginState(state) {
            state.isLogin = !state.isLogin
        },
        storeUserInfo(state, user) {
            state.user.userId = user.id
            state.user.name = user.name
            state.user.phone = user.phone
            state.user.gender = user.gender
            state.user.mail = user.mail
            state.user.password = user.password
        },
    },
})

export default store
