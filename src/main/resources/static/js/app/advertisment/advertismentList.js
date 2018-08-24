define(['vue','utils','fileUpload','jquery'],function (vue,utils,fileUpload) {
    var _editData_temp={
        code:'',
        imgs:[]
    };
    new vue({
        el:'#app',
        data:function () {
            return{
                test:'hello',
                list:[],
                editData:eval('('+JSON.stringify(_editData_temp)+')')
            }
        },
        mounted:function () {
            this.getAdvList();
        },
        methods:{
            newAdv:function(){
                this.showDialog();
            },
            editAdv:function(){},
            getAdvList:function () {

            },
            saveAdv:function(){

            },
            cancelAdv:function(){
                this.editData=eval('('+JSON.stringify(_editData_temp)+')');
                this.hideDialog();
            },
            addAdvImg:function(){
                var imgs = this.editData.imgs;
                if(imgs.length==5){
                    alert('最多上传5张图片！');
                    return false;
                }
                var imgItem={path:'',link:''};
                imgs.push(imgItem);
                this.editData.imgs = imgs;
            },
            selectImg:function(id){
                var _file_el = $('#'+id);
                _file_el.trigger('click');
                _file_el.change(function () {
                    fileUpload.uploadImg('/web/admin/adv/upload',id,function (res) {
                        alert(res.result)
                    })
                })
            },
            showDialog:function () {
                $('#edit-form').show();
            },
            hideDialog:function () {
                $('#edit-form').hide();
            }
        }
    });
});