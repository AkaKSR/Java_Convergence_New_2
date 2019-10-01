package kr.green.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kr.green.jdbc.JdbcUtil;
import kr.green.memo.vo.MemoVO;

public class MemoDAO {
	// 싱글톤 패턴 생성
	private static MemoDAO instance = new MemoDAO();
	private MemoDAO() {}
	public static MemoDAO getInstance() {
		return instance;
	}
	// 싱글톤 패턴 생성 종료
	
	// Log4j 초기화
	private static Logger logger = Logger.getLogger(MemoDAO.class);
	
	// 1. 개수얻기
	public int selectCount(Connection conn) throws SQLException {
		int count = 0;
		logger.debug("MemoDAO selectCount 인수 : " + conn);
		
		String sql = "select count(*) from memo";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			count = rs.getInt(1);
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		
		logger.debug("MemoDAO selectCount 리턴값 : " + count);
		return count;
	}
	
	// selectByIdx
	public MemoVO selectByIdx(Connection conn, int idx) throws SQLException {
		MemoVO vo = null;
		logger.debug("MemoDAO selectByIdx 인수 : " + conn + ", " + idx);
		String sql = "select * from memo where idx=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		ResultSet rs = pstmt.executeQuery(sql);
		if (rs.next()) {
			vo = makeVO(rs);
		}
		JdbcUtil.close(pstmt);
		logger.debug("MemoDAO selectByIdx 리턴값 : " + vo);
		return vo;
	}
	
	// 2. 1페이지 얻기
	public List<MemoVO> selectList(Connection conn, int startNo, int pageSize) throws SQLException {
		List<MemoVO> list = null;
		logger.debug("MemoDAO selectList 인수 : " + conn + ", " + startNo + ", " + pageSize);
		
		String sql = "select * from memo order by idx desc limit ?, ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startNo);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			list = new ArrayList<MemoVO>();
			do {
				MemoVO vo = makeVO(rs);
				list.add(vo);
			} while (rs.next());
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		logger.debug("MemoDAO selectList 리턴값 : " + list);
		return list;
	}
	
	private MemoVO makeVO(ResultSet rs) throws SQLException {
		MemoVO vo = new MemoVO();
		logger.debug("MemoDAO makeVO 인수 : " + rs);
		
		vo.setIdx(rs.getInt("idx"));
		vo.setName(rs.getString("name"));
		vo.setPassword(rs.getString("password"));
		vo.setContent(rs.getString("content"));
		vo.setRegDate(rs.getTimestamp("regDate"));
		vo.setIp(rs.getString("ip"));
		
		logger.debug("MemoDAO makeVO 리턴값 : " + vo);
		return vo;
	}
	
	// 3. 저장하기
	public int insert(Connection conn, MemoVO vo) throws SQLException {
		int count = 0;
		logger.debug("MemoDAO insert 인수 : " + conn + ", " + vo);
		
		String sql = "insert into memo (name,password,content,regDate,ip) values (?,?,?,now(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, vo.getIp());
		count = pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
		
		logger.debug("MemoDAO insert 리턴값 : " + count);
		return count;
	}
	// 4. 수정하기
	public void update(Connection conn, MemoVO vo) throws SQLException {
		logger.debug("MemoDAO update 인수 : " + conn + ", " + vo);
		String sql = "update memo set content=?, regDate=now(), ip=? where idx=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getIp());
		pstmt.setInt(3, vo.getIdx());
		pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
	}
	
	// 5. 삭제하기
	public void delete(Connection conn, int idx) throws SQLException {
		logger.debug("MemoDAO delete 인수 : " + conn + ", " + idx);
		String sql = "delete from memo where idx=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		pstmt.executeUpdate();
		JdbcUtil.close(pstmt);
	}
}
