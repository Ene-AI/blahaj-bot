import { createRouter, createWebHistory } from 'vue-router'
import { routes } from "./routes";

export function router(backendLink){
  const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes(backendLink)
  });

  router.beforeEach((to, from, next) => {
    let requiresAuth = to.matched.some(value => value.meta.requiresAuth);
    let currentUser = localStorage.getItem("currentUser");

    if(requiresAuth && !currentUser) {
      next("/");
    } else {
      next();
    }
  });

  return router;
}


