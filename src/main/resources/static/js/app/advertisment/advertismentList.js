define(['vue', 'utils', 'fileUpload', 'jquery'], function (vue, utils, fileUpload) {
    var _editData_temp = {
        id:0,
        code: '',
        path: '',
        link:''
    };
    new vue({
        el: '#app',
        data: function () {
            return {
                test: 'hello',
                list: [],
                editData: eval('(' + JSON.stringify(_editData_temp) + ')')
            }
        },
        mounted: function () {
            this.getAdvList();
        },
        methods: {
            newAdv: function () {
                this.showDialog();
            },
            editAdv: function (id) {

                this.getAdvDetail(id)
            },
            onLineAdv:function(item){
                var _this = this;
                var _url="/admin/adv/get_adv_online";
                utils.post(_url,{id:item.id},function (res) {
                   item.status=res.result;
                })
            },
            offLineAdv:function(item){
                var _this = this;
                var _url="/admin/adv/get_adv_offline";
                utils.post(_url,{id:item.id},function (res) {
                    item.status=res.result;
                })
            },
            delAdv:function(item){
                var _this = this;
                var _url="/admin/adv/del_adv";
                utils.post(_url,{id:item.id,path:item.path},function (res) {
                    _this.list = res.result;
                })
            },
            getAdvDetail:function(id){
                var _this = this;
                var param = {
                    id:id
                }
                var _url="/admin/adv/get_adv_detail";
                utils.post(_url,param,function (res) {
                    _this.editData = res.result;
                    _this.showDialog();
                })
            },
            getAdvList: function () {
                var _this = this;
                var param = {
                    code:'',
                    status:''
                }
                var _url="/admin/adv/get_adv_list";
                utils.post(_url,param,function (res) {
                    _this.list = res.result;
                })
            },
            saveAdv: function () {
                var _this = this;
                var _url="/admin/adv/save";
                utils.post(_url,_this.editData,function (res) {
                    _this.list = res.result;
                    _this.closeWindow();
                })
            },
            cancelAdv: function () {
                var _this = this;
                var flag=true;
                    if(confirm('是否确认取消？取消后数据将不保存！')){
                        _this.doRemove(_this.editData)
                    }else{
                        flag=false;
                    }
                if(flag) {
                   this.closeWindow();
                }
            },
            closeWindow:function(){
                this.editData = eval('(' + JSON.stringify(_editData_temp) + ')');
                this.hideDialog();
            },
            selectImg: function () {
                var _this = this;
                var id='upload_img_0';
                var _file_el = $('#'+id);
                _file_el.trigger('click');
                _file_el.change(function () {
                    fileUpload.uploadImg('/web/admin/adv/upload', id, function (res) {
                        //alert(res.result)
                        _this.editData.path = res.result;
                    })
                })
            },
            removeImg: function (item) {
                var _this = this;
                if (confirm('是否确认删除？')) {
                   _this.doRemove(item)
                }
            },
            doRemove:function(item){
                var _url = "/admin/adv/remove_img";
                var param = {id:item.id||0,path: item.path};
                utils.post(_url, param, function (res) {
                    item.path='';
                })
            },
            showDialog: function () {
                $('#edit-form').show();
            },
            hideDialog: function () {
                $('#edit-form').hide();
            }
        }
    });
});