<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>글 작성</title>
</head>
<body>
<h2>글 작성</h2>
<form action="write_ok.jsp" method="post" enctype="multipart/form-data">
    <label for="title">제목:</label>
    <input type="text" name="title" id="title" required><br>

    <label for="writer">작성자:</label>
    <input type="text" name="writer" id="writer" required><br>

    <label for="content">내용:</label>
    <textarea name="content" id="content" rows="5" required></textarea><br>

    <label for="photo">첨부 파일:</label>
    <input type="file" name="photo" id="photo"><br><br>

    <button type="submit">작성하기</button>
</form>
</body>
</html>
