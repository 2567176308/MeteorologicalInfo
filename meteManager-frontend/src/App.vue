<script setup>
import CloudShowVue from './components/CloudShow.vue';
import Header from './components/Header.vue';
import Login from './components/Login.vue'
import Register from './components/Register.vue';
import Footer from './components/Footer.vue';
</script>

<template>
  <Header />
  <main>
    <RouterView></RouterView>
  </main>
</template>

<style scoped>
</style>
