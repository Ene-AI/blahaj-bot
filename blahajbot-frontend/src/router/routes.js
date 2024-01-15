//import HomeComponent from '../components/home/HomeComponent';
//import LoginComponent from "../components/auth/LoginComponent";
//import LogoutComponent from "../components/auth/LogoutComponent";

export const routes = [
    {
        path: '/',
        name: 'Home',
        component: ()=>import ('../components/home/HomeComponent.vue'),
        meta: {
            requiresAuth: false
    }
    },
    {
        path: '/login',
        name: 'Login',
        component: ()=>import ('../components/auth/LoginComponent.vue'),
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/logout',
        name: "Logout",
        component: ()=>import ('../components/auth/LogoutComponent.vue'),
        meta: {
            requiresAuth: true
        }
    },
    {
      path: '/scheduled_messages',
      name: 'scheduled_messages',
      component: ()=>import ('../views/ViewScheduledMessages.vue'),
      meta: {
          requiresAuth: true
      }
    },
    {
      path: '/add',
      name: 'add',
      component: ()=>import ('../views/AddScheduledMessage.vue'),
      meta: {
          requiresAuth: true
      }
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: () => import('../views/UpdateScheduledMessage.vue'),
      meta: {
          requiresAuth: true
      }
    }
];