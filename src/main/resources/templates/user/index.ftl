<html>
<#include "../common/header.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">


            <h2>登录</h2>

            <form role="form"  action="/lovenote/user/manage/login" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">手机号</label>
                    <input type="text" class="form-control" name="phone"/>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input   type="text" class="form-control" name="password" />
                </div>
                <div class="checkbox">
                    <label><input type="checkbox"/>记住密码</label>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>