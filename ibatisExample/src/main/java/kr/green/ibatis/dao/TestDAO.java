package kr.green.ibatis.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TestDAO {
	private static TestDAO instance = new TestDAO();
	private TestDAO() {}
	public static TestDAO getInstance() {
		return instance;
	}
	
	// ============================================================== //
	// 1. 개수 세기
	public int selectCount(SqlMapClient mapClient) throws SQLException {
		return (int) mapClient.queryForObject("test.selectCount", "");
	}
}
