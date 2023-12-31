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
    <form action="/logout" method="post">
        <input type="submit" value="Выйти"/>
    </form>
</div>
<div style="position: fixed; top: 50px; right: 20px">
    <form action="/admin" method="get" style="display: ${currentUser.equals("admin") ? "auto" : "none"}">
        <input type="submit" value="Просмотреть загружаемые файлы"/>
    </form>
</div>
<c:choose>
    <c:when test="${actualFiles.size() > 0}">
        <h3>Ваши файлы:</h3>
        <table>
            <thead>
            <tr>
                <th>Название</th>
                <th>Размер (Mb)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="file" items="${actualFiles}">

                <c:url var="downloadButton" value="/loading/download">
                    <c:param name="filePath" value="${file.filePath}"/>
                    <c:param name="fileName" value="${file.fileName}"/>
                </c:url>

                <tr>
                    <td>${file.fileName}</td>
                    <td>${file.fileSize / 1000}</td>
                    <td>
                        <input type="button" value="Скачать"
                               onclick="window.location.href = '${downloadButton}'">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        Здесь будут отображаться ваши файлы
    </c:otherwise>
</c:choose>
<form action="/loading" method="get">
    <input style="margin-top: 20px" type="submit" value="Перейти к загрузке файлов"/>
</form>
</body>
</html>
