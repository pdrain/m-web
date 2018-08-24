define(['jquery'],function () {
    var fileUpload={};

    var _getFileInstance = function (id) {
        var file =  $('#'+id)[0].files[0];
        return file;
    }

    var _getFileInfo=function (file) {
        var _file_name = file.name;
        var _file_size = file.size;
        var _file_type =  _file_name.substring(_file_name.lastIndexOf('.') + 1);
        return {
            name:_file_name,
            size:_file_size,
            type:_file_type
        }
    }

    var _getPostData=function (file) {
        var formData = new FormData();
        formData.append('file', file);
        return formData;
    }

    var _uploadPreCheck=function (_file) {

        if(!_file){
            alert('请选择要上传的文件！');
            return false;
        }
        var _file_info = _getFileInfo(_file);
        if (_file_info.type != 'jpg' && _file_info.type != 'png' && _file_info.type != 'gif') {
            alert("请上传.jpg、.png、.gif格式的图片！");
            return false;
        }
        if (_file_info.size > 4000 * 1024) {
            alert("请上传大小小于4MKB的图片！");
            return false;
        }
    }

    var _upload=function (url,id,callback) {
        var _file = _getFileInstance(id);
        _uploadPreCheck(_file);
        var _post_data = _getPostData(_file)
        $.ajax({
            url: url,
            type: 'POST',
            data: _post_data,
            cache: false,
            processData: false,
            contentType: false
        }).done(function (result) {
            if (result != '') {
                if(callback && typeof(callback)=='function'){
                    callback(result);
                }
            } else {
                alert("请上传.jpg、.png、.gif格式的图片！");
            }
        }).fail(function () {
            alert("图片上传失败！");
        });
    }

    fileUpload.uploadImg = _upload

    return fileUpload;
});