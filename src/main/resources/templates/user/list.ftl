<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>用户id</th>
                            <th>用户名</th>
                            <th>性别</th>
                            <th>电话</th>
                            <th>邮箱</th>
                            <th>地址</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list userInfoList as user>
                        <tr>
                            <td>${user.userId}</td>
                            <td>${user.userName}</td>
                            <td>${user.sex}</td>
                            <td>${user.phone}</td>
                            <td>${user.email}</td>
                            <td>${user.address}</td>
                            <td>${user.createTime}</td>
                            <td>${user.updateTime}</td>
                            <td><a href="/lovenote/user/manage/edit?userId=${user.userId}">修改</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>