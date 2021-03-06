<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>conversation</title>
    <link rel='stylesheet' href='../production/bootstrap/css/bootstrap.min.css'>
    <link rel='stylesheet' href='../production/font-awesome/css/font-awesome.min.css'>
    <link rel='stylesheet' href='../production/bootstrap-table/css/bootstrap-table.min.css'>
    <link rel="stylesheet" href="../custom/css/main.css">
    <style>
        .dropdown {
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
<div class="container-fluid" style="margin: 10px;margin-top: -5px">
    <form id="replayForm" action="/replay/save" method="post">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <!--封装数据-->
                            <input type="hidden" id="caseId" name="caseId" value="${case.id}">
                            <input type="hidden" id="userId" name="userId" value="${user.id}">

                            <div class="col-md-3 col-lg-3 col-xs-3">
                                <span class="pull-left">${case.title}</span>
                                <div class="clearfix col-md-3 col-lg-3">&nbsp;</div>
                            </div>

                            <div class="col-md-6 col-lg-6 col-xs-6">
                                  <#if (case.urls)??>
                                      <#list (case.urls) as url>
                                         <div style="width: 125px;height: 40px;display: inline-block" class="text-center">
                                             <a href="#" class="thumbnail imgA">
                                                 <img class="imgThumb" src="${url}">
                                                      <#--alt="通用的占位符缩略图">-->
                                                 </img>
                                             </a>
                                         </div>
                                      </#list>
                                  </#if>
                                <#--<div style="width: 40px;height: 40px;display: inline-block" class="text-center">-->
                                    <#--<a href="#" class="thumbnail imgA">-->
                                        <#--<img class="imgThumb" src="../fileupload/abc.png"-->
                                             <#--alt="通用的占位符缩略图"></img>-->
                                    <#--</a>-->
                                <#--</div>-->
                                <#--<div style="width: 40px;height: 40px;display: inline-block" class="text-center">-->
                                    <#--<a href="#" class="thumbnail imgA">-->
                                        <#--<img class="imgThumb" src="../fileupload/abc.png"-->
                                             <#--alt="通用的占位符缩略图"></img>-->
                                    <#--</a>-->
                                <#--</div>-->
                                <!--<img src="../fileupload/abc.png">-->
                            </div>
                            <div class="col-md-3 col-lg-3 col-xs-3">
                                <span class="pull-right"
                                      style="font-size: 8px">创建时间：<span>${case.createTimeStr?string("yyyy-MM-dd HH:mm")}</span></span>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-12" style="font-size: 8px;">
                            患者：<span>${case.patient}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            性別：<span>
                                <#if (case.gender == 1)>男</#if>
                                 <#if (case.gender == 2)>女</#if>
                        </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            电话：<span>${case.contact}</span>
                            <!--<hr style="border:1px dashed #5c6254">-->
                            <hr style="height:1px;border:none;border-top: 1px dashed #5c6254;">
                        </div>
                        <div class="col-md-12 col-lg-12" style="font-size: 8px;margin-left: 5px;">
                            <p>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${case.description}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="panel-body conversation">
                <#if replyList??>
                    <#list replyList as reply>
                        <#if (reply.role==1)>
                                 <div class="row row-left">
                                     <div class="col-md-3 col-lg-3 pull-left text-left" style="margin-left: -12px">
                                         <div class="col-lg-12 col-md-12" style="font-size: 4px">
                                 <#if (reply.role==1)>医生：<span>${reply.nickname}</span></#if>
                                 <#if (reply.role==2)>患者：<span>${reply.nickname}</span></#if>
                                         </div>
                                         <div class="col-lg-12 col-md-12" style="font-size: 1px; color: gray">
                                             ${reply.createTimeStr?string("yyyy-MM-dd HH:mm")}
                                         </div>
                                     </div>
                                     <div class="col-md-9 col-lg-9 col-md-pull-1"
                                          style="font-size: 10px;margin-left: -10px">
                                         <div class="left"></div>
                                         <div class="radius">
                                             <p>${reply.content}</p>
                                         </div>
                                     </div>
                                 </div>
                    <div class="clearfix col-md-12 col-lg-12">&nbsp;</div>
                        </#if>

                        <#if (reply.role==2)>

                     <div class="row row-right">
                         <div class="col-md-9 col-lg-9 col-md-push-1 col-lg-push-1"
                              style="font-size: 10px;margin-right: -15px">
                             <div class="radius">
                                 <p>${reply.content}</p>
                             </div>
                             <div class="right"></div>
                         </div>
                         <div class="col-md-3 col-lg-3 pull-right text-right" style="margin-right: -12px">
                             <div class="col-lg-12 col-md-12" style="font-size: 4px">
                                       <#if (reply.role==1)>医生：<span>${reply.nickname}</span></#if>
                                 <#if (reply.role==2)>患者：<span>${reply.nickname}</span></#if>
                             </div>
                             <div class="col-lg-12 col-md-12" style="font-size: 1px; color: gray">
                                 ${reply.createTimeStr?string("yyyy-MM-dd HH:mm")}
                             </div>
                         </div>
                     </div>
                <div class="clearfix col-md-12 col-lg-12">&nbsp;</div>
                        </#if>



                    </#list>
                </#if>

                </div>
                <hr>
            <#if case.status!=1>
                 <div class="panel-body">
                     <div class="row" style="margin: -30px;margin-top: -32px;">
                         <div class="col-md-12 col-lg-12">
                        <textarea id="conversation-detail" name="content" class="col-md-12 col-lg-12"
                                  style="border: 0px" placeholder="正在输入中..."></textarea>
                         </div>
                         <div class="col-md-12 col-lg-12">
                             <div class="pull-right" style="margin-right: 20px;margin-top: 5px;">
                                 <button id="clearBtn" type="button" class="btn btn-default btn-sm"
                                         style="margin-right: 15px">清空
                                 </button>
                                 <button id="submitBtn" type="button" class="btn btn-info btn-sm">提交</button>
                             </div>
                         </div>
                     </div>
                 </div>
            </#if>
            </div>
        </div>

    </form>
</div>
<div id="previewDiv" class="text-center" style="width: 600px;height: 500px;z-index: -1;position: fixed;left: 25%;top: 25%; display: none">
    <img id="previewImg" style="width: 600px;height: 450px" src=""
         <#--alt="通用的占位符缩略图">-->
</div>
</body>
<script src="../production/jquery/jquery.min.js"></script>
<script src="../production/bootstrap/js/bootstrap.min.js"></script>
<script>

    $('#clearBtn').click(function () {
        $('#conversation-detail').val("");
    });


    $('#submitBtn').click(function () {
        $('#replayForm').submit();
    });
</script>
<script>

        $(".imgA").mouseover(function () {
            var src = $(this).children("img").attr("src");
            $("#previewImg").attr("src", src);
            $("#previewDiv").css("z-index", "9999");
            $("#previewDiv").show();
        });

        $(".imgA").mouseout(function () {
            var src = $(this).children("img").attr("src");
            $("#previewImg").attr("src", "");
            $("#previewDiv").css("z-index", "-1");
            $("#previewDiv").css("z-index", "9999");
            $("#previewDiv").hide();

        });
        $(".imgA").click(function () {
            var src = $(this).children("img").attr("src");
            var separatorIndex = src.lastIndexOf("/");
            src = src.substring(separatorIndex+1);
            location.href = "../patient/downloadCase?url="+src;
        });

</script>
</html>