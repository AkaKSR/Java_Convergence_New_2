package kr.green.board.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.green.board.vo.BoardVO;

public class BoardDAO {
	// ------------------------------------------------------------------------
	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		return instance;
	}

	// ------------------------------------------------------------------------
	// 로그 출력을 위하여 Logger변수를 정적변수로 선언
	// ------------------------------------------------------------------------
	// 1. 개수얻기
	public int selectCount(SqlSession sqlSession) throws SQLException {
		return sqlSession.selectOne("freeboard.selectCount", "");
	}

	// 2. 1개 얻기
	public BoardVO selectByIdx(SqlSession sqlSession, int idx) throws SQLException {
		return sqlSession.selectOne("freeboard.selectByIdx", idx);
	}

	// 3. 1페이지 얻기
	public List<BoardVO> selectList(SqlSession sqlSession, int startNo, int pageSize) throws SQLException {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", startNo);
		map.put("pageSize", pageSize);
		return sqlSession.selectList("freeboard.selectList", map);
	}

	// 4. 저장하기
	public void insert(SqlSession sqlSession, BoardVO vo) throws SQLException {
		sqlSession.insert("freeboard.insert", vo);
	}

	// 5. 수정하기
	public void update(SqlSession sqlSession, BoardVO vo) throws SQLException {
		sqlSession.update("freeboard.update", vo);
	}

	// 6. 삭제하기
	public void delete(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.delete("freeboard.delete", idx);
	}

	// 7. 조회수 증가하기
	public void increaseHit(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.update("freeboard.increaseHit", idx);
	}

	// 8. 추천 증가하기
	public void increaseGood(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.update("freeboard.increaseGood", idx);
	}

	// 9. 비추천 증가하기
	public void increaseBad(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.update("freeboard.increaseBad", idx);
	}
}
