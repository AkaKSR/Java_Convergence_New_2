package kr.green.ibatis.service;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.green.ibatis.IbatisApp;
import kr.green.ibatis.dao.TestDAO;

public class TestService {
	private static TestService instance = new TestService();
	private TestService() {}
	public static TestService getInstance() {
		return instance;
	}
	
	// ============================================================== //
	// 1. 개수 세기
	public int selectCount() {
		int count = 0;
		SqlMapClient mapClient = null;
		TestDAO dao = null;
		try {
			mapClient = IbatisApp.getSqlMapClient();
			mapClient.startTransaction();
			dao = TestDAO.getInstance();
			// ==================================================== //
			count = dao.selectCount(mapClient);
			// ==================================================== //
			mapClient.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (mapClient != null) mapClient.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
