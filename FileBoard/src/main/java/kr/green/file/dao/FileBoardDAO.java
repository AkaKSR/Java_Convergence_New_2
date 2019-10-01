package kr.green.file.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.file.vo.FileBoardVO;

public class FileBoardDAO {
	private static FileBoardDAO instance = new FileBoardDAO();
	private FileBoardDAO() {}
	public static FileBoardDAO getInstance() {
		return instance;
	}
	
	// ============================================================ //
	
//	<!-- 1. 개수 얻기 -->
	public int selectCount(SqlMapClient sqlMap) throws SQLException {
		return (int) sqlMap.queryForObject("board.selectCount", "");
	}
//	<!-- 2. 1개 얻기 -->
	public FileBoardVO selectByIdx(SqlMapClient sqlMap, int idx) throws SQLException {
		return (FileBoardVO) sqlMap.queryForObject("board.selectByIdx", idx);
	}
//	<!-- 3. 1페이지 얻기 -->
	@SuppressWarnings("unchecked")
	public List<FileBoardVO> selectList(SqlMapClient sqlMap, int startNo, int pageSize) throws SQLException {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", startNo);
		map.put("pageSize", pageSize);
		return sqlMap.queryForList("board.selectList", map);
	}
//	<!-- 4. 저장 -->
	public void insert(SqlMapClient sqlMap, FileBoardVO vo) throws SQLException {
		sqlMap.insert("board.insert", vo);
	}
//	<!-- 5. 수정 -->
	public void update(SqlMapClient sqlMap, FileBoardVO vo) throws SQLException {
		sqlMap.update("board.update", vo);
	}
//	<!-- 6. 삭제 -->
	public void delete(SqlMapClient sqlMap, int idx) throws SQLException {
		sqlMap.delete("board.delete", idx);
	}
//	<!-- 7. 마지막으로 증가한 auto_increment 값 업기 -->
	public int selectLastIdx(SqlMapClient sqlMap) throws SQLException {
		return (int) sqlMap.queryForObject("board.selectLastIdx", "");
	}
//	<!-- 8. 암호가 일치하는지 확인하는 쿼리 -->
	public int passwordCheck(SqlMapClient sqlMap, int idx, String password) throws SQLException {
		HashMap<String, String> map = new HashMap<>();
		map.put("idx", idx+"");
		map.put("password", password);
		return (int) sqlMap.queryForObject("board.passwordCheck", map);
	}
	// ============================================================ //
}
