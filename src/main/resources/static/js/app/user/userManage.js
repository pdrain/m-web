define(['vue','utils'],function (vue,utils) {
    var _editdata_temp={
        id:0,
        user:'',
        password:'',
        password1:'',
        userName:'',
        roleId:0
    }
    new vue({
        el:'#app',
        data:function () {
            return{
                id:'',
                modelName:'新增',
                adminUser:eval('('+JSON.stringify(_editdata_temp)+')'),
            }

        },
        mounted:function(){
            this.id  = utils.getQueryParam('id');
            if(this.id){
                this.modelName='编辑';
                this.getDetail();
            }
        },
        methods:{
            getDetail:function(){
                var _this  =this;
                var _url = '/admin/user/get_admin_user';
                var _param = {id:_this.id};
                utils.post(_url,_param,function (res) {
                    if(res.result){
                        _this.adminUser = res.result;
                    }
                });
            },
            reset:function () {
                this.adminUser=eval('('+JSON.stringify(_editdata_temp)+')');
            },
            save:function () {
                var _this = this;

                if(!_this.savePreCheck()) return false;

                var _url= '/admin/user/add_admin_user';
                utils.post(_url,_this.adminUser,function (res) {
                    alert('保存成功！');
                    if(_this.id){
                        window.location.href='/web/admin/user/list';
                    }else{
                        _this.reset();
                    }

                });
            },
            savePreCheck:function () {
                var _this = this;
                if(!_this.adminUser.user){
                    alert('登录账号不能为空！');
                    return false;
                }
                if(!this.id) {
                    if (!_this.adminUser.password) {
                        alert('密码不能为空！');
                        return false;
                    }
                    if (_this.adminUser.password != _this.adminUser.password1) {
                        alert('两次密码输入不一致，请重新输入！');
                        return false;
                    }
                }
                if(!_this.adminUser.userName){
                    alert('用户名不能为空！');
                    return false;
                }
                return true;

            }
        }
    });
});