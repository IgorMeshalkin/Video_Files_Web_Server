<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .background {
        height: 100vh;
        width: 100vw;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }

    .backButton {
        position: fixed;
        top: 20px;
        left: 20px;
    }

    .message {
        color: red;
        font-size: 24px;
        font-weight: bold;
    }

</style>
<html>
<body>
<div style="position: fixed; top: 20px; right: 20px">
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Выйти"/>
    </form>
</div>
<div class="background">
    <button onclick="window.location.href = '/loading'" class="backButton">Назад</button>
    <span class="message">
        <%= request.getAttribute("message") %>
    </span>
</div>
</tbody>
</body>
</html>