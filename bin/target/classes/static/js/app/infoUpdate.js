$(document).ready(function () {
    var E = window.wangEditor;
    var editor = new E('#contentSection')
    var $content = $('#content')

    editor.customConfig.uploadImgShowBase64 = true
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $content.val(html)
    }
    editor.create()
    editor.txt.html($content.val())
    // 初始化 textarea 的值
    $content.val(editor.txt.html())

    $('#infoUpdateForm').on('submit', function () {
        var value = $('#content').val();
        if (!value && $.trim(value).length < 1) {
            alert("请输入内容");
            return false;
        }
        return true;
    });

});