package com.min.chap20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetUpdate {
	
	// ResultSet = SELECT 결과를 저장하는 객체
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/board","root","1234");
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM BOARD");
        rs.first();
        rs.updateString("TITLE", "BBBBBBBBBBBBBBBB");
        rs.getFetchSize(); // 반복문을 사용해야함 
//        rs.next(); // 다음 레코드로 이동
//        rs.updateString("TITLE", "cccccccccccc");
        // 이 외에 previous, absolute(int row)등등 
        rs.updateRow();
        System.out.println("성공");
//        rs.deleteRow(); // 삭제됨
        rs.close();
        stmt.close();
        con.close();
        }catch(SQLException ex) {
        	System.err.println("SQLException" + ex.getMessage());
        }
		
	}

}
