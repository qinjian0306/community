//检查用户名
$('#username').blur(function () {
    $(".help-username .help-block").hide();
    var username = $('#username').val().trim();
    if (username.length > 0) {
        var data = "username=" + username;
        $.get("/user/ifExist", data, function (data, status) {
            if ("success" == status) {
                if (1 == data.code) {
                    $(".help-username .help-success").show();
                } else if (0 == data.code) {
                    $(".help-username .help-warning").show();
                } else {
                    $(".help-username .help-default").show();
                }
            }
        });
    } else {
        $(".help-username .help-danger").show();
    }
});

var pass = null;

//检查密码
$('#password').blur(function () {
    $(".help-password .help-block").hide();
    var password = $('#password').val().trim();
    if (password.length > 0) {
        pass = password;
        $(".help-password .help-success").show();
    } else {
        $(".help-password .help-danger").show();
    }
});

//确认密码
$('#repassword').blur(function () {
    if (pass != null) {
        $(".help-repassword .help-block").hide();
        var repassword = $('#repassword').val().trim();
        if (repassword.length > 0) {
            if (pass == repassword) {
                $(".help-repassword .help-success").show();
            } else {
                $(".help-repassword .help-warning").show();
            }
        } else {
            $(".help-repassword .help-danger").show();
        }
    } else {
        $(".help-repassword .help-default").show();
    }
});

//检查手机号
$('#phone').blur(function () {
    $(".help-phone .help-block").hide();
    var phone = $('#phone').val();
    if (phone.length > 0) {
        var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (regex.test(phone)) {
            $(".help-phone .help-success").show();
        } else {
            $(".help-phone .help-warning").show();
        }
    } else {
        $(".help-phone .help-default").show();
    }
});


//检查邮箱
$('#email').blur(function () {
    $(".help-email .help-block").hide();
    var email = $('#email').val();
    if (email.length > 0) {
        var regex = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (regex.test(email)) {
            $(".help-email .help-success").show();
        } else {
            $(".help-email .help-warning").show();
        }
    } else {
        $(".help-email .help-default").show();
    }
});