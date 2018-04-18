(function(){
    $.ajax({
        type: 'POST',
        url: '/visit',
        data: {page: document.URL},
        success: function (data) {
            console.log('Thanks for your visit');
        }
    });
})();
