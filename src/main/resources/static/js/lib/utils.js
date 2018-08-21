define(['jquery'],function ($) {

    var utils={};
    
    var _ajax=function (_url,_data,_successCallback) {
        $.ajax({
            method:'POST',
            url:'/web'+_url,
            data:JSON.stringify(_data),
            contentType:'application/json;charset=UTF-8',
            dataType:'json',// 返回值类型
            success:function (res) {
                if(res.code=='000000'){
                    if(_successCallback && typeof(_successCallback)=='function'){
                        _successCallback(res)
                    }
                }else{
                    alert(res.msg);
                }
            },
            error:function (err) {
                alert('调用失败');
            }
        });
    }

    var  _getQueryParam = function(name){

        var result = location.search.match(new RegExp("[\?\&]" + name+ "=([^\&]+)","i"));

        if(result == null || result.length < 1){

            return "";

        }

        return result[1];

    }

    //post 请求
    utils.post = _ajax;
    utils.getQueryParam=_getQueryParam;



    return utils;
    
});