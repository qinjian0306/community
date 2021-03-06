<!DOCTYPE html>
<html lang="en">
<head>
    <link rel='stylesheet' href='../production/bootstrap/css/bootstrap.min.css'>
    <link rel='stylesheet' href='../production/font-awesome/css/font-awesome.min.css'>
    <link rel='stylesheet' href='../custom/css/login.css'>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 100px">
        <div id="loginDiv" class="col-md-offset-3 col-md-6 col-lg-offset-3 col-lg-6">
            <form id="login" class="form-horizontal">
                <span class="heading">用户登录</span>
                <div>
                    <label class="help-block">
                        <span class="text-danger help-tip">&nbsp;</span>
                    </label>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="username" id="username" placeholder="用 户 名">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" name="password" id="password" placeholder="密　码">
                    <i class="fa fa-lock"></i>
                    <div style="margin-top: 5px">
                        <span class="pull-left small">
                            &nbsp;&nbsp;<a href="#">忘记密码</a>
                        </span>
                        <span class="pull-right small">
                            还没有账号？<a href="/toRegister">去注册</a>&nbsp;&nbsp;
                        </span>
                    </div>
                    <!--<a href="#" class="fa fa-question-circle"></a>-->
                </div>

                <div class="form-group text-left">
                    &nbsp;&nbsp;
                    <label class="radio-inline">
                        <input type="radio" name="role" id="doctorRadio" value="1"> 医生
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="patientRadio" value="2" > 病人
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="patientRadio" value="3" checked> 管理员
                    </label>
                    <button id="submit" type="button" class="btn btn-default">登录</button>
                </div>

                <!--<div class="form-group">-->
                <!--<div class="main-checkbox">-->
                <!--<input type="checkbox" value="None" id="checkbox1" name="check"/>-->
                <!--<label for="checkbox1"></label>-->
                <!--</div>-->
                <!--<span class="text">Remember me</span>-->
                <!--<button type="submit" class="btn btn-default">登录</button>-->
                <!--</div>-->
            </form>
        </div>
    </div>
</div>
</body>

<script src="../production/jquery/jquery.min.js"></script>
<script>
    $('#login').on('submit', function () {
        var options = {
            type: 'POST',
            url: '/user/login',
            data: $(this).serialize(),
            success: function (data) {
                // alert(data.msg);
                if(data.data==1){
                    location.href="/doctor/getCaseList";
                }else if(data.data==2){
                    location.href="/patient/getCaseList";
                } else if(data.data==3){
                    location.href="/doctor/allPend";
                }
                $('.help-tip').html(data.msg);

                // $('.help-block').show();
                // alert(data.msg);
            },
            error: function () {
                alert(data.msg);
                $('.help-tip').html(data.msg);
                // $('.help-block').show();
            }
        };
        $.ajax(options);
    });
    $('#submit').click(function () {
        $('#login').submit();
    });
</script>
</html>