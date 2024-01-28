import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router'
import store from './store'
import { properties } from './properties'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'


const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
    },
  })
const backendLink = properties.backendUrl
const app = createApp(App)
store.commit("SET_BACKEND_LINK", backendLink)

app.use(router(backendLink))
app.use(store)
app.use(vuetify)

app.mount('#app')
