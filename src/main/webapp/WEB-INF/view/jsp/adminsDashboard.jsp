<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    td {
        padding-left: 20px;
        padding-right: 20px;
    }

    th {
        padding-left: 20px;
        padding-right: 20px;
    }
</style>
<html>
<body style="height: 100vh; width: 100vw; display: flex; justify-content: center; align-items: center; flex-direction: column">
<div style="position: fixed; top: 20px; right: 20px">
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Выйти"/>
    </form>
</div>
<c:choose>
    <c:when test="${uploadingFiles.size() > 0}">
        <h2>Файлы которые загружаются в настоящий момент</h2>
        <table>
            <thead>
            <tr>
                <th>Владелец</th>
                <th>Имя файла</th>
                <th>Размер (Mb)</th>
                <th>Прогресс загрузки</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="file" items="${uploadingFiles}">
                <tr>
                    <td>${file.owner}</td>
                    <td>${file.fileName}</td>
                    <td>${file.fileSize / 1000}</td>
                    <td>${file.progress} %</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        Здесь будут отображаться загружаемые файлы
    </c:otherwise>
</c:choose>
</body>
</html>