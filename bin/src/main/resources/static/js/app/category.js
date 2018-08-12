$(document).ready(function () {
    $('form #menuSeleced').on('change', function () {
        $('form #firstMenuSection').hide();
        $('form #secondMenuSection').hide();
        $('form #parentId').val("");
         var grade = this.value;
         if (grade === '2') {
             $('form #firstMenuSection').show();
             $('form #parentId').val($('#firstMenuSection select').val());
         } else if (grade === '3') {
             $('form #secondMenuSection').show();
             $('form #parentId').val($('#secondMenuSection select').val());
         }
    })
});