package kr.green.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.green.vo.GuestbookVO;


public class GuestbookDAO {
	private static GuestbookDAO instance = new GuestbookDAO();
	private GuestbookDAO() {}
	public static GuestbookDAO getInstance() {
		return instance;
	}
	//-----------------------------------------------
	// 1. 개수 구하기
	public int selectCount(SqlSession sqlSession) throws SQLException {
		return sqlSession.selectOne("guestbook.selectCount", "");
	}
	// 2. 1개 구하기
	public GuestbookVO selectByIdx(SqlSession sqlSession, int idx) throws SQLException {
		return sqlSession.selectOne("guestbook.selectByIdx", idx);
	}
	// 3. 1페이지 얻기
	public List<GuestbookVO> selectList(SqlSession sqlSession, int startNo, int pageSize) throws SQLException{
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", startNo);
		map.put("pageSize", pageSize);
		return sqlSession.selectList("guestbook.selectList", map);
	}
	// 4. 저장하기
	public void insert(SqlSession sqlSession, GuestbookVO vo) throws SQLException {
		sqlSession.insert("guestbook.insert", vo);
	}
	// 5. 수정하기
	public void update(SqlSession sqlSession, GuestbookVO vo) throws SQLException {
		sqlSession.update("guestbook.update", vo);
	}
	// 6. 삭제하기
	public void delete(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.delete("guestbook.delete", idx);
	}
}









