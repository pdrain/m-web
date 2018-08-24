define(['vue','utils'],function (vue,utils) {
    var _queryParamTemp={
        user:'',
            userName:'',
        roleId:1
    }
    new vue({
        el:'#app',
        data:function () {
            return{
                queryParam:eval('('+JSON.stringify(_queryParamTemp)+')'),
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
            query:function () {
                this.getUserList();
            },
            reset:function () {
                this. queryParam=eval('('+JSON.stringify(_queryParamTemp)+')');
            }
        }
    });
});