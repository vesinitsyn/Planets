function checkEmpty() {
    $(".user-data").each(function (i, element) {
        makeEmptyCheck(element);
    });
}

function makeEmptyCheck(element) {
    var input = $(element);
    var parent = input.parent();
    if (input.val() === "") {
        parent.addClass("has-error");
    } else {
        parent.removeClass("has-error");
    }
}

function onSubmit() {
    checkEmpty();

    return $(".has-error").length == 0;
}

$(document).ready(function () {
    $("#loginButton").click(function () {
        if (onSubmit()) {
            $(this).parent().submit();
        }
    });
});
