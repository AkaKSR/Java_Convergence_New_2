package kr.green.board.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.green.board.dao.CommentDAO;
import kr.green.board.vo.CommentVO;
import kr.green.mybatis.MybatisApp;

public class CommentService {
	private static CommentService instance = new CommentService();

	private CommentService() {
	}

	public static CommentService getInstance() {
		return instance;
	}

	private Logger logger = Logger.getLogger(CommentService.class);

	// ========================================================= //
	// 1. 해당 번호의 댓글의 개수 구하기
	public int selectByIdxCount(int ref) throws SQLException {
		int count = 0;
		logger.debug("CommentService selectByIdxCount 인수 : " + ref);
		SqlSession sqlSession = null;
		CommentDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = CommentDAO.getInstance();
			count = dao.selectByIdxCount(sqlSession, ref);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		logger.debug("CommentService selectByIdxCount 리턴값 : " + count);
		return count;
	}

	// 2. 해당 번호의 댓글 목록 구하기
	public List<CommentVO> selectbyIdxList(int ref) throws SQLException {
		List<CommentVO> list = null;
		logger.debug("CommentService selectByIdxList 인수 : " + ref);
		SqlSession sqlSession = null;
		CommentDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = CommentDAO.getInstance();
			list = dao.selectByIdxList(sqlSession, ref);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		logger.debug("CommentService selectByIdxList 리턴값 : " + list);
		return list;
	}

	// 3. 저장
	public void insert(CommentVO vo) {
		logger.debug("CommentService insert 인수 : " + vo);
		SqlSession sqlSession = null;
		CommentDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = CommentDAO.getInstance();
			// ===================================================== //
			if (vo != null)
				dao.insert(sqlSession, vo);
			// ===================================================== //
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	// 4. 수정
	public void update(CommentVO vo) {
		logger.debug("CommentService update 인수 : " + vo);
		SqlSession sqlSession = null;
		CommentDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = CommentDAO.getInstance();
			// ===================================================== //
			CommentVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(sqlSession, vo);
			}
			// ===================================================== //
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	// 5. 삭제
	public void delete(CommentVO vo, int idx) {
		logger.debug("CommentService delete 인수 : " + vo);
		SqlSession sqlSession = null;
		CommentDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = CommentDAO.getInstance();
			// ===================================================== //
			CommentVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(sqlSession, idx);
			}
			// ===================================================== //
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	// ========================================================= //
}
