<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>
<%
    String searchType = request.getParameter("searchType");
    String keyword = request.getParameter("keyword");
    String sort = request.getParameter("sort");

    BoardDAO dao = new BoardDAO();
    List<BoardVO> boardList = dao.getBoardList(searchType, keyword, sort);
%>
<!DOCTYPE html>
<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
<h2>게시판 목록</h2>

<!-- 검색 및 정렬 폼 -->
<form action="list.jsp" method="get">
    <select name="searchType">
        <option value="title" <%= "title".equals(searchType) ? "selected" : "" %>>제목</option>
        <option value="content" <%= "content".equals(searchType) ? "selected" : "" %>>내용</option>
    </select>
    <input type="text" name="keyword" value="<%= keyword != null ? keyword : "" %>" placeholder="검색어">
    <select name="sort">
        <option value="regdate" <%= "regdate".equals(sort) ? "selected" : "" %>>등록일순</option>
        <option value="title" <%= "title".equals(sort) ? "selected" : "" %>>제목순</option>
    </select>
    <button type="submit">검색</button>
</form>

<table border="1">
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.seq}</td>
            <td><a href="view.jsp?seq=${board.seq}">${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.regDate}</td>
            <td>${board.cnt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
