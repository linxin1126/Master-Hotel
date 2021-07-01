<template>
    <div id="myProfile">
      <mu-container class="profilePaper">
        <mu-row>
          <mu-appbar class="profileAppBar" color="primary">
            <mu-icon slot="left"></mu-icon>
            我的个人资料
          </mu-appbar>
        </mu-row>
        <mu-row>
          <mu-col span="12">
            <mu-card class="ProfileCard" raised>
              <mu-card-header :title="'用户名： ' + workerInfo.username" :sub-title="'操作员ID号:  ' + workerInfo.workerId">
                <mu-avatar slot="avatar">
                  
                </mu-avatar>
              </mu-card-header>
              <mu-card-text>
                <mu-list>
                  <mu-list-item>
                    <mu-list-item-action><mu-icon ></mu-icon></mu-list-item-action>
                    <mu-list-item-title>姓名： {{ workerInfo.name }}</mu-list-item-title>
                  </mu-list-item>
                  <mu-list-item>
                    <mu-list-item-action><mu-icon ></mu-icon></mu-list-item-action>
                    <mu-list-item-title>角色： {{ workerInfo.role == 'admin' ? '管理员' : '操作员'}}</mu-list-item-title>
                  </mu-list-item>
                  <mu-list-item>
                    <mu-list-item-action><mu-icon ></mu-icon></mu-list-item-action>
                    <mu-list-item-title>手机号： {{ workerInfo.phone }}</mu-list-item-title>
                  </mu-list-item>
                  <mu-list-item>
                    <mu-list-item-action><mu-icon ></mu-icon></mu-list-item-action>
                    <mu-list-item-title>电子邮箱：{{ workerInfo.email }}</mu-list-item-title>
                  </mu-list-item>
                  <mu-list-item>
                    <mu-list-item-action><mu-icon ></mu-icon></mu-list-item-action>
                    <mu-list-item-title>地址： {{ workerInfo.address }}</mu-list-item-title>
                  </mu-list-item>
                  <mu-list-item>
                    <mu-list-item-action><mu-icon ></mu-icon></mu-list-item-action>
                    <mu-list-item-title>加入时间： {{ workerInfo.createTime | formatDate }}</mu-list-item-title>
                  </mu-list-item>
                </mu-list>
              </mu-card-text>
              <mu-card-actions>
                <!--<mu-button flat>Action 1</mu-button>-->
                <!--<mu-button flat>Action 2</mu-button>-->
              </mu-card-actions>
            </mu-card>
          </mu-col>
        </mu-row>
      </mu-container>
    </div>
</template>

<script>
  import Cookies from 'js-cookie'
  import { getWorkerInfo } from '@/api/worker'
    export default {
        name: "MyProfile",
      data(){
          return{
            //username: Cookies.get("username"),
            workerInfo: {
              userId: 0,
              username: "username",
              createTime: null,
            },
        }
      },
      created: function(){
          this.fetchData()
      },
      methods:{
          fetchData(){
            getWorkerInfo().then(res => {
              this.workerInfo = res.data;
            }).catch(err => {
              this.$toast.error(err)
            })
          }
      },
    }
</script>

<style scoped>
  .profileAppBar{
    height: 40px;
    width: auto!important;
    border-radius: 35px;
    margin: 10px;
    color: #fff;
    padding: 10px;
  }
  .profilePaper{
    padding: 10px;
  }
  .ProfileCard{
    width: 100%;
    margin: 0 auto;
  }
</style>
