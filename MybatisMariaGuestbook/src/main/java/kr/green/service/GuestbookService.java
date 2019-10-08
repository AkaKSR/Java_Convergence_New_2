package kr.green.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.green.dao.GuestbookDAO;
import kr.green.mybatis.MybatisApp;
import kr.green.vo.GuestbookVO;
import kr.green.vo.Paging;

public class GuestbookService {
	private static GuestbookService instance = new GuestbookService();

	private GuestbookService() {
	}

	public static GuestbookService getInstance() {
		return instance;
	}

	// ------------------------------------------------------
	// 1. 목록보기
	public Paging<GuestbookVO> selectList(int currentPage, int pageSize, int blockSize) {
		Paging<GuestbookVO> paging = null;
		SqlSession sqlSession = null;
		GuestbookDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = GuestbookDAO.getInstance();
			// --------------------------------------
			int totalCount = dao.selectCount(sqlSession);
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize);
			List<GuestbookVO> list = dao.selectList(sqlSession, paging.getStartNo(), paging.getPageSize());
			paging.setList(list);
			// --------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}

		return paging;
	}

	// 2. 저장하기
	public void insert(GuestbookVO vo) {
		SqlSession sqlSession = null;
		GuestbookDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = GuestbookDAO.getInstance();
			// --------------------------------------
			if (vo != null) {
				dao.insert(sqlSession, vo);
			}
			// --------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	// 3. 수정하기
	public void update(GuestbookVO vo) {
		SqlSession sqlSession = null;
		GuestbookDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = GuestbookDAO.getInstance();
			// --------------------------------------
			GuestbookVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			if (vo != null && dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(sqlSession, vo);
			}
			// --------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	// 4. 삭제하기
	public void delete(GuestbookVO vo) {
		SqlSession sqlSession = null;
		GuestbookDAO dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = GuestbookDAO.getInstance();
			// --------------------------------------
			GuestbookVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			if (vo != null && dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(sqlSession, vo.getIdx());
			}
			// --------------------------------------
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}
}
