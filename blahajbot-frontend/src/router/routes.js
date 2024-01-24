export const routes = [
    {
        path: '/',
        name: 'Home',
        component: ()=>import ('../views/Home.vue'),
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/:guildId/scheduled_messages',
        name: 'scheduled_messages',
        component: ()=>import ('../views/ViewScheduledMessages.vue'),
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/:guildId/add',
        name: 'add',
        component: ()=>import ('../views/AddScheduledMessage.vue'),
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/:guildId/edit/:id',
        name: 'edit',
        component: () => import('../views/UpdateScheduledMessage.vue'),
        meta: {
            requiresAuth: true
        }
    },
    {
        name: "Login",
        path: "/login",
        component: {},
        meta: { RedirectExternalUrl: "http://localhost:8080/discord/callback" },
    }
];