<template>
  <v-layout>
    <NavBar/>
    <v-main>
      
    </v-main>
  </v-layout>
</template>

<script>
  import NavBar from '../components/NavBar.vue'

  export default {
    name: "/home",
    components: {
        NavBar
    },
  }
</script>