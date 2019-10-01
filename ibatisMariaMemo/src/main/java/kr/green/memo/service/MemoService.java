package kr.green.memo.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.ibatis.IbatisApp;
import kr.green.memo.dao.MemoDAO;
import kr.green.memo.vo.MemoVO;
import kr.green.memo.vo.Paging;

public class MemoService {
	private static MemoService instance = new MemoService();
	private MemoService() {}
	public static MemoService getInstance() {
		return instance;
	}
	
	// ========================================= //
	// 1. 목록보기
	public Paging<MemoVO> selectList(int currentPage, int pageSize, int blockSize) {
		Paging<MemoVO> paging = null;
		
		SqlMapClient sqlMap = null;
		MemoDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = MemoDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================= //
			int totalCount = dao.selectCount(sqlMap);
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize);
			List<MemoVO> list = dao.selectList(sqlMap, paging.getStartNo(), paging.getPageSize());
			paging.setList(list);
			// ======================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return paging;
	}
	
	// 2. 저장하기
	public void insert(MemoVO vo) {
		SqlMapClient sqlMap = null;
		MemoDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = MemoDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================= //
			if (vo != null) {
				dao.insert(sqlMap, vo);
			}
			// ======================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 3. 수정하기
	public void update(MemoVO vo) {
		SqlMapClient sqlMap = null;
		MemoDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = MemoDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================= //
			MemoVO dbVO = dao.selectByIdx(sqlMap, vo.getIdx());
			if (vo != null &&dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(sqlMap, vo);
			}
			// ======================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 4. 삭제하기
	public void delete(MemoVO vo) {
		SqlMapClient sqlMap = null;
		MemoDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = MemoDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================= //
			MemoVO dbVO = dao.selectByIdx(sqlMap, vo.getIdx());
			if (vo != null && dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(sqlMap, vo.getIdx());
			}
			// ======================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// ========================================= //
}
