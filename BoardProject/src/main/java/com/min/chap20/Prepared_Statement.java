package com.min.chap20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 문장을 매개변수화 하기
// 비슷한 질의문을 여러번 사용
public class Prepared_Statement {
	
	public static void main(String[] args) throws SQLException {
		
	// 1. 드라이버 로딩
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Getting Connection");
			
			// 2. DB연결 객체 생성
			Connection conn = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/board","root","1234");
			
			// 3. 테이블 정보에 연결하는 객체 생성
			Statement stmt = conn.createStatement(); // 질의를 문자열형태로 db에 보냄
			
//			ResultSet rs = stmt.executeQuery(
//					"SELECT * FROM BOARD");
			
			String query = "INSERT INTO BOARD(TITLE,CONTENT) VALUES (?,?)";
//					"SELECT * FROM BOARD WHERE TITLE=?";
//			"INSERT INTO BOARD(TITLE,CONTENT) VALUES (?,?)"
			String query2 = "SELECT * FROM BOARD WHERE TITLE = ?";
			PreparedStatement pr = conn.prepareStatement(query);
			PreparedStatement pr2 = conn.prepareStatement(query2);
			
			pr.setString(1,"A");
			pr.setString(2,"B");
			System.out.println(pr);
			ResultSet rs = pr.executeQuery();
			
//			pr2.setString(1, "A");
//			pr2.executeUpdate(); // DBMS에서 DML 개념
//			pr2.executeQuery();
//			ResultSet rs2 = pr2.executeQuery();
			rs.close();
//			rs2.close();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
}
}
