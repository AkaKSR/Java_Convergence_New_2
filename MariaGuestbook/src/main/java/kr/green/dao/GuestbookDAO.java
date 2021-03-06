package kr.green.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.green.jdbc.JdbcUtil;
import kr.green.vo.GuestbookVO;

/*
 *  SQL 명령어 1개당 1개의 메서드로 작성
 *  transection을 위하여 Connection을 첫번째 인수로 받는다.
 *  Singleton 패턴으로 작성하라.
 */
public class GuestbookDAO {
	// 1. 전체개수 얻기
	// ==================================================== //
	// 싱글톤 패턴
	// 1. 자기 자신의 객체를 정적 멤버로 가진다.
	private static GuestbookDAO instance = new GuestbookDAO();

	// 2. 밖에서 객체를 생성하지 못하도록 생성자를 private으로 만든다.
	private GuestbookDAO() {
	}

	// 3. 밖에서 이미 만들어진 객체를 사용할 수 있도록 정적 메서드를 생성한다.
	public static GuestbookDAO getInstance() {
		return instance;
	}

	// ==================================================== //
	// 1. 전체개수 얻기
	public int selectCount(Connection conn) throws SQLException {
		int count = 0;
		// SQL 명령 만들고
		String sql = "select count(*) from guestbook";
		// 명령 객체 만들고
		// 명령에 변하는 부분이 있으면 PreparedStatement 객체 생성
		// 명령에 변하는 부분이 없으면 Statement 객체 생성
		Statement stmt = conn.createStatement();
		// 실행하고
		ResultSet rs = stmt.executeQuery(sql);
		// 결과얻고
		rs.next();
		count = rs.getInt(1);
		// 닫기
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		// 결과 보내기
		return count;
	}

	// 2. 1개 얻기

	// 3. 1페이지 얻기
	public List<GuestbookVO> selectList(Connection conn, int startNo, int pageSize) throws SQLException {
		List<GuestbookVO> list = null;
		// SQL 명령 만들고
		String sql = "select * from guestbook order by idx desc limit ?,?";
		// 명령 객체 만들고
		// 명령에 변하는 부분이 있으면 PreparedStatement 객체 생성
		// 명령에 변하는 부분이 없으면 Statement 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startNo);
		pstmt.setInt(2, pageSize);
		// 실행하고
		ResultSet rs = pstmt.executeQuery();
		// 결과얻고
		if (rs.next()) {
			list = new ArrayList<>();
			do {
				GuestbookVO vo = new GuestbookVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getTimestamp("regdate"));
				vo.setIp(rs.getString("ip"));
				list.add(vo);
			} while (rs.next());
		}
		// 닫기
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		// 결과 보내기
		return list;
	}

	// 4. 저장하기
	public int insert(Connection conn, GuestbookVO vo) throws SQLException {
		int count = 0;
		// SQL 명령 만들고
		String sql = "insert into guestbook (name,password,content,regDate,ip) values (?,?,?,now(),?)";
		// 명령 객체 만들고
		// 명령에 변하는 부분이 있으면 PreparedStatement 객체 생성
		// 명령에 변하는 부분이 없으면 Statement 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, vo.getIp());
		// 실행하고
		count = pstmt.executeUpdate();
		// 닫기
		JdbcUtil.close(pstmt);
		// 결과 보내기
		return count;
	}
	// 5. 수정하기
	public int update(Connection conn, GuestbookVO vo) throws SQLException {
		int count = 0;
		// SQL 명령 만들고
		String sql = "update guestbook set content=?, regDate=now(), ip=? where idx=?";
		// 명령 객체 만들고
		// 명령에 변하는 부분이 있으면 PreparedStatement 객체 생성
		// 명령에 변하는 부분이 없으면 Statement 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getIp());
		pstmt.setInt(3, vo.getIdx());
		// 실행하고
		count = pstmt.executeUpdate();
		// 닫기
		JdbcUtil.close(pstmt);
		// 결과 보내기
		return count;
	}
	// 6. 삭제하기
	public int delete(Connection conn, int idx) throws SQLException {
		int count = 0;
		// SQL 명령 만들고
		String sql = "delete from guestbook where idx=?";
		// 명령 객체 만들고
		// 명령에 변하는 부분이 있으면 PreparedStatement 객체 생성
		// 명령에 변하는 부분이 없으면 Statement 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		// 실행하고
		count = pstmt.executeUpdate();
		// 닫기
		JdbcUtil.close(pstmt);
		// 결과 보내기
		return count;
	}
	// ==================================================== //
}
