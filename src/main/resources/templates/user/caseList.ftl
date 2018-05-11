<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>verity</title>
    <link rel='stylesheet' href='../production/bootstrap/css/bootstrap.min.css'>
    <link rel='stylesheet' href='../production/font-awesome/css/font-awesome.min.css'>
    <link rel='stylesheet' href='../production/bootstrap-table/css/bootstrap-table.min.css'>
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
        <div class="span12">
            <ul id="doctor-tab" class="nav nav-tabs">
                <li>
                    <a class="unreview">待审核</a>
                </li>
                <li>
                    <a class="review">已审核</a>
                </li>
                <li class="active">
                    <a href="">病例页</a>
                </li>
            </ul>
            <div class="panel-body">
                <#list list.data as case>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <a class="pull-left" href="/patient/viewCase?caseId=${case.id}">${case.title}</a>
                            <span class="pull-right">
                              <span>
                                  <#if (case.status==1)>已关闭</#if>
                                  <#if (case.status==0)>开放中</#if>&nbsp;&nbsp;&nbsp;&nbsp;
                                  <div id="validate" class="text-info text-center small" style="display: inline; font-size: 16px">
                                                <a href="/patient/removeCase?caseId=${case.id}" class="btn btn-xs btn-primary">删除病历</a>
                                            </div>
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
                </ul>
            </nav>
        </div>
    </div>
</div>

</body>
<script src="../production/jquery/jquery.min.js"></script>
<script src="../production/bootstrap/js/bootstrap.min.js"></script>
<script src="../production/bootstrap-pagenator/js/bootstrap-paginator.min.js"></script>
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
            location.href = "/admin/getCaseList?pageNum=" + page;
        }
    };
    $("#pagination").bootstrapPaginator(options);

</script>

<!--审核js-->
<script>

    $(".review").click(function () {
        location.href = "/doctor/allPend?status=1"
    });

    $(".unreview").click(function () {
        location.href = "/doctor/allPend?status=3"
    });

</script>
</html>