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

function checkPasswords() {
    var passwordInput = $("#inputPassword");
    var passwordAgainInput = $("#inputPasswordAgain");
    var password = passwordInput.val();
    var passwordAgain = passwordAgainInput.val();
    var pP = passwordInput.parent();
    var pA = passwordAgainInput.parent();
    if (password == "" || passwordAgain == "" || password != passwordAgain) {
        pP.addClass("has-error");
        pA.addClass("has-error");
    } else {
        pP.removeClass("has-error");
        pA.removeClass("has-error");
    }
}

function onSubmit() {
    checkEmpty();
    checkPasswords();

    return $(".has-error").length == 0;
}

$(document).ready(function () {
    $("#registrationButton").click(function () {
        if (onSubmit()) {
            console.log("0: " + $(".has-error").length);
            $(this).parent().submit();
        }
    });
});
