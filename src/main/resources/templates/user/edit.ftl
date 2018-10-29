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
                    <form role="form" method="post" action="/lovenote/user/manage/save">
                        <div class="form-group">
                            <label>用户名</label>
                            <input name="userName" type="text" class="form-control"
                                   value="${(userInfo.getUserName())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <input name="sex" type="number" class="form-control" value="${(userInfo.getSex())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>电话</label>
                            <input name="phone" type="text" class="form-control" value="${(userInfo.getPhone())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>邮箱</label>
                            <input name="email" type="text" class="form-control" value="${(userInfo.getEmail())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input name="address" type="text" class="form-control"
                                   value="${(userInfo.getAddress())!''}"/>
                        </div>

                        <input hidden type="text" name="userId" value="${(userInfo.getUserId())!''}">
                        <input hidden type="text" name="password" value="${(userInfo.getPassword())!''}">
                        <input hidden type="text" name="userIcon" value="${(userInfo.getUserIcon())!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>