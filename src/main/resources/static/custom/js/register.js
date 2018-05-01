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


//检查密码
var pass = null;
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
$('#password').focus(function () {
    $('#repassword').val('');
    $('.help-repassword .help-block').hide();
    $('.help-repassword .help-default').show();
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
$('#mobile').blur(function () {
    $(".help-mobile .help-block").hide();
    var phone = $('#mobile').val();
    if (phone.length > 0) {
        var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (regex.test(phone)) {
            $(".help-mobile .help-success").show();
        } else {
            $(".help-mobile .help-warning").show();
        }
    } else {
        $(".help-mobile .help-default").show();
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


//检查表单是否可提交
function checkSubmit() {
    var submit = true;
    $('.required').each(function () {
        var value = $(this).val().trim();
        if (value.length == 0) {
            submit = false;
        }
    });
    return submit;
}

$('.form-control').blur(function () {
    var warning = $('.help-warning[style=""]').length;
    var danger = $('.help-danger[style=""]').length;
    var error = warning + danger;
    if (checkSubmit() && error == 0) {
        $('#submit').removeAttr('disabled');
    } else {
        $('#submit').attr('disabled', true);
    }
});

//表单提交事件绑定
$('#register').on('submit', function () {
    var options = {
        type: 'POST',
        url: '/user/register',
        data: $(this).serialize(),
        success: function (data) {
            alert(data.msg);
        },
        error: function () {
            alert(data.msg);
        }
    };
    $.ajax(options);
});

$('#submit').click(function () {
    $('#register').submit();
});