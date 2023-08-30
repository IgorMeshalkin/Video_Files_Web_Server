<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body style="height: 100vh; width: 100vw; display: flex; justify-content: center; align-items: center; flex-direction: column">
<div style="position: fixed; top: 20px; right: 20px">
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Выйти"/>
    </form>
</div>
<div style="position: fixed; top: 20px; left: 20px">
    <form action="/" method="get">
        <input type="submit" value="На главную"/>
    </form>
</div>
<div style="position: fixed; top: 50px; right: 20px">
    <form action="/admin" method="get" style="display: ${currentUser.equals("admin") ? "auto" : "none"}">
        <input type="submit" value="Просмотреть загружаемые файлы"/>
    </form>
</div>
<h3>Загрузить файл на сервер:</h3>
<form action="/loading/uploadFile" method="post" enctype="multipart/form-data">
    <input id="selectButton" style="transition: .3s; margin-top: 20px" type="file" name="file" size="50"/>
    <br/>
    <input id="submitButton" type="submit" style="margin-top: 20px" value="Загрузить"/>
</form>
</body>
</html>
<script type="text/javascript">
    <%@include file="/WEB-INF/view/js/upload.js"%>
</script>