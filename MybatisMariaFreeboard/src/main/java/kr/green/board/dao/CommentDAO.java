package kr.green.board.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.green.board.vo.CommentVO;

public class CommentDAO {
	private static CommentDAO instance = new CommentDAO();

	private CommentDAO() {
	}

	public static CommentDAO getInstance() {
		return instance;
	}

	// 1. 해당 번호의 댓글의 개수 구하기
	public int selectByIdxCount(SqlSession sqlSession, int ref) throws SQLException {
		return sqlSession.selectOne("comment.selectByIdxCount", ref);
	}

	// 2. 해당 번호의 댓글 목록 구하기
	public List<CommentVO> selectByIdxList(SqlSession sqlSession, int ref) throws SQLException {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("ref", ref);
		return sqlSession.selectList("comment.selectByIdxList", map);
	}

	// 3. 1개 얻기
	public CommentVO selectByIdx(SqlSession sqlSession, int idx) throws SQLException {
		return sqlSession.selectOne("comment.selectByIdx", idx);
	}

	// 4. 저장하기
	public void insert(SqlSession sqlSession, CommentVO vo) throws SQLException {
		sqlSession.selectOne("comment.insert", vo);
	}

	// 5. 수정하기
	public void update(SqlSession sqlSession, CommentVO vo) throws SQLException {
		sqlSession.selectOne("comment.update", vo);
	}

	// 6. 삭제하기
	public void delete(SqlSession sqlSession, int idx) throws SQLException {
		sqlSession.selectOne("comment.delete", idx);
	}
}
