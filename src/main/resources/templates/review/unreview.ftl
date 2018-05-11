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
                <li class="active">
                    <a href="#">待审核</a>
                </li>
                <li>
                    <a class="review">已审核</a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="reviewing" class="panel panel-default tab-pane active">
                    <div class="panel-body">
                        <div class="row">
                            <#if doctorList??>
                                <#list doctorList.data as doctor>
                                    <div class="col-md-4 col-lg-4" style="height:150px;padding: 5px 5px;">
                                        <div class="col-md-12 col-lg-12" style="border: 1px solid #eeeeee;height: 100%">
                                            <div class="col-md-12 col-lg-12"
                                                 style="padding: 6px 3px 3px 3px;height: 75%">
                                                <div class="pull-left" style="margin: 2px 2px 2px 12px;">
                                            <span style="font-size: 12px;">医生姓名：
                                                <span class="text-primary">${doctor.realName}</span>
                                            </span>
                                                </div>
                                                <div class="pull-right" style="margin: 2px 12px 2px 2px;">
                                            <span style="font-size: 12px;">医院：
                                                <span class="text-primary">${doctor.hospital}</span>
                                            </span>
                                                </div>
                                                <div class="col-md-12 col-lg-12" style="margin: 2px 2px 2px -5px;">
                                            <span style="font-size: 12px;">联系电话：
                                                <span class="text-primary">${doctor.mobile}</span>
                                            </span>
                                                </div>
                                                <div class="col-md-12 col-lg-12" style="margin: 2px 2px 2px -5px;">
                                                    <span style="font-size: 12px;">描述：</span>
                                                    <span class="text-primary"
                                                          style="font-size: 8px">${doctor.detials}</span>
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-lg-12" style="margin: -5px -5px -5px -5px;">
                                                <div class="pull-right">
                                                    <a id="pass" class="btn btn-primary btn-sm" role="button"
                                                       href="/doctor/pend?doctorId=${doctor.id}&action=1">通过</a>
                                                    &nbsp;&nbsp;&nbsp;
                                                    <a id="refuse" class="btn btn-default btn-sm" role="button"
                                                       href="/doctor/pend?doctorId=${doctor.id}&action=2">拒绝</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                        </div>
                    </div>
                    <nav aria-label="Page navigation" class="pull-right">
                        <ul id="paginationFirst" class="pagination">

                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="../production/jquery/jquery.min.js"></script>
<script src="../production/bootstrap/js/bootstrap.min.js"></script>
<script src="../production/bootstrap-pagenator/js/bootstrap-paginator.min.js"></script>
<script>
    $(function () {
        $('#doctor-tab a:first').tab('show');
    })
    $('#doctor-tab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    })
</script>
<script>
    var options = {
        bootstrapMajorVersion: 3, //对应的bootstrap版本
        currentPage: ${doctorList.currentPage}, //当前页数，这里是用的EL表达式，获取从后台传过来的值
        numberOfPages: ${doctorList.pageSize},//每页页数
        totalPages: ${doctorList.totalPages}, //总页数，这里是用的EL表达式，获取从后台传过来的值
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
            location.href = "/doctor/allPend?pageNum=" + page;
        }
    };
    $("#paginationFirst").bootstrapPaginator(options);

</script>

<!--审核js-->
<script>

    $(".review").click(function () {
        location.href = "/doctor/allPend?status=1"
    });

</script>
</html>