package kr.green.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.dao.GuestbookDAO;
import kr.green.ibatis.IbatisApp;
import kr.green.vo.GuestbookVO;
import kr.green.vo.Paging;

public class GuestbookService {
	private static GuestbookService instance = new GuestbookService();
	private GuestbookService() {}
	public static GuestbookService getInstance() {
		return instance;
	}
	
	// =============================================== //
	// 1. 목록보기
	public Paging<GuestbookVO> selectList(int currentPage, int pageSize, int blockSize) {
		Paging<GuestbookVO> paging = null;
		
		SqlMapClient sqlMap = null;
		GuestbookDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = GuestbookDAO.getInstance();
			sqlMap.startTransaction();
			// ===================================== //
			int totalCount = dao.selectCount(sqlMap);
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize);
			List<GuestbookVO> list = dao.selectList(sqlMap, paging.getStartNo(), pageSize);
			paging.setList(list);
			// ===================================== //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return paging;
	}
	// 2. 저장하기
	public void insert(GuestbookVO vo) {
		SqlMapClient sqlMap = null;
		GuestbookDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = GuestbookDAO.getInstance();
			sqlMap.startTransaction();
			// ===================================== //
			if (vo != null) {
				dao.insert(sqlMap, vo);
			}
			// ===================================== //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 3. 수정하기
	public void update(GuestbookVO vo) {
		SqlMapClient sqlMap = null;
		GuestbookDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = GuestbookDAO.getInstance();
			sqlMap.startTransaction();
			// ===================================== //
			GuestbookVO dbVO = dao.selectByIdx(sqlMap, vo.getIdx());
			if (vo != null && dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(sqlMap, vo);
			}
			// ===================================== //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 4. 삭제하기
	public void delete(GuestbookVO vo) {
		SqlMapClient sqlMap = null;
		GuestbookDAO dao = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			dao = GuestbookDAO.getInstance();
			sqlMap.startTransaction();
			// ===================================== //
			GuestbookVO dbVO = dao.selectByIdx(sqlMap, vo.getIdx());
			if (vo != null && dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(sqlMap, vo.getIdx());
			}
			// ===================================== //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// =============================================== //
}
