<template>
  <v-layout>
    <Suspense>
      <AppBar/>
    </Suspense>
    <v-main>
      
    </v-main>
  </v-layout>
</template>

<script>
  import AppBar from '../components/AppBar.vue'

  export default {
    name: "/home",
    components: {
        AppBar
    },
  }
</script>