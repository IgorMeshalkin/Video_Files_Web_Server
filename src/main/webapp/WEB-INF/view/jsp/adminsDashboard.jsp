<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body style="height: 100vh; width: 100vw; display: flex; justify-content: center; align-items: center; flex-direction: column">
<div style="position: fixed; top: 20px; right: 20px">
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Выйти"/>
    </form>
</div>
<h2>Файлы которые загружаются в настоящий момент</h2>
<table>
    <tbody>
    <c:forEach var="file" items="${uploadingFiles}">
        <tr>
            <td>${file.owner}</td>
            <td>${file.fileName}</td>
            <td>${file.fileSize}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>