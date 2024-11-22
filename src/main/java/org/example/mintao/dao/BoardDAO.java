package org.example.mintao.dao;

import org.example.mintao.bean.BoardVO;
import org.example.mintao.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // SQL 쿼리 수정
    private final String BOARD_INSERT = "insert into BOARDP (title, writer, content, photo) values (?,?,?,?)";
    private final String BOARD_UPDATE = "update BOARDP set title=?, writer=?, content=?, photo=? where seq=?";
    private final String BOARD_DELETE = "delete from BOARDP where seq=?";
    private final String BOARD_GET = "select * from BOARDP where seq=?";
    private final String BOARD_LIST = "select * from BOARDP order by seq desc";

    // 추가: 게시글 삽입
    public int insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_INSERT);
            pst.setString(1, vo.getTitle());
            pst.setString(2, vo.getWriter());
            pst.setString(3, vo.getContent());
            pst.setString(4, vo.getPhoto()); // photo 컬럼 처리
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.close(pst, conn);
        }
    }

    public int updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();

            // 기존 파일명이 유지되는지 확인
            String currentPhoto = getCurrentPhoto(vo.getSeq());
            System.out.println("현재 파일명: " + currentPhoto);  // 로그 추가

            pst = conn.prepareStatement(BOARD_UPDATE);
            pst.setString(1, vo.getTitle());
            pst.setString(2, vo.getWriter());
            pst.setString(3, vo.getContent());
            pst.setString(4, (vo.getPhoto() == null || vo.getPhoto().isEmpty()) ? currentPhoto : vo.getPhoto());  // 사진 정보 처리
            pst.setInt(5, vo.getSeq());
            System.out.println("Fuck");
            int result = pst.executeUpdate();
            System.out.println("업데이트 결과: " + result);  // 로그 추가
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.close(pst, conn);
        }
    }

    // 현재 게시글의 파일 정보를 가져오는 메소드
    private String getCurrentPhoto(int seq) {
        String photo = null;
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement("SELECT photo FROM BOARDP WHERE seq = ?");
            pst.setInt(1, seq);
            rs = pst.executeQuery();
            if (rs.next()) {
                photo = rs.getString("photo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pst, conn);
        }
        return photo;
    }


    // 추가: 게시글 상세 보기
    public BoardVO getBoard(int seq) {
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        BoardVO board = null;
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_GET);
            pst.setInt(1, seq);
            rs = pst.executeQuery();
            if (rs.next()) {
                board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regDate"));
                board.setCnt(rs.getInt("cnt"));
                board.setPhoto(rs.getString("photo"));  // 파일 정보 추가
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pst, conn);
        }
        return board;
    }

    // 추가: 게시글 목록 조회
    public List<BoardVO> getBoardList() {
        System.out.println("===> JDBC로 getBoardList() 기능 처리");
        List<BoardVO> boardList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_LIST);
            rs = pst.executeQuery();
            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regdate"));
                board.setCnt(rs.getInt("cnt"));
                board.setPhoto(rs.getString("photo")); // photo 데이터 설정
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pst, conn);
        }
        return boardList;
    }
    public List<BoardVO> getBoardList(String searchKeyword) {
        System.out.println("===> JDBC로 getBoardList() 기능 처리");
        List<BoardVO> boardList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();

            String sql = "select * from BOARDP ";

            // 검색어가 있을 경우, SQL 쿼리 수정
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                sql += "where title like ? or content like ? ";
            }

            sql += "order by seq desc"; // 기본적으로 시퀀스 순으로 정렬

            pst = conn.prepareStatement(sql);

            // 검색어가 있을 경우, ? 값 설정
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                pst.setString(1, "%" + searchKeyword + "%");
                pst.setString(2, "%" + searchKeyword + "%");
            }

            rs = pst.executeQuery();
            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regDate"));
                board.setCnt(rs.getInt("cnt"));
                board.setPhoto(rs.getString("photo")); // photo 데이터 설정
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pst, conn);
        }
        return boardList;
    }

    public void incrementViewCount(int seq) {
        System.out.println("===> JDBC로 조회수 증가 기능 처리");
        String query = "UPDATE BOARDP SET cnt = cnt + 1 WHERE seq = ?";
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, seq);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pst, conn);
        }
    }

    // 추가: 게시글 삭제
    public void deleteBoard(int seq) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_DELETE);
            pst.setInt(1, seq); // 삭제할 게시글의 seq 설정
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pst, conn);
        }
    }


}
