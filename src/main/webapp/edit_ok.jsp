<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>
<%@ page import="org.example.mintao.util.FileUpload" %>
<%
    int seq = Integer.parseInt(request.getParameter("seq"));
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");

    // 파일 업로드 처리
    String uploadPath = application.getRealPath("/uploads");
    String photo = FileUpload.uploadFile(request, uploadPath);

    // VO 객체 생성 및 데이터 설정
    BoardVO vo = new BoardVO();
    vo.setSeq(seq);
    vo.setTitle(title);
    vo.setWriter(writer);
    vo.setContent(content);
    vo.setPhoto(photo);

    // DAO를 통해 데이터 업데이트
    BoardDAO dao = new BoardDAO();
    int result = dao.updateBoard(vo);

    if (result > 0) {
%>
<script>
    alert("글이 성공적으로 수정되었습니다!");
    location.href = "list.jsp";
</script>
<%
} else {
%>
<script>
    alert("글 수정에 실패했습니다.");
    history.back();
</script>
<%
    }
%>
