define(['vue','utils'],function (vue,utils) {
    "use strict"
    new vue({
        el:'#app',
        data:function () {
            return{
                queryParam:{
                    user:'',
                    userName:'',
                    roleId:1
                },
                userList:[]
            }
        },
        mounted:function(){
            this.getUserList();
        },
        methods:  {
            getUserList:function () {
                var _this = this;
                var _url = '/admin/user/get_all_userlist';
                var data = this.queryParam;
                utils.post(_url,data,function (res) {
                    _this.userList = res.result;
                })
            },
            goDetail:function (id) {
                window.location.href='/web/admin/user/add-and-edit?id='+id;
            }
        }
    });
});