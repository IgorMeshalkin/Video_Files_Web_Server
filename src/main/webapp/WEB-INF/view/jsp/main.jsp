<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br/>
<form action="uploadFile" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>
<table>
    <tbody>
    <c:forEach var="file" items="${actualFiles}">

        <c:url var="downloadButton" value="/download">
            <c:param name="filePath" value="${file.filePath}"/>
            <c:param name="fileName" value="${file.fileName}"/>
        </c:url>

        <tr>
            <td>${file.fileName}</td>
            <td>${file.fileSize}</td>
            <td>
                <input type="button" value="Скачать"
                       onclick="window.location.href = '${downloadButton}'">
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
