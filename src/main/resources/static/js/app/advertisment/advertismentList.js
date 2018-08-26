define(['vue', 'utils', 'fileUpload', 'jquery'], function (vue, utils, fileUpload) {
    var _editData_temp = {
        code: '',
        imgs: []
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
            editAdv: function () {
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

                var param={
                    code:_this.editData.code,
                    advertismentList:_this.editData.imgs
                }
                var _url="/admin/adv/save";
                utils.post(_url,param,function (res) {
                    _this.list = res.result;
                    _this.cancelAdv();
                    _this.editData = eval('(' + JSON.stringify(_editData_temp) + ')')
                })
            },
            cancelAdv: function () {
                var _this = this;
                var imgs = this.editData.imgs;
                var flag=true;
                if(imgs.length>0){
                    if(confirm('是否确认取消？取消后数据将不保存！')){
                        imgs.forEach(function (value) {
                            _this.doRemove(value)
                        })
                    }else{
                        flag=false;
                    }
                }
                if(flag) {
                    this.editData = eval('(' + JSON.stringify(_editData_temp) + ')');
                    this.hideDialog();
                }
            },
            addAdvImg: function () {
                var imgs = this.editData.imgs;
                if (imgs.length == 5) {
                    alert('最多上传5张图片！');
                    return false;
                }
                var imgItem = {path: '', link: ''};
                imgs.push(imgItem);
                this.editData.imgs = imgs;
            },
            selectImg: function (item, id) {
                var _file_el = $('#' + id);
                _file_el.trigger('click');
                _file_el.change(function () {
                    fileUpload.uploadImg('/web/admin/adv/upload', id, function (res) {
                        //alert(res.result)
                        item.path = res.result;
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
                var param = {path: item.path};
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