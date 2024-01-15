import { createRouter, createWebHistory } from 'vue-router'
import { routes } from "./routes";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes
});

router.beforeEach((to, from, next) => {
  let requiresAuth = to.matched.some(value => value.meta.requiresAuth);
  let currentUser = localStorage.getItem("currentUser");

  if(requiresAuth && !currentUser || requiresAuth && currentUser === {}) {
    next("/login");
  } else {
    next();
  }
});

export default router
