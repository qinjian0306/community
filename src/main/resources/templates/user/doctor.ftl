<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>doctor</title>
    <link rel='stylesheet' href='../production/bootstrap/css/bootstrap.min.css'>
    <link rel='stylesheet' href='../production/font-awesome/css/font-awesome.min.css'>
    <link rel='stylesheet' href='../production/bootstrap-table/css/bootstrap-table.min.css'>
    <link rel="stylesheet" href="../custom/css/main.css">
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
                    <#if (user.dstatus==1)>
                      <div id="verified" class="text-success small verified">
                          已认证
                      </div>
                    </#if>
                     <#if (user.dstatus==0)>
                      <div id="validate" class="text-info text-center small" style="display: inline;">
                          <a class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal">
                              认证
                          </a>
                      </div>
                     </#if>
                      <#if (user.dstatus==3)>
                        <div id="verified" class="text-success small verified">
                            正在审核认证信息
                        </div>
                      </#if>
                </div>
            </div>
            <div class="panel-body">
                <#list list.data as case>
                  <div class="row">
                      <div class="col-md-12 col-lg-12">
                          <#if (user.dstatus==0)>
                                 <a class="pull-left" href="#" disabled="disabled">${case.title}</a>
                          </#if>
                          <#if (user.dstatus==1 && case.status==1)>
                                   <a class="pull-left" href="#">${case.title}</a>
                          </#if>
                          <#if (user.dstatus==1 && case.status!=1)>
                                   <a class="pull-left" href="/patient/viewCase?caseId=${case.id}">${case.title}</a>
                          </#if>
                         <#if (user.dstatus==3)>
                             <a class="pull-left" href="#" disabled="disabled">${case.title}</a>
                         </#if>
                          <span class="pull-right">
                              <span>
                                  <#if (case.status==1)>已关闭</#if>
                                  <#if (case.status==0)>开放中</#if>
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
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <a class="pull-left" href="./conversation.html">医生我觉得脑子好像不太好使？</a>
                        <span class="pull-right"><span>已关闭</span></span>
                        <div class="clearfix col-md-12 col-lg-12">&nbsp;</div>
                    </div>
                    <div class="col-md-12 col-lg-12" style="font-size: 10px">
                        昵称：<span>王行健</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        创建时间：<span>2018-05-01 22:23:24</span>
                        <span class="pull-right">最后回复时间：<span>2018-05-01 22:23:24</span></span>
                    </div>
                </div>
            </div>
            <nav aria-label="Page navigation" class="pull-right">
                <ul id="pagination" class="pagination">
                    <!--<li>-->
                    <!--<a class="leftPage" href="#" aria-label="Previous">-->
                    <!--<span aria-hidden="true">&laquo;</span>-->
                    <!--</a>-->
                    <!--</li>-->
                    <!--<li><a class="pageNo" href="#">1</a></li>-->
                    <!--<li><a class="pageNo" href="#">2</a></li>-->
                    <!--<li><a class="pageNo" href="#">3</a></li>-->
                    <!--<li><a class="pageNo" href="#">4</a></li>-->
                    <!--<li><a class="pageNo" href="#">5</a></li>-->
                    <!--<li>-->
                    <!--<a class="rightPage" href="#" aria-label="Next">-->
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
                <h4 class="modal-title" id="myModalLabel">添加认证信息</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" method="post" class="form-horizontal" role="form" style="padding: 30px 100px 10px;">
                    <div class="form-group">
                        <div class="col-md-9 col-lg-9">
                            <input type="hidden" class="form-control" id="userId" value="0" name="userId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hospital" class="col-md-3 col-lg-3 control-label">医院</label>
                        <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control" id="hospital" name="hospital">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="realName" class="col-md-3 col-lg-3 control-label">真实姓名</label>
                        <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control required" id="realName" name="realName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-md-3 col-lg-3 control-label">电话</label>
                        <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control required" id="mobile" name="mobile">
                        </div>
                        <label class="help-block help-warning col-md-offset-3 col-lg-offset-3" style="display:none">
                            <span class="text-warning">
                                <i class="fa fa-close">&nbsp;</i>手机号不合法，请重新输入
                            </span>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="detail" class="col-md-3 col-lg-3 control-label">介绍</label>
                        <div class="col-md-9 col-lg-9">
                            <textarea id="detail" name="detail" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="addBtn" type="button" disabled class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

</body>
<script src="../production/jquery/jquery.min.js"></script>
<script src="../production/bootstrap/js/bootstrap.min.js"></script>
<script src="../production/bootstrap-pagenator/js/bootstrap-paginator.min.js"></script>
<script>
    $("#addBtn").click(function () {
        $("#addForm").submit();
    });
    $("#addForm").on("submit", function () {
        $.ajax({
            url: "/doctor/verify",
            method: "post",
            data: $("#addForm").serialize(),
            success: function (data) {
                location.href = "/doctor/getCaseList";
            },
            error: function (data) {
            }
        });
    });

</script>
<script>
    var options = {
        bootstrapMajorVersion: 3, //对应的bootstrap版本
        currentPage: ${list.currentPage}, //当前页数，这里是用的EL表达式，获取从后台传过来的值
        numberOfPages: ${list.pageSize},//每页页数
        totalPages: ${list.totalPages},  //总页数，这里是用的EL表达式，获取从后台传过来的值
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
            location.href = "/doctor/getCaseList?pageNum=" + page;
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
            $('#addBtn').removeAttr('disabled');
        } else {
            $('#addBtn').attr('disabled', true);
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