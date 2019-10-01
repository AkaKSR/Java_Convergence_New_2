package kr.green.memo.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.memo.vo.MemoVO;

public class MemoDAO {
	private static MemoDAO instance = new MemoDAO();
	private MemoDAO() {}
	public static MemoDAO getInstance() {
		return instance;
	}
	
	// ======================================================= //
	// 1. 개수 구하기
	public int selectCount(SqlMapClient sqlMap) throws SQLException {
		return (int) sqlMap.queryForObject("memo.selectCount", "");
	}
	
	// 2. 1개 구하기
	public MemoVO selectByIdx(SqlMapClient sqlMap, int idx) throws SQLException {
		return (MemoVO) sqlMap.queryForObject("memo.selectByIdx", idx);
	}
	
	// 3. 1페이지 얻기
	@SuppressWarnings("unchecked")
	public List<MemoVO> selectList(SqlMapClient sqlMap, int startNo, int pageSize) throws SQLException {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", startNo);
		map.put("pageSize", pageSize);
		return sqlMap.queryForList("memo.selectList", map);
	}
	
	// 4. 저장하기
	public void insert(SqlMapClient sqlMap, MemoVO vo) throws SQLException {
		sqlMap.insert("memo.insert", vo);
	}
	
	// 5. 수정하기
	public void update(SqlMapClient sqlMap, MemoVO vo) throws SQLException {
		sqlMap.update("memo.update", vo);
	}
	
	// 6. 삭제하기
	public void delete(SqlMapClient sqlMap, int idx) throws SQLException {
		sqlMap.delete("memo.delete", idx);
	}
	
	// ======================================================= // 
	
}
