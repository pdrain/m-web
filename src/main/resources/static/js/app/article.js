$(document).ready(function(){
    $('#selectAll').click(function () {
        if($(this).is(':checked')) {
            $('.checkItem:checkbox').prop("checked", true);
        } else {
            $('.checkItem:checkbox').prop("checked", false);
        }
    });
    
    //删除按钮
    $('button.deleteBtn').click(function () {
        var ids = [];
        $('.checkItem:checkbox:checked').each(function (index, value) {
             ids.push($(this).val());
        });
        if (ids.length > 0) {
            $.ajax({
                url: '/admin/article/delete',
                type: 'post',
                data: {ids: ids.join(',')},
                cache: false,
                success: function (data) {
                    for (var id in ids) {
                       $('tr.item-' + id).remove();
                    }
                }
            });
        }
    });

    //编辑按钮
    $('button.edit').click(function () {
        var id = $(this).data('id');
        window.location.href='/web/admin/articles/views/' + id;
    });
});
