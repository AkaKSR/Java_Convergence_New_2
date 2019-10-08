package kr.green.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.green.memo.dao.MemoDAO;
import kr.green.memo.vo.MemoVO;
import kr.green.memo.vo.Paging;
import kr.green.mybatis.MybatisApp;

public class MemoService {
	private static MemoService instance = new MemoService();
	private MemoService() {}
	public static MemoService getInstance() {
		return instance;
	}
	//------------------------------------------------------
	// 1. 목록보기
	public Paging<MemoVO> selectList(int currentPage,int pageSize, int blockSize){
		Paging<MemoVO> paging = null;
		SqlSession sqlSession = null;
		MemoDAO  dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = MemoDAO.getInstance();
			//--------------------------------------
			int totalCount = dao.selectCount(sqlSession);
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize);
			List<MemoVO> list = dao.selectList(sqlSession, paging.getStartNo(), paging.getEndNo());
			paging.setList(list);
			//--------------------------------------
			sqlSession.commit();
		}catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) sqlSession.close();
		}
		
		return paging;
	}
	
	// 2. 저장하기
	public void insert(MemoVO vo) {
		SqlSession sqlSession = null;
		MemoDAO  dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = MemoDAO.getInstance();
			//--------------------------------------
			if(vo!=null) {
				dao.insert(sqlSession, vo);
			}
			//--------------------------------------
			sqlSession.commit();
		}catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) sqlSession.close();
		}
	}
	
	// 3. 수정하기
	public void update(MemoVO vo) {
		SqlSession sqlSession = null;
		MemoDAO  dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = MemoDAO.getInstance();
			//--------------------------------------
			MemoVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			if(vo!=null && dbVO!=null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(sqlSession, vo);
			}
			sqlSession.commit();
		}catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) sqlSession.close();
		}
	}
	
	// 4. 삭제하기
	public void delete(MemoVO vo) {
		SqlSession sqlSession = null;
		MemoDAO  dao = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			dao = MemoDAO.getInstance();
			//--------------------------------------
			MemoVO dbVO = dao.selectByIdx(sqlSession, vo.getIdx());
			if(vo!=null && dbVO!=null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(sqlSession, vo.getIdx());
			}
			//--------------------------------------
			sqlSession.commit();
		}catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) sqlSession.close();
		}
	}
	
}
