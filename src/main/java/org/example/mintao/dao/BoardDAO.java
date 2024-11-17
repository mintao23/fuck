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

    private final String BOARD_INSERT = "insert into BOARD (title, writer, content) values (?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set title=?, writer=?, content=? where seq=?";
    private final String BOARD_DELETE = "delete from BOARD where seq=?";
    private final String BOARD_GET = "select * from BOARD where seq=?";
    private final String BOARD_LIST = "select * from BOARD order by seq desc";

    public int insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_INSERT);
            pst.setString(1, vo.getTitle());
            pst.setString(2, vo.getWriter());
            pst.setString(3, vo.getContent());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void deleteBoard(int seq) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_DELETE);
            pst.setInt(1, seq);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pst, conn);
        }
    }

    public int updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(BOARD_UPDATE);
            pst.setString(1, vo.getTitle());
            pst.setString(2, vo.getWriter());
            pst.setString(3, vo.getContent());
            pst.setInt(4, vo.getSeq());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.close(pst, conn);
        }
    }

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pst, conn);
        }
        return board;
    }

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
                board.setRegDate(rs.getDate("regDate"));
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pst, conn);
        }
        return boardList;
    }
}
