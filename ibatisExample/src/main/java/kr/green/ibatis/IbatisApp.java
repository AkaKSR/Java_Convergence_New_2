package kr.green.ibatis;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisApp {
	private static SqlMapClient sqlMap;
	
	static {
		String resource = "SqlMapConfig.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient() {
		return sqlMap;
	}
}
