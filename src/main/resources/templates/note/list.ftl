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
                            <th>笔记id</th>
                            <th>用户id</th>
                            <th>内容</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list noteList as note>
                        <tr>
                            <td>${note.noteId}</td>
                            <td>${note.userId}</td>
                            <td>${note.noteText}</td>
                            <td>${note.createTime}</td>
                            <td>${note.updateTime}</td>
                            <td><a href="/lovenote/note/manage/index?noteId=${note.noteId}">修改</a></td>
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