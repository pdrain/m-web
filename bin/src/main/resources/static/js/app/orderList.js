$(document).ready(function () {
    $('.handle').on('click', function (e) {
        var id = $(this).data('id');
        udpateStatus(id,1);
    });

    $('.finish').on('click', function (e) {
        var id = $(this).data('id');
        udpateStatus(id,2);
    });

    var udpateStatus = function (id, status) {
        $.ajax({
            type: "POST",
            url: '/web/admin/orders/update/' + id,
            data: {status: status},
            success: function (msg)
            {
                window.location.href="/web/admin/orders";
            },
            error: function (e)
            {
               console.log("更新失败");
            }
        });
    };
});