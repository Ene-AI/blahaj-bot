import { createRouter, createWebHistory } from 'vue-router'
import ViewScheduledMessages from '../views/ViewScheduledMessages.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ViewScheduledMessages
    },
    {
      path: '/add',
      name: 'add',
      component: ()=>import ('../views/AddScheduledMessage.vue')
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: () => import('../views/UpdateScheduledMessage.vue')
    }
  ]
})

export default router
