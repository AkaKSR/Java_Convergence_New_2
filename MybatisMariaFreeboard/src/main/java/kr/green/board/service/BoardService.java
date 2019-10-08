package kr.green.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.green.board.dao.BoardDAO;
import kr.green.board.dao.CommentDAO;
import kr.green.board.vo.BoardVO;
import kr.green.board.vo.CommentVO;
import kr.green.board.vo.Paging;
import kr.green.mybatis.MybatisApp;

public class BoardService {
	// ------------------------------------------------------------------------
	private static BoardService instance = new BoardService();

	private BoardService() {
	}

	public static BoardService getInstance() {
		return instance;
	}

	// ------------------------------------------------------------------------
	// 로그 출력을 위하여 Logger변수를 정적 변수로 선언
	private static Logger logger = Logger.getLogger(BoardService.class);

	// ------------------------------------------------------------------------
	// 1. 목록보기
	public Paging<BoardVO> selectList(int currentPage, int pageSize, int blockSize) {
		Paging<BoardVO> paging = null;
		logger.debug("BoardService selectList 인수 : " + currentPage + ", " + pageSize + ", " + blockSize);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			int totalCount = dao.selectCount(sqlSession); // 전체 개수 구하기
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize); // 계산 완료
			// 1페이지 분량의 글 가져오기
			List<BoardVO> list = dao.selectList(sqlSession, paging.getStartNo(), paging.getPageSize());
			// ==================================================== //
			// 각각의 글에 대한 댓글의 갯수를 구해서 VO에 채워준다.
			if (list != null) {
				for (BoardVO vo : list) {
					int commentCount = CommentDAO.getInstance().selectByIdxCount(sqlSession, vo.getIdx());
					vo.setCommentCount(commentCount);
				}
			}
			// ==================================================== //
			paging.setList(list);
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		logger.debug("BoardService selectList 리턴값 : " + paging);
		return paging;
	}

	// 2. 글쓰기(저장하기)
	public void insert(BoardVO vo) {
		logger.debug("BoardService insert 인수 : " + vo);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			if (vo != null)
				dao.insert(sqlSession, vo);
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	// 3. 수정하기
	public void update(BoardVO vo) {
		logger.debug("BoardService update 인수 : " + vo);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			// DB에 있는 비번과 입력된 비번이 일치 할때만 수정한다.
			// 1. DB에서 해당 글번호 데이터 가져오기
			BoardVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			// 2. 넘어온 VO의 비번과 같은지 검사
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(sqlSession, vo);
			}
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	// 4. 삭제하기
	public void delete(BoardVO vo) {
		logger.debug("BoardService delete 인수 : " + vo);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			// DB에 있는 비번과 입력된 비번이 일치 할때만 삭제한다.
			// 1. DB에서 해당 글번호 데이터 가져오기
			BoardVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			// 2. 넘어온 VO의 비번과 같은지 검사
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(sqlSession, vo.getIdx());
			}
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	// 5. 내용보기(1개 리턴하기) : idx(글번호), mode(조회수 증가 여부)
	public BoardVO selectByIdx(int idx, int mode) {
		BoardVO vo = null;
		logger.debug("BoardService selectByIdx 인수 : " + idx + ", " + mode);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			// 보여줄 1개 얻기
			vo = dao.selectByIdx(sqlSession, idx);
			// ============================================================== //
			// 해당 글의 댓글을 모두 가져온다.
			List<CommentVO> commentList = CommentDAO.getInstance().selectByIdxList(sqlSession, vo.getIdx());
			vo.setCommentList(commentList);
			// ============================================================== //
			// 해당 글이 있으면서 mode값이 1이면
			if (vo != null && mode == 1) {
				dao.increaseHit(sqlSession, idx); // 조회수 증가
				// 자신의 조회수 1 증가
				vo.setHit(vo.getHit() + 1);
			}
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		logger.debug("BoardService selectByIdx 리턴값 : " + vo);
		return vo;
	}

	// 6. 추천 증가하기
	public int increaseGood(int idx) {
		int count = 0;

		logger.debug("BoardService increaseGood 인수 : " + idx);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			// 1. 추천 증가
			dao.increaseGood(sqlSession, idx);
			// 2. 증가된 추천수 리턴
			BoardVO vo = dao.selectByIdx(sqlSession, idx);
			count = vo.getGood();
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		logger.debug("BoardService increaseGood 리턴값 : " + count);

		return count;
	}

	// 7. 비추천 증가하기
	public int increaseBad(int idx) {
		int count = 0;

		logger.debug("BoardService increaseBad 인수 : " + idx);
		SqlSession sqlSession = null;
		BoardDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = BoardDAO.getInstance();
			// ---------------------------------------------------------------
			// 1. 비추천 증가
			dao.increaseBad(sqlSession, idx);
			// 2. 증가된 비추천수 리턴
			BoardVO vo = dao.selectByIdx(sqlSession, idx);
			count = vo.getBad();
			// ---------------------------------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		logger.debug("BoardService increaseBad 리턴값 : " + count);

		return count;
	}
}
