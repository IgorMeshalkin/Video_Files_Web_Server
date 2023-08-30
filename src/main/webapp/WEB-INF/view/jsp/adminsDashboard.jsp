<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
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