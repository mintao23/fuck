<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>

<%
    BoardDAO dao = new BoardDAO();
    List<BoardVO> boardList = dao.getBoardList();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Board List</title>
</head>
<body>
<h1>Board List</h1>
<a href="write.jsp">Write a New Post</a>
<table border="1">
    <tr>
        <th>Seq</th>
        <th>Title</th>
        <th>Writer</th>
        <th>RegDate</th>
        <th>Action</th>
    </tr>
    <%
        for (BoardVO board : boardList) {
    %>
    <tr>
        <td><%= board.getSeq() %></td>
        <td><a href="view.jsp?seq=<%= board.getSeq() %>"><%= board.getTitle() %></a></td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getRegDate() %></td>
        <td>
            <a href="edit.jsp?seq=<%= board.getSeq() %>">Edit</a>
            <a href="delete_ok.jsp?seq=<%= board.getSeq() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
