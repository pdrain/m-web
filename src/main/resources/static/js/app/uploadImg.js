var isJpg = function(name) {
    return name.match(/jpg$/i)
};

var isPng = function(name) {
    return name.match(/png$/i)
};

$(document).ready(function () {
    var file = $('[name="file"]');

    $('#upload').click(function (event) {
        event.preventDefault();
        var filename = $.trim(file.val());

        if (!(isJpg(filename) || isPng(filename))) {
            alert('Please browse a JPG/PNG file to upload ...');
            return;
        }

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/admin/article/upload",
            data: new FormData(document.getElementById("addArticle")),
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000}).done(function(data) {
            var imageSection = $('div.image-section');
            imageSection.html('');
            var img = '<img src="' + data.file +'"/>';

            imageSection.append(img);
        }).fail(function(jqXHR, textStatus) {
            //alert(jqXHR.responseText);
            alert('File upload failed ...');
        });
    });
});