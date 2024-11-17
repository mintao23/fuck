<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>

<%
    int seq = Integer.parseInt(request.getParameter("seq"));
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");

    BoardVO board = new BoardVO();
    board.setSeq(seq);
    board.setTitle(title);
    board.setWriter(writer);
    board.setContent(content);

    BoardDAO dao = new BoardDAO();
    int result = dao.updateBoard(board);

    if (result > 0) {
        response.sendRedirect("list.jsp");
    } else {
        out.println("Error occurred while updating the post.");
    }
%>
