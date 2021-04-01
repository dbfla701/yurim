package com.min.chap20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class Connect {
	
	public static void main(String[] args) throws SQLException {
		String dbURL = "jdbc:mariadb://localhost:3306/board";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			DriverManager.setLogStream(System.err);
			
			System.out.println("Getting Connection");
			Connection conn = 
					DriverManager.getConnection(dbURL, "root", "1234");
			
			SQLWarning warn = conn.getWarnings(); // 발생하는 부가적인 경고를 출력한다.
			while (warn != null) {
				System.out.println("SQLState:"+ warn.getSQLState());
				System.out.println("Message: " + warn.getMessage()); 
				System.out.println("Vendor: " + warn.getErrorCode());
				System.out.println("");
				warn = warn.getNextWarning();
			}
			// 여기서 연결 진행
			System.out.println("연결?");
			conn.close(); // DB연결을 종료한다.
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load driver" + e);
		} catch (SQLException e) {
			System.out.println("Database access failed" + e);
		}
	}

}
