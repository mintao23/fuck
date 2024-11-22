<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>
<%
    int seq = Integer.parseInt(request.getParameter("seq"));
    BoardDAO dao = new BoardDAO();
    BoardVO vo = dao.getBoard(seq);
%>
<!DOCTYPE html>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
<h2>글 수정</h2>
<form action="edit_ok.jsp" method="post" enctype="multipart/form-data">
    <input type="hidden" name="seq" value="<%= vo.getSeq() %>">

    <label for="title">제목:</label>
    <input type="text" name="title" id="title" value="<%= vo.getTitle() %>"><br>

    <label for="writer">작성자:</label>
    <input type="text" name="writer" id="writer" value="<%= vo.getWriter() %>"><br>

    <label for="content">내용:</label>
    <textarea name="content" id="content" rows="5"><%= vo.getContent() %></textarea><br>

    <label for="photo">첨부 파일 (기존: <%= vo.getPhoto() %>):</label>
    <input type="file" name="photo" id="photo"><br><br>

    <button type="submit">수정하기</button>
</form>
</body>
</html>
