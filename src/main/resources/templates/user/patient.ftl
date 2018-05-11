<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>patient</title>
    <link rel='stylesheet' href='../production/bootstrap/css/bootstrap.min.css'>
    <link rel='stylesheet' href='../production/font-awesome/css/font-awesome.min.css'>
    <link rel='stylesheet' href='../production/bootstrap-table/css/bootstrap-table.min.css'>
    <link rel="stylesheet" href="../production/fileinput/css/fileinput.min.css">
    <link rel="stylesheet" href="../custom/css/main.css">
    <style>
        .dropdown{
            min-width: 100px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">医患交流平台</a>
        </div>
        <!--<div>-->
        <!--<ul class="nav navbar-nav">-->
        <!--<li class="active">-->
        <!--<a href="#">主页</a>-->
        <!--</li>-->
        <!--</ul>-->
        <!--</div>-->
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    ${user.nickname}
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <#--<li><a href="#">用户信息</a></li>-->
                        <li><a href="#">密码修改</a></li>
                        <li class="divider"></li>
                            <li><a href="/user/logout">登出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid" style="margin: 10px;margin-top: -10px">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-body">
                <div style="vertical-align: center">
                    <label class="nickname">昵称：<span>${user.nickname}</span></label>
                    <div id="validate" class="text-info text-center small" style="display: inline;">
                        <a class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal">添加病历</a>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                 <#list list.data as case>
                     <div class="row">
                         <div class="col-md-12 col-lg-12">
                                   <a class="pull-left" href="/patient/viewCase?caseId=${case.id}">${case.title}</a>
                             <#--<#if (case.status==1)>-->
                                   <#--<a class="pull-left" href="#">${case.title}</a>-->
                             <#--</#if>-->
                              <#--<#if (case.status!=1)>-->
                                   <#--<a class="pull-left" href="/patient/viewCase?caseId=${case.id}">${case.title}</a>-->
                              <#--</#if>-->
                             <span class="pull-right">
                                  <span>
                                        <#if (case.status==1)>已关闭</#if>
                                       <#if (case.status==0)>
                                           <div id="validate" class="text-info text-center small" style="display: inline;">
                                                <a href="/patient/lockCase?caseId=${case.id}" class="btn btn-xs btn-primary">关闭</a>
                                            </div>
                                       </#if>
                                  </span>
                              </span>
                             <div class="clearfix col-md-12 col-lg-12">&nbsp;</div>
                         </div>
                         <div class="col-md-12 col-lg-12" style="font-size: 10px">
                             患者：<span>${case.patient}</span>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             创建时间：<span>${case.createTimeStr?string("yyyy-MM-dd HH:mm")}</span>
                             <span class="pull-right">最后回复时间：<span>${case.updateTimeStr?string("yyyy-MM-dd HH:mm")}</span></span>
                         </div>
                     </div>
                     <hr>
                 </#list>
            </div>
            <nav aria-label="Page navigation" class="pull-right">
                <ul id="pagination" class="pagination">
                    <!--<li>-->
                    <!--<a href="#" aria-label="Previous">-->
                    <!--<span aria-hidden="true">&laquo;</span>-->
                    <!--</a>-->
                    <!--</li>-->
                    <!--<li><a href="#">1</a></li>-->
                    <!--<li><a href="#">2</a></li>-->
                    <!--<li><a href="#">3</a></li>-->
                    <!--<li><a href="#">4</a></li>-->
                    <!--<li><a href="#">5</a></li>-->
                    <!--<li>-->
                    <!--<a href="#" aria-label="Next">-->
                    <!--<span aria-hidden="true">&raquo;</span>-->
                    <!--</a>-->
                    <!--</li>-->
                </ul>
            </nav>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加病例</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" method="post" class="form-horizontal" role="form" style="padding: 30px 100px 10px;"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="col-md-9 col-lg-9">
                            <input type="hidden" class="form-control" id="userId" name="userId" value="${user.id}">
                            <input type="hidden" class="form-control" id="author" name="author"
                                   value="${user.nickname}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-md-3 col-lg-3 control-label">标题</label>
                        <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control required" id="title" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-md-3 col-lg-3 control-label">患者</label>
                        <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control" id="patient" name="patient">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 col-lg-3 control-label">性別</label>
                        <div class="col-md-9 col-lg-9">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="male" value="1" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="female" value="2"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-md-3 col-lg-3 control-label">电话</label>
                        <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control" id="mobile" name="contact">
                        </div>
                        <label class="help-block help-warning col-md-offset-3 col-lg-offset-3" style="display:none">
                            <span class="text-warning">
                                <i class="fa fa-close">&nbsp;</i>手机号不合法，请重新输入
                            </span>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-3 col-lg-3 control-label">详情</label>
                        <div class="col-md-9 col-lg-9">
                            <textarea id="description" name="description" class="form-control"></textarea>
                            <!--<input type="text" class="form-control" id="detail" name="detail">-->
                        </div>
                    </div>
                    <!--<div class="form-group">-->
                    <!--<label for="photo" class="col-md-3 col-lg-3 control-label">图片</label>-->
                    <!--<div class="col-md-9 col-lg-9">-->
                    <!--<input type="file" class="form-control" id="photo" name="photo">-->
                    <!--</div>-->
                    <!--</div>-->
                    <!--<div class="form-group">-->
                    <!--<label class="btn-sm col-md-3 col-lg-3 control-label">-->
                    <!--<a class="btn btn-xs btn-primary">上传图片</a><input id="filename-hide" class="form-control"-->
                    <!--type="file" style="display: none" multiple>-->
                    <!--</label>-->
                    <!--<div class="col-md-9 col-lg-9">-->
                    <!--<input id="filename" type="text" class="form-control" id="photo" name="photo">-->
                    <!--</div>-->
                    <!--</div>-->
                    <div class="form-group">
                        <label class="btn-sm col-md-3 col-lg-3 control-label">上传文件
                            <!--<a class="btn btn-xs btn-primary">上传图片</a><input id="filename-hide2" class="form-control"-->
                            <!--type="file" style="display: none">-->
                        </label>
                        <div class="col-md-9 col-lg-9">
                            <input type="file" class="form-control" id="files" name="files" multiple>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="addCaseBtn" type="button" disabled class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

</body>
<script src="../production/jquery/jquery.min.js"></script>
<script src="../production/bootstrap/js/bootstrap.min.js"></script>
<script src="../production/fileinput/js/fileinput.min.js"></script>
<script src="../production/bootstrap-pagenator/js/bootstrap-paginator.min.js"></script>
<script>
    $('#filename-hide').change(function () {
        var filename = $('#filename-hide').val();
        alert(filename);
        var separator = 0;
        if (filename.lastIndexOf('/') > 0) {
            separator = filename.lastIndexOf('/');
        } else if (filename.lastIndexOf('\\')) {
            separator = filename.lastIndexOf('\\');
        }
        filename = filename.substring(separator + 1);
        $('#filename').val(filename);
    })
</script>
<script>
    $("#files").fileinput({
        showCaption: false,
        dropZoneEnabled: false,
        showUpload: false,
        showRemove: false,
        showUploadThumbs: false,
        resizeImage: false,
        showZoom: false,
        maxFileCount: 3,
        maxFileSize: 20480,
        allowedFileExtensions: ['jpg', 'png', 'pdf', 'jpeg', 'bmp', 'gif'],
    });

    $('#addCaseBtn').click(function () {
        $('#addForm').submit();
    });
    $('#addForm').on('submit', function () {
        var formData = new FormData();
        var files = document.getElementById("files").files;
        var userId = $('#userId').val();
        var title = $('#title').val();
        var author = $('#author').val();
        var patient = $('#patient').val();
        var gender = $('input[name="gender"]:checked').val();
        var contact = $('#mobile').val();
        var description = $('#description').val();
        formData.append("userId", userId);
        formData.append("title", title);
        formData.append("author", author);
        formData.append("patient", patient);
        formData.append("gender", gender);
        formData.append("contact", contact);
        formData.append("description", description);
        for (var i = 0; i < files.length; i++) {
            formData.append("files", files[i]); //++++++++++
        }
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/patient/addCase');
        xhr.send(formData);
        xhr.onreadystatechange = function (response) { //第四步
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = JSON.parse(xhr.responseText);
                // location.href = "/patient/getCaseList";
            }
        };
        // $.ajax({
        //     url: "/patient/addCase/upload/files",
        //     method: "post",
        //     contentType: "multipart/form-data",
        //     data: formData,
        //     success: function () {
        //         alert("success");
        //     },
        //     error: function () {
        //         alert("error");
        //     }
        // });

    });
