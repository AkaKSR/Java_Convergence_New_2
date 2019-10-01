package kr.green.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kr.green.board.vo.CommentVO;
import kr.green.jdbc.JdbcUtil;

public class CommentDAO {
	private static CommentDAO instance = new CommentDAO();
	
	private CommentDAO() {}
	public static CommentDAO getInstance() {
		return instance;
	}
	
	private static Logger logger = Logger.getLogger(CommentDAO.class);
	
	// 1. 해당 번호의 댓글의 개수 구하기
	public int selectByIdxCount(Connection conn, int ref) throws SQLException {
		int count = 0;
		logger.debug("CommentDAO selectByIdxCount 인수 : " + conn + ", " + ref);
		String sql = "select count(*) from free_comment where ref=" + ref;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			count = rs.getInt(1);
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		logger.debug("CommentDAO selectByIdxCount 리턴값 : " + count);
		return count;
	}
	// 2. 해당 번호의 댓글 목록 구하기
	public List<CommentVO> selectByIdxList(Connection conn, int ref) throws SQLException {
		List<CommentVO> list = null;
		logger.debug("CommentDAO selectByIdxList 인수 : " + conn + ", " + ref);
		String sql = "select * from free_comment where ref=" + ref + " order by idx desc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			list = new ArrayList<CommentVO>();
			do {
				CommentVO vo = makeVO(rs);
				list.add(vo);
			} while (rs.next());
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		logger.debug("CommentDAO selectByIdxList 리턴값 : " + list);
		return list;
	}
	// 3. 1개 얻기
	public CommentVO selectByIdx(Connection conn, int idx) throws SQLException {
		CommentVO vo = null;
		logger.debug("CommentDAO selectByIdx 인수 : " + conn + ", " + idx);
		String sql = "select * from free_comment where idx=" + idx;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			vo = makeVO(rs);
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		logger.debug("CommentDAO selectByIdx 리턴값 : " + vo);
		return vo;
	}
	// ResultSet으로 VO를 만들어 리턴하는 메서드
	private CommentVO makeVO(ResultSet rs) throws SQLException {
		logger.debug("CommentDAO makeVO 인수 : " + rs);
		CommentVO vo = new CommentVO();
		vo.setIdx(rs.getInt("idx"));
		vo.setRef(rs.getInt("ref"));
		vo.setName(rs.getString("name"));
		vo.setPassword(rs.getString("password"));
		vo.setContent(rs.getString("content"));
		vo.setRegDate(rs.getTimestamp("regDate"));
		logger.debug("CommentDAO makeVO 리턴값 : " + vo);		
		return vo;
	}
	// 4. 저장하기
	public int insert(Connection conn, CommentVO vo) throws SQLException {
		int count = 0;
		logger.debug("CommentDAO insert 인수 : " + conn + ", " + vo);
		String sql = "insert into free_comment (ref,name,password,content) values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, vo.getRef());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getPassword());
		pstmt.setString(4, vo.getContent());
		count = pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
		logger.debug("CommentDAO insert 리턴값 : " + vo);
		return count;
	}
	// 5. 수정하기
	public int update(Connection conn, CommentVO vo) throws SQLException {
		int count = 0;
		logger.debug("CommentDAO update 인수 : " + conn + ", " + vo);
		String sql = "update free_comment set content=?, regDate=now() where idx=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setInt(2, vo.getIdx());
		count = pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
		logger.debug("CommentDAO update 리턴값 : " + vo);
		return count;
	}
	// 6. 삭제하기
	public int delete(Connection conn, int idx) throws SQLException {
		int count = 0;
		logger.debug("CommentDAO delete 인수 : " + conn + ", " + idx);
		String sql = "delete from free_comment where idx=" + idx;
		Statement stmt = conn.createStatement();
		count = stmt.executeUpdate(sql);
		JdbcUtil.close(stmt);
		logger.debug("CommentDAO delete 리턴값 : " + count);
		return count;
	}
}
