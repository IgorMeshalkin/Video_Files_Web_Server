<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<style>
    .mainContainer {
        height: 100vh;
        width: 100vw;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>
<body>
<div class="mainContainer">
    <form action="/login" method="post">
        <div align="center"><label> Имя пользователя : <input type="text" name="username"/> </label></div>
        <br>
        <div align="center" hidden><label> Password: <input type="password" name="password" value="" readonly/> </label>
        </div>
        <br>
        <div align="center"><input type="submit" value="Войти"/></div>
    </form>
</div>
</body>
</html>