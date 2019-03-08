import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
var constantRouterMap=[

 {
    path: "/login",
    component: (resolve) => require(['../views/login/index.vue'], resolve),
    hidden: true
  }
]


export default new Router({
  mode: 'history',
  routes: constantRouterMap,




})
