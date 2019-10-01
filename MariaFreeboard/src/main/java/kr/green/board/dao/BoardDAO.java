package kr.green.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kr.green.board.vo.BoardVO;
import kr.green.jdbc.JdbcUtil;

public class BoardDAO {
	// ------------------------------------------------------------------------
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		return instance;
	}
	// ------------------------------------------------------------------------
	// 로그 출력을 위하여 Logger변수를 정적변수로 선언
	private static Logger logger = Logger.getLogger(BoardDAO.class);
	// ------------------------------------------------------------------------
	// 1. 개수얻기
	public int selectCount(Connection conn) throws SQLException {
		int count = 0;
		logger.debug("BoardDAO selectCount 인수 : " + conn);
		String sql = "select count(*) from freeboard";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			count = rs.getInt(1);
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		logger.debug("BoardDAO selectCount 리턴값 : " + count);
		return count;
	}
	// 2. 1개 얻기
	public BoardVO selectByIdx(Connection conn, int idx) throws SQLException {
		BoardVO vo = null;
		logger.debug("BoardDAO selectByIdx 인수 : " + conn + ", " + idx);
		String sql = "select * from freeboard where idx=" + idx;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			vo = makeVO(rs);
		}
		logger.debug("BoardDAO selectByIdx 리턴값 : " + vo);
		return vo;
	}
	
	// 3. 1페이지 얻기
	public List<BoardVO> selectList(Connection conn, int startNo, int pageSize) throws SQLException {
		logger.debug("BoardDAO selectList 인수 : " + startNo + ", " + pageSize);
		List<BoardVO> list = null;
		
		String sql = "select * from freeboard order by idx desc limit ?, ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startNo);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			// 담을 컬렉션 객체 생성
			list = new ArrayList<BoardVO>();
			do {
				// 1개의 VO를 만들어 데이터 DB에서 읽어 채워준다
				BoardVO vo = makeVO(rs);
				list.add(vo);
			} while (rs.next());
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		logger.debug("BoardDAO selectList 리턴값 : " + list);
		return list;
	}
	
	private BoardVO makeVO(ResultSet rs) throws SQLException {
		logger.debug("BoardDAO makeVO 인수 : " + rs);
		BoardVO vo = new BoardVO();
		
		vo.setIdx(rs.getInt("idx"));
		vo.setHit(rs.getInt("hit"));
		vo.setGood(rs.getInt("good"));
		vo.setBad(rs.getInt("bad"));
		vo.setName(rs.getString("name"));
		vo.setPassword(rs.getString("password"));
		vo.setContent(rs.getString("content"));
		vo.setSubject(rs.getString("subject"));
		vo.setIp(rs.getString("ip"));
		vo.setRegDate(rs.getTimestamp("regDate"));
		
		logger.debug("BoardDAO makeVO 리턴값 : " + vo);
		
		return vo;
	}
	// 4. 저장하기
	public int insert(Connection conn, BoardVO vo) throws SQLException {
		logger.debug("BoardDAO insert 인수 : " + conn + ", " + vo);
		int count = 0;
		String sql = "insert into freeboard (name,password,subject,content,ip) values (?,?,?,?,?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getSubject());
		pstmt.setString(4, vo.getContent());
		pstmt.setString(5, vo.getIp());
		count = pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
		logger.debug("BoardDAO insert 리턴값 : " + count);
		return count;
	}
	// 5. 수정하기
	public void update(Connection conn, BoardVO vo) throws SQLException {
		logger.debug("BoardDAO update 인수 : " + conn + ", " + vo);
		String sql = "update freeboard set subject=?, content=?, regDate=now(), ip=? where idx=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getSubject());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getIp());
		pstmt.setInt(4, vo.getIdx());
		pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
	}
	// 6. 삭제하기
	public void delete(Connection conn, int idx) throws SQLException {
		logger.debug("BoardDAO delete 인수 : " + conn + ", " + idx);
		String sql = "delete from freeboard where idx=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
	}
	// 7. 조회수 증가하기
	public void increaseHit(Connection conn, int idx) throws SQLException {
		logger.debug("BoardDAO increaseHit 인수 : " + idx);
		String sql = "update freeboard set hit = hit + 1 where idx=" + idx;
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		JdbcUtil.close(stmt);
	}
	// 8. 추천 증가하기
	public void increaseGood(Connection conn, int idx) throws SQLException {
		logger.debug("BoardDAO increaseGood 인수 : " + idx);
		String sql = "update freeboard set good = good + 1 where idx=" + idx;
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		JdbcUtil.close(stmt);
	}
	// 9. 비추천 증가하기
	public void increaseBad(Connection conn, int idx) throws SQLException {
		logger.debug("BoardDAO increaseBad 인수 : " + idx);
		String sql = "update freeboard set bad = bad + 1 where idx=" + idx;
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		JdbcUtil.close(stmt);
	}
}
