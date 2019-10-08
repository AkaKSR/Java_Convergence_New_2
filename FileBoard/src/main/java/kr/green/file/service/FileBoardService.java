package kr.green.file.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.file.dao.FileBoardDAO;
import kr.green.file.dao.FileBoardFileDAO;
import kr.green.file.vo.FileBoardFileVO;
import kr.green.file.vo.FileBoardVO;
import kr.green.file.vo.Paging;
import kr.green.ibatis.IbatisApp;

public class FileBoardService {
	private static FileBoardService instance = new FileBoardService();
	private FileBoardService() {}
	public static FileBoardService getInstance() {
		return instance;
	}
	
	// 1. 목록
	public Paging<FileBoardVO> selectList(int currentPage, int pageSize, int blockSize) {
		Paging<FileBoardVO> paging = null;
		SqlMapClient sqlMap = null;
		FileBoardDAO fileBoardDAO = null;
		FileBoardFileDAO fileBoardFileDAO = null;
		
		try {
			
			sqlMap = IbatisApp.getSqlMapClient();
			fileBoardDAO = FileBoardDAO.getInstance();
			fileBoardFileDAO = FileBoardFileDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================================= //
			int totalCount = fileBoardDAO.selectCount(sqlMap);
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize);
			List<FileBoardVO> list = fileBoardDAO.selectList(sqlMap, paging.getStartNo(), paging.getPageSize());
			// 첨부 파일의 개수를 얻어서 리스트에 넣자
			for (FileBoardVO vo : list) {
				int count = fileBoardFileDAO.selectCountByRef(sqlMap, vo.getIdx());
				vo.setFileCount(count);
			}
			// 리스트를 페이징 객체에 대입
			paging.setList(list);
			// ======================================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return paging;
	}
	// 2. 저장
	public void insert(FileBoardVO vo, List<FileBoardFileVO> list) {
		SqlMapClient sqlMap = null;
		FileBoardDAO fileBoardDAO = null;
		FileBoardFileDAO fileBoardFileDAO = null;
		
		try {
			
			sqlMap = IbatisApp.getSqlMapClient();
			fileBoardDAO = FileBoardDAO.getInstance();
			fileBoardFileDAO = FileBoardFileDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================================= //
			if(vo != null) {
				// 글 저장
				fileBoardDAO.insert(sqlMap, vo);
				// 파일 저장
				if (list != null) {
					int ref = fileBoardDAO.selectLastIdx(sqlMap);
					for (FileBoardFileVO fvo : list) { // 마지막 idx 구하기
						fvo.setRef(ref); // 원본글 번호 넣고
						fileBoardFileDAO.insert(sqlMap, fvo); // 저장
					}
				}
			}
			// ======================================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 3. 수정
	public void update(FileBoardVO vo, List<FileBoardFileVO> list) {
		SqlMapClient sqlMap = null;
		FileBoardDAO fileBoardDAO = null;
		FileBoardFileDAO fileBoardFileDAO = null;
		
		try {
			
			sqlMap = IbatisApp.getSqlMapClient();
			fileBoardDAO = FileBoardDAO.getInstance();
			fileBoardFileDAO = FileBoardFileDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================================= //
			if(vo != null) {
				// 글 수정
				// 비밀번호 같을때만 수정
				int result = fileBoardDAO.passwordCheck(sqlMap, vo.getIdx(), vo.getPassword());
				if (result == 1) {
					fileBoardDAO.update(sqlMap, vo);
					// 파일 수정
					if (list != null) {
						int ref = vo.getIdx(); // 원본글 번호
						for (FileBoardFileVO fvo : list) { // 마지막 idx 구하기
							fvo.setRef(ref); // 원본글 번호 넣고
							fileBoardFileDAO.insert(sqlMap, fvo); // 수정
						}
					}
				}
			}
			// ======================================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 4. 삭제
	public void delete(FileBoardVO vo) {
		SqlMapClient sqlMap = null;
		FileBoardDAO fileBoardDAO = null;
		FileBoardFileDAO fileBoardFileDAO = null;
		
		try {
			
			sqlMap = IbatisApp.getSqlMapClient();
			fileBoardDAO = FileBoardDAO.getInstance();
			fileBoardFileDAO = FileBoardFileDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================================= //
			if(vo != null) {
				// 글 삭제
				// 비밀번호 같을때만 수정
				int result = fileBoardDAO.passwordCheck(sqlMap, vo.getIdx(), vo.getPassword());
				if (result == 1) {
					// 글 삭제
					fileBoardDAO.delete(sqlMap, vo.getIdx());
					// 파일 모두 삭제
					fileBoardFileDAO.deleteByRef(sqlMap, vo.getIdx());
				}
			}
			// ======================================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 5. 파일 1개 삭제
	public int deleteFile(int idx) {
		int count = 0;
		
		SqlMapClient sqlMap = null;
		FileBoardFileDAO fileBoardFileDAO = null;
		try {
			
			sqlMap = IbatisApp.getSqlMapClient();
			fileBoardFileDAO = FileBoardFileDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================================= //
			// 첨부파일 삭제하기
			fileBoardFileDAO.deleteByIdx(sqlMap, idx);
			count = 1;
			// ======================================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	// 6. 1개 얻기
	public FileBoardVO selectByIdx(int idx) {
		FileBoardVO vo = null;
		SqlMapClient sqlMap = null;
		FileBoardDAO fileBoardDAO = null;
		FileBoardFileDAO fileBoardFileDAO = null;
		try {
			sqlMap = IbatisApp.getSqlMapClient();
			fileBoardDAO = FileBoardDAO.getInstance();
			fileBoardFileDAO = FileBoardFileDAO.getInstance();
			sqlMap.startTransaction();
			// ======================================================= //
			// 첨부파일에 대한 내용 얻어오기(글 얻기)
			vo = fileBoardDAO.selectByIdx(sqlMap, idx);
			if (vo != null) {
				// 첨부파일에 대한 정보 얻기
				List<FileBoardFileVO> list = fileBoardFileDAO.selectListByRef(sqlMap, idx); 
				vo.setFileList(list);
			}
			// ======================================================= //
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlMap != null) sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
}
