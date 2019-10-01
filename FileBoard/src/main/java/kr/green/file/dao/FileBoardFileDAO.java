package kr.green.file.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.file.vo.FileBoardFileVO;

public class FileBoardFileDAO {
	private static FileBoardFileDAO instance = new FileBoardFileDAO();
	private FileBoardFileDAO() {}
	public static FileBoardFileDAO getInstance() {
		return instance;
	}
	
	// ============================================================ //
	
//	<!-- 1. 저장 insert -->
	public void insert(SqlMapClient sqlMap, FileBoardFileVO vo) throws SQLException {
		sqlMap.insert("file.insert", vo);
	}
//	<!-- 2. 1개 삭제 deleteByIdx -->
	public void deleteByIdx(SqlMapClient sqlMap, int idx) throws SQLException {
		sqlMap.delete("file.deleteByIdx", idx);
	}
	
//	<!-- 3. ref가 같은 모든 파일 삭제 deleteByRef -->
	public void deleteByRef(SqlMapClient sqlMap, int ref) throws SQLException {
		sqlMap.delete("file.deleteByRef", ref);
	}
//	<!-- 4. 개수 selectCountByRef -->
	public int selectCountByRef(SqlMapClient sqlMap, int ref) throws SQLException {
		return (int) sqlMap.queryForObject("file.selectCountByRef", ref);
	}
	// ============================================================ //
}
