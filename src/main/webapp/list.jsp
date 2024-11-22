<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>
<%@ page import="java.util.List" %>
<%
    String searchKeyword = request.getParameter("searchKeyword"); // 검색어를 받기 위한 변수
    BoardDAO dao = new BoardDAO();

    // 게시글 목록을 가져오는 부분에서 검색어가 있을 경우 필터링
    List<BoardVO> boardList = dao.getBoardList(searchKeyword); // 수정된 DAO 메소드 사용
%>

<!DOCTYPE html>
<html>
<head>
    <title>게시판 목록</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 8px;
        }

        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
<h2>게시판 목록</h2>

<!-- 검색 폼 -->
<form action="list.jsp" method="get">
    <input type="text" name="searchKeyword" value="<%= searchKeyword != null ? searchKeyword : "" %>" placeholder="제목 또는 내용 검색">
    <button type="submit">검색</button>
</form>

<table>
    <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
        <th>상세보기</th>
        <th>기타</th> <!-- 새로운 열 추가 -->
    </tr>

    <% for (BoardVO board : boardList) { %>
    <tr>
        <td><%= board.getTitle() %></td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getRegDate() %></td>
        <td><%= board.getCnt() %></td>
        <td><a href="view.jsp?seq=<%= board.getSeq() %>">상세보기</a></td>
        <td>
            <!-- 수정 및 삭제 버튼 -->
            <a href="edit.jsp?seq=<%= board.getSeq() %>">수정</a>
            |
            <a href="delete_ok.jsp?seq=<%= board.getSeq() %>" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
        </td>
    </tr>
    <% } %>
</table>

<a href="write.jsp">새 글 작성</a>
</body>
</html>
