<template>
  <div class="aside">
      <ul class="menu">
          <li class="menu-item">
              <router-link to="/home/users">用户管理</router-link>
          </li>
          <li class="menu-item">
              <router-link to="/home/rights">权限管理</router-link>
          </li>
          <li class="menu-item">
              <router-link to="/home/goods">商品管理</router-link>
          </li>
          <li class="menu-item">
              <router-link to="/home/orders">订单管理</router-link>
          </li>
          <li class="menu-item">
              <router-link to="/home/settings">系统设置</router-link>
          </li>
      </ul>
  </div>
</template>

<script>
export default {
name:'MySide',
}
</script>

<style>
li{
    list-style: none;
}
.aside{
    float: left;
    width: 20%;
}
</style>