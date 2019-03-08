<template>
  <div>
    <div id="particles" style="background-image: url('/static/image/dark_souls.jpg');
-webkit-background-size: cover;position:fixed;top:0px;bottom: 0px;left: 0px;right: 0px"></div>
    <div class="loginfrom">
      <el-form :model="ruleForm" status-icon :rules="rules2" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item>
          <h1 style="text-align: center;color: darkorange">欢迎登陆</h1>
        </el-form-item>
        <el-form-item label="用户名" prop="username">

          <el-input  type="text" v-model="ruleForm.username" autocomplete="off">
            <template slot="prepend"><span class="fa fa-user"></span></template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">

          <el-input  type="password" v-model="ruleForm.pass" autocomplete="off">
            <template slot="prepend"><span class="fa fa-key"></span></template>
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-button style="float: left" type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button  style="float: right" @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>

      </el-form>

    </div>


  </div>
</template>
<style scoped>
  .loginfrom {
    width: 470px;
    position: relative;
    top: 350px;
    margin: auto;
    left: 150px;
  }
  .el-button{
    width: 40%;
  }

</style>
<script>
  import particles from 'particles.js'

  export default {
    data() {
      var checkUsername = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空'));
        }
        callback();
      };
      var validatePass = (rule, value, callback) => {
        if (!value ){
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.pass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      return {
        ruleForm: {
          username: '',
          pass: '',
        },
        rules2: {
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          username: [
            {validator: checkUsername, trigger: 'blur'}
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const ref=this;
            this.$http.post('/api/user/login', {
              'password': ref.ruleForm.pass,
              'userName': ref.ruleForm.username,

            })
              .then(function (response) {
                if(response.data.status===200){
                  saveObjArr("state",response.data.status);
                  ref.$router.push({path:'/'})
                }else {
                  ref.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }, mounted() {
      particlesJS.load('particles', '/static/cavs.json');
    }, comments() {
      name:particles
    }
  }

</script>
