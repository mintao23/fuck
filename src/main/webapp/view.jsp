<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>
<%
    int seq = Integer.parseInt(request.getParameter("seq"));
    BoardDAO dao = new BoardDAO();

    // 조회수 증가 처리
    dao.incrementViewCount(seq);

    // 게시글 정보 가져오기
    BoardVO board = dao.getBoard(seq);  // 게시글 정보 가져오기
%>

<!DOCTYPE html>
<html>
<head>
    <title>게시글 보기</title>
</head>
<body>
<h2>게시글 상세보기</h2>

<table border="1">
    <tr>
        <th>제목</th>
        <td><%= board.getTitle() %></td>
    </tr>
    <tr>
        <th>작성자</th>
        <td><%= board.getWriter() %></td>
    </tr>
    <tr>
        <th>등록일</th>
        <td><%= board.getRegDate() %></td>
    </tr>
    <tr>
        <th>조회수</th>
        <td><%= board.getCnt() %></td>
    </tr>
    <tr>
        <th>내용</th>
        <td><%= board.getContent() %></td>
    </tr>
    <tr>
        <th>첨부파일</th>
        <td>
            <% if (board.getPhoto() != null && !board.getPhoto().isEmpty()) {
                String filePath = "uploads/" + board.getPhoto();
                String fileExtension = board.getPhoto().substring(board.getPhoto().lastIndexOf(".") + 1).toLowerCase();

                if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png") || fileExtension.equals("gif")) { %>
            <!-- 이미지 파일일 경우 미리보기 -->
            <img src="<%= filePath %>" alt="이미지 미리보기" style="max-width: 200px; max-height: 200px;">
            <% } else { %>
            <!-- 이미지가 아니면 다운로드 링크 제공 -->
            <a href="<%= filePath %>" target="_blank">파일 다운로드</a>
            <% }
            } else { %>
            첨부파일 없음
            <% } %>
        </td>
    </tr>
</table>

<br>
<a href="list.jsp">목록으로</a>
</body>
</html>
