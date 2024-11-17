<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.mintao.dao.BoardDAO" %>

<%
    int seq = Integer.parseInt(request.getParameter("seq"));

    BoardDAO dao = new BoardDAO();
    dao.deleteBoard(seq);

    response.sendRedirect("list.jsp");
%>
