package kr.green.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.vo.GuestbookVO;

public class GuestbookDAO {
	private static GuestbookDAO instance = new GuestbookDAO();

	private GuestbookDAO() {
	}

	public static GuestbookDAO getInstance() {
		return instance;
	}

	// ==================================================== //
	// 1. 전체 개수 얻기
	public int selectCount(SqlMapClient sqlMap) throws SQLException {
		return (int) sqlMap.queryForObject("memo.selectCount", "");
	}

	// 2. 1개 얻기
	public GuestbookVO selectByIdx(SqlMapClient sqlMap, int idx) throws SQLException {
		return (GuestbookVO) sqlMap.queryForObject("memo.selectByIdx", idx);
	}

	// 3. 1페이지 얻기
	@SuppressWarnings("unchecked")
	public List<GuestbookVO> selectList(SqlMapClient sqlMap, int startNo, int pageSize) throws SQLException {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", startNo);
		map.put("pageSize", pageSize);
		return sqlMap.queryForList("memo.selectList", map);
	}

	// 4. 저장하기
	public void insert(SqlMapClient sqlMap, GuestbookVO vo) throws SQLException {
		sqlMap.insert("memo.insert", vo);
	}

	// 5. 수정하기
	public void update(SqlMapClient sqlMap, GuestbookVO vo) throws SQLException {
		sqlMap.update("memo.update", vo);
	}

	// 6. 삭제하기
	public void delete(SqlMapClient sqlMap, int idx) throws SQLException {
		sqlMap.delete("memo.delete", idx);
	}

	// ==================================================== //
}
