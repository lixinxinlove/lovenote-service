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
                    <form role="form" method="post" action="/lovenote/note/manage/save">
                        <div class="form-group">
                            <label>用户id</label>
                            <input name="userId" type="text" class="form-control" value="${(note.getUserId())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>Id</label>
                            <input name="noteId" type="number" class="form-control" value="${(note.getNoteId())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>内容</label>
                            <input name="noteText" type="text" class="form-control" value="${(note.getNoteText())!''}"/>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>