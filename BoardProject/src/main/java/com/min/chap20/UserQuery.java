package com.min.chap20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DB에서 질의에 대한 결과물 얻기
// Statement를 가져와서 질의 수행한다.
// -> ResultSet 객체를 얻음
public class UserQuery {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// 1. 드라이버 로딩
		Class.forName("org.mariadb.jdbc.Driver");
		
		System.out.println("Getting Connection");
		
		// 2. DB연결 객체 생성
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/board","root","1234");
		
		// 3. 테이블 정보에 연결하는 객체 생성
		Statement stmt = conn.createStatement(); // 질의를 문자열형태로 db에 보냄
		
		ResultSet rs = stmt.executeQuery(
				"SELECT * FROM BOARD");
		
		while (rs.next()) {
			String seq= rs.getString(1);
			String content = rs.getString(3);
			System.out.println("User \t" + seq+"\t is named \t"+content); // 1번= seq, 3번= content
		}
		rs.close();
		stmt.close();
		conn.close();
		System.exit(0);
	}

}