</script>
<script>
    var options = {
        bootstrapMajorVersion: 3, //对应的bootstrap版本
        currentPage: ${list.currentPage}, //当前页数，这里是用的EL表达式，获取从后台传过来的值
        numberOfPages: ${list.pageSize},//每页页数
        totalPages: ${list.totalPages}, //总页数，这里是用的EL表达式，获取从后台传过来的值
        shouldShowPage: true,//是否显示该按钮
        itemTexts: function (type, page, current) {//设置显示的样式，默认是箭头
            switch (type) {
                case "first":
                    return "<span class='fa fa-angle-double-left'></span>";
                case "prev":
                    return "<span class='fa fa-angle-left'></span>";
                case "next":
                    return "<span class='fa fa-angle-right'></span>";
                case "last":
                    return "<span class='fa fa-angle-double-right'></span>";
                case "page":
                    return page;
            }
        },
        //点击事件
        onPageClicked: function (event, originalEvent, type, page) {
            location.href = "/patient/getCaseList?pageNum=" + page;
        }
    };
    $("#pagination").bootstrapPaginator(options);

</script>
<script>
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
        // var warning = $('.help-warning[style=""]').length;
        // var danger = $('.help-danger[style=""]').length;
        // var error = warning + danger;
        var error = 0;
        if (checkSubmit() && error == 0) {
            $('#addCaseBtn').removeAttr('disabled');
        } else {
            $('#addCaseBtn').attr('disabled', true);
        }
    });
</script>
<script>
    //检查手机号
    $('#mobile').blur(function () {
        var phone = $('#mobile').val();
        if (phone.length > 0) {
            var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (!regex.test(phone)) {
                $('#addBtn').attr('disabled', true);
                $(".help-warning").show();
            }else {
                $(".help-warning").hide();
            }
        }else{
            $(".help-warning").hide();
        }
    });
</script>
</html>