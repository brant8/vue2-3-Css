<template>
  <div class="div">
      <p>{{ count }}</p>
      <button @click="add">+1</button>
  </div>
</template>

<script>
export default {
    props:['init'],
    data(){
        return{
            count:this.init,
        }
    },
    methods: {
        add(){
            this.count= this.count +1
        }
    },

}
</script>

<style>

</style>