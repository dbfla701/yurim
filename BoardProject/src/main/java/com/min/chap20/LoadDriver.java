package com.min.chap20;

public class LoadDriver {
	
	public static void main(String[] args) {
		try {
			Class<?> c =Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Loaded"+c);
			
		}catch(ClassNotFoundException ex) {
			System.err.println(ex);
		}
	}

}
