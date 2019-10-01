package kr.green.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import kr.green.board.dao.CommentDAO;
import kr.green.board.vo.CommentVO;
import kr.green.jdbc.JdbcUtil;

public class CommentService {
	private static CommentService instance = new CommentService();
	private CommentService() {}
	public static CommentService getInstance() {
		return instance;
	}
	private Logger logger = Logger.getLogger(CommentService.class);
	
	// ========================================================= //
	// 1. 해당 번호의 댓글의 개수 구하기
	public int selectByIdxCount(int ref) throws SQLException {
		int count = 0;
		logger.debug("CommentService selectByIdxCount 인수 : " + ref);
		Connection conn = null;
		CommentDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			dao = CommentDAO.getInstance();
			count = dao.selectByIdxCount(conn, ref);
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		logger.debug("CommentService selectByIdxCount 리턴값 : " + count);
		return count;
	}
	// 2. 해당 번호의 댓글 목록 구하기
	public List<CommentVO> selectbyIdxList(int ref) throws SQLException {
		List<CommentVO> list = null;
		logger.debug("CommentService selectByIdxList 인수 : " + ref);
		Connection conn = null;
		CommentDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			dao = CommentDAO.getInstance();
			list = dao.selectByIdxList(conn, ref);
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		logger.debug("CommentService selectByIdxList 리턴값 : " + list);
		return list;
	}
	// 3. 저장
	public int insert(CommentVO vo) {
		int count = 0;
		logger.debug("CommentService insert 인수 : " + vo);
		Connection conn = null;
		CommentDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = CommentDAO.getInstance();
			conn.setAutoCommit(false);
			// ===================================================== //
			if(vo != null) count = dao.insert(conn, vo);
			// ===================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		logger.debug("CommentService insert 리턴값 : " + count);
		return count;
	}
	// 4. 수정
	public int update(CommentVO vo) {
		int count = 0;
		logger.debug("CommentService update 인수 : " + vo);
		Connection conn = null;
		CommentDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = CommentDAO.getInstance();
			conn.setAutoCommit(false);
			// ===================================================== //
			CommentVO dbVO = dao.selectByIdx(conn, vo.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				count = dao.update(conn, vo);
			}
			// ===================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		logger.debug("CommentService update 리턴값 : " + count);
		return count;
	}
	// 5. 삭제
	public int delete(CommentVO vo) {
		int count = 0;
		logger.debug("CommentService delete 인수 : " + vo);
		Connection conn = null;
		CommentDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = CommentDAO.getInstance();
			conn.setAutoCommit(false);
			// ===================================================== //
			CommentVO dbVO = dao.selectByIdx(conn, vo.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				count = dao.delete(conn, vo.getIdx());
			}
			// ===================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		logger.debug("CommentService delete 리턴값 : " + count);
		return count;
	}
	// ========================================================= //
}
