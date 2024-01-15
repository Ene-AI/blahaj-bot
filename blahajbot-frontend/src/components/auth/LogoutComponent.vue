<template>
    <a class="btn btn-block" @click.prevent="logout">Logout</a>
</template>
  
<script>
  import { useStore } from 'vuex'
  export default {
    name: "Logout",
    setup() {
      const store = useStore()

      return {
        logout: () => store.dispatch("logout")
      }
    }
  }
</script>