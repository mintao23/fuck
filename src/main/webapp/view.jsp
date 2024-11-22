<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>
<%
    int seq = Integer.parseInt(request.getParameter("seq"));
    BoardDAO dao = new BoardDAO();

    // 조회수 증가 처리
    dao.incrementViewCount(seq);

    // 글 정보 가져오기
    BoardVO board = dao.getBoard(seq);
%>
<!DOCTYPE html>
<html>
<head>
    <title>글 상세보기</title>
</head>
<body>
<h2>글 상세보기</h2>
<p>제목: <%= board.getTitle() %></p>
<p>작성자: <%= board.getWriter() %></p>
<p>내용: <%= board.getContent() %></p>
<p>등록일: <%= board.getRegDate() %></p>
<p>조회수: <%= board.getCnt() %></p>
<a href="list.jsp">목록으로</a>
</body>
</html>
