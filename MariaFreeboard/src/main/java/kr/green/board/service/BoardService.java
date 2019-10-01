package kr.green.board.service;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import kr.green.board.dao.BoardDAO;
import kr.green.board.dao.CommentDAO;
import kr.green.board.vo.BoardVO;
import kr.green.board.vo.CommentVO;
import kr.green.board.vo.Paging;
import kr.green.jdbc.JdbcUtil;

public class BoardService {
	//------------------------------------------------------------------------
	private static BoardService instance = new BoardService();
	private BoardService() {}
	public static BoardService getInstance() {
		return instance;
	}
	//------------------------------------------------------------------------
	// 로그 출력을 위하여 Logger변수를 정적 변수로 선언
	private static Logger logger = Logger.getLogger(BoardService.class);
	//------------------------------------------------------------------------
	// 1. 목록보기
	public Paging<BoardVO> selectList(int currentPage, int pageSize, int blockSize){
		Paging<BoardVO> paging = null;
		logger.debug("BoardService selectList 인수 : " + currentPage + ", " + pageSize + ", " + blockSize);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			int totalCount = dao.selectCount(conn); // 전체 개수 구하기
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize); // 계산 완료
			// 1페이지 분량의 글 가져오기
			List<BoardVO> list = dao.selectList(conn, paging.getStartNo(), paging.getPageSize());
			// ==================================================== //
			// 각각의 글에 대한 댓글의 갯수를 구해서 VO에 채워준다.
			if (list != null) {
				for (BoardVO vo : list) {
					int commentCount = CommentDAO.getInstance().selectByIdxCount(conn, vo.getIdx());
					vo.setCommentCount(commentCount);
				}
			}
			// ==================================================== //
			paging.setList(list);
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		logger.debug("BoardService selectList 리턴값 : " + paging);
		return paging;
	}
	// 2. 글쓰기(저장하기)
	public int insert(BoardVO vo) {
		int count = 0;
		logger.debug("BoardService insert 인수 : " + vo);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			if(vo!=null) count = dao.insert(conn, vo);
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		logger.debug("BoardService insert 리턴값 : " + count);
		return count;
	}
	// 3. 수정하기
	public void update(BoardVO vo) {
		logger.debug("BoardService update 인수 : " + vo);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			// DB에 있는 비번과 입력된 비번이 일치 할때만 수정한다.
			// 1. DB에서 해당 글번호 데이터 가져오기
			BoardVO dbVO = dao.selectByIdx(conn, vo.getIdx());
			// 2. 넘어온 VO의 비번과 같은지 검사
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(conn, vo);
			}
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	// 4. 삭제하기
	public void delete(BoardVO vo) {
		logger.debug("BoardService delete 인수 : " + vo);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			// DB에 있는 비번과 입력된 비번이 일치 할때만 삭제한다.
			// 1. DB에서 해당 글번호 데이터 가져오기
			BoardVO dbVO = dao.selectByIdx(conn, vo.getIdx());
			// 2. 넘어온 VO의 비번과 같은지 검사
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(conn, vo.getIdx());
			}
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	// 5. 내용보기(1개 리턴하기) : idx(글번호), mode(조회수 증가 여부)
	public BoardVO selectByIdx(int idx, int mode) {
		BoardVO vo = null;
		logger.debug("BoardService selectByIdx 인수 : " + idx + ", " + mode);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			// 보여줄 1개 얻기
			vo = dao.selectByIdx(conn, idx);
			// ============================================================== //
			// 해당 글의 댓글을 모두 가져온다.
			List<CommentVO> commentList = CommentDAO.getInstance().selectByIdxList(conn, vo.getIdx());
			vo.setCommentList(commentList);
			// ============================================================== //
			// 해당 글이 있으면서 mode값이 1이면
			if(vo!=null && mode==1) {
				dao.increaseHit(conn, idx); // 조회수 증가
				// 자신의 조회수 1 증가
				vo.setHit(vo.getHit()+1);
			}
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		logger.debug("BoardService selectByIdx 리턴값 : " + vo);
		return vo;
	}
	
	// 6. 추천 증가하기
	public int increaseGood(int idx) {
		int count = 0;
		
		logger.debug("BoardService increaseGood 인수 : " + idx);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			// 1. 추천 증가
			dao.increaseGood(conn, idx);
			// 2. 증가된 추천수 리턴
			BoardVO vo = dao.selectByIdx(conn, idx);
			count = vo.getGood();
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		logger.debug("BoardService increaseGood 리턴값 : " + count);
		
		return count;
	}
	
	// 7. 비추천 증가하기
	public int increaseBad(int idx) {
		int count = 0;
		
		logger.debug("BoardService increaseBad 인수 : " + idx);
		Connection conn = null;
		BoardDAO   dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = BoardDAO.getInstance();
			conn.setAutoCommit(false);
			//---------------------------------------------------------------
			// 1. 비추천 증가
			dao.increaseBad(conn, idx);
			// 2. 증가된 비추천수 리턴
			BoardVO vo = dao.selectByIdx(conn, idx);
			count = vo.getBad();
			//---------------------------------------------------------------
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		logger.debug("BoardService increaseBad 리턴값 : " + count);
		
		return count;
	}
}
