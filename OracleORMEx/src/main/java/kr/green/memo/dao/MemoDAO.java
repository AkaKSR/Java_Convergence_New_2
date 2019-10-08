package kr.green.memo.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.green.memo.vo.MemoVO;

public class MemoDAO {
	private static MemoDAO instance = new MemoDAO();
	private MemoDAO() {}
	public static MemoDAO getInstance() {
		return instance;
	}
	//-----------------------------------------------
	// 1. 개수 구하기
	public int selectCount(SqlSession sqlSession) throws SQLException {
		return sqlSession.selectOne("memo.selectCount");
	}
	// 2. 1개 구하기
	public MemoVO selectByIdx(SqlSession sqlSession, int idx) throws SQLException {
		return sqlSession.selectOne("memo.selectByIdx", idx);
	}
	// 3. 1페이지 얻기
	public List<MemoVO> selectList(SqlSession sqlSession, int startNo, int endNo) throws SQLException{
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		return sqlSession.selectList("memo.selectList", map);
	}
	// 4. 저장하기
	public void insert(SqlSession sqlSession, MemoVO vo) throws SQLException {
		sqlSession.insert("memo.insert", vo);
	}
	// 5. 수정하기
	public void update(SqlSession sqlSession, MemoVO vo) throws SQLException {
		sqlSession.update("memo.update", vo);
	}
	// 6. 삭제하기
	public void delete(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.delete("memo.delete", idx);
	}
}









