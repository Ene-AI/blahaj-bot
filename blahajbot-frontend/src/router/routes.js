export function routes(backendLink){
    return [
        {
            path: '/',
            name: 'Home',
            component: ()=>import ('../views/Home.vue'),
            meta: {
                requiresAuth: false
            }
        },
        {
            path: '/:guildId/config',
            name: 'Configuration Dashboard',
            component: ()=>import ('../views/ConfigurationDashboard.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/:guildId/scheduled_messages',
            name: 'Scheduled Message Config',
            component: ()=>import ('../views/ViewScheduledMessages.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/:guildId/add',
            name: 'Add Scheduled Message',
            component: ()=>import ('../views/AddScheduledMessage.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/:guildId/edit/:id',
            name: 'Edit Scheduled Message',
            component: () => import('../views/UpdateScheduledMessage.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            name: "Login",
            path: "/login",
            component: {},
            meta: { RedirectExternalUrl: backendLink + "/discord/callback" },
        }
    ];
}