<template>
    <main role="main" class="inner cover">
      <h1 class="cover-heading">Welcome </h1>
      <h1 class="cover-heading" v-if="!isAuthenticated">You are not logged in!</h1>
    </main>
  </template>
  
  <script>
    import { computed } from 'vue';
    import { useStore } from 'vuex'

    export default {
      name: "Home",
      setup() {
        const store = useStore()

        let currentUser = localStorage.getItem("currentUser")
        if (!currentUser || currentUser === {}) {
          store.dispatch("fetchUser");
        }

        return {
          isAuthenticated: computed(() => store.getters.isAuthenticated),
        }
      }  
    }
  </script>