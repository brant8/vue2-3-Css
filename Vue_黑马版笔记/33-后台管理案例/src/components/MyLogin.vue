<template>
  <div class="container ">
      <div class="loginBox">
              <div class="singin">
                  
            <form>
                <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="username" class="col-form-label">Username</label>
                </div>
                <div class="col-auto">
                    <input type="text" id="username" class="form-control" aria-describedby="passwordHelpInline" v-model.trim="username">
                </div>
                </div>

                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <label for="password" class="col-form-label">Password</label>
                    </div>
                    <div class="col-auto">
                        <input type="password" id="password" class="form-control" aria-describedby="passwordHelpInline" v-model="password">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" @click="login">Submit</button>
                <button class="btn btn-info" @click="reset">清空</button>
                </form>

              </div>
      </div>
  </div>
</template>

<script>
export default {
    name:'MyLogin',
    data(){
        return{
            username:'',
            password:'',
        }
    },
    methods:{
        reset(){
            this.username=''
            this.password=''
            localStorage.removeItem('token')

        },
        login(){
            if(this.username==='admin' &&  this.password==='666666'){
                localStorage.setItem('token','Bear xxx') //1.存储token
                this.$router.push('/home') //2.跳转到后台主页
            }else{
                localStorage.removeItem('token')
                this.$router.push('/login')
            }
        }
    }


}
</script>

<style>
.container{
    margin: 0 auto;
}
.loginBox{
    margin: 20% auto;
    width: 450px;
    padding: 10px;
    border:1px solid grey
}
button{
    margin:10px;
}
</style>