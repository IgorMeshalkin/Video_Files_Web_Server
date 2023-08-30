<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body style="height: 100vh; width: 100vw; display: flex; justify-content: center; align-items: center; flex-direction: column">
<div style="position: fixed; top: 20px; right: 20px">
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Выйти"/>
    </form>
</div>
<h3>Загрузить файл на сервер:</h3>
Выберите файл для загрузки: <br/>
<form action="/loading/uploadFile" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="submit" value="Загрузить"/>
</form>
</body>
</html>