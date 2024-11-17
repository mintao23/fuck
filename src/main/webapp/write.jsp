<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Write a Post</title>
</head>
<body>
<h1>Write a New Post</h1>
<form action="write_ok.jsp" method="post">
    <label>Title:</label>
    <input type="text" name="title"><br>
    <label>Writer:</label>
    <input type="text" name="writer"><br>
    <label>Content:</label>
    <textarea name="content"></textarea><br>
    <button type="submit">Submit</button>
</form>
</body>
</html>
