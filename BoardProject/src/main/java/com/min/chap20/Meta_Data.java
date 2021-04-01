package com.min.chap20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Meta_Data {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/board","root","1234");
		Statement stmt = conn.createStatement(); // 질의를 문자열형태로 db에 보냄
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		String query = "SELECT * FROM BOARD";
		rs = stmt.executeQuery(query);
		
		ResultSetMetaData data = rs.getMetaData();
		System.out.println(data.getColumnCount()); // 총 필드수
		
		for (int i = 1; i < data.getColumnCount(); i++) { // 컬럼 목록 보여주기
			System.out.println(data.getColumnName(i)+ " "); // 해당위치의 컬럼명을 반환
			System.out.print(data.getColumnTypeName(i)+ " ");
			System.out.println("("+ data.getPrecision(i)+ ")");
		}
		conn.close();
		rs.close();
		stmt.close();
	}catch (ClassNotFoundException e) {
        e.printStackTrace();

} catch (SQLException e) {

        e.printStackTrace();

}

}
}