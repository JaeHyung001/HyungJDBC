package com.Hyung.jdx_day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static  String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String USER = "KH";
	private final static String PASSWORD = "KH";	
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기 - 후처리
	 * 6. 자원해제
	 */
	public static void main(String[] args) {
		updateRun();
	}
	
	public static void updateRun() {
		String query = "UPDATE EMPLOYEE SET EMP_NAME = '섬동일' WHERE EMP_ID = '200'";
		Connection conn = null;
		Statement stmt = null;
		// INSERT 할건데 ResultSet이 필요할까? : 필요없음
		int result = -1;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0 ) {
				System.out.println("데이터 수정이 완료되었습니다.");
			} else {
				System.out.println("데이터 수정이 완료되지 않았습니다.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} 
		}
	}
	
	
	public static void insertRun() {
		String query = "INSERT INTO EMPLOYEE VALUES('201', '선동일', '001122-1234567', 'di_sun@kh.or.kr', '01033345556', 'D9', 'J1', 'S1', 8000000, 0.5, null, Sysdate, null, 'N')";
		Connection conn = null;
		Statement stmt = null;
		// INSERT 할건데 ResultSet이 필요할까? : 필요없음
		int result = -1;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0 ) {
				System.out.println("데이터 추가가 완료되었습니다.");
			} else {
				System.out.println("데이터 추가가 완료되지 않았습니다.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} 
		}
	}
		
		public void deleteRun() {
//			String driver = "oracle.jdbc.driver.OracleDriver";
//			String url = "jdbc:oracle:thin:@localhost:1521:XE";
//			String user = "KH";
//			String password = "KH";
			String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = 200";
			Connection conn = null;
			Statement stmt = null;
			ResultSet rset = null;
			int result = -1;
			
			// 1. 드라이버 등록
			try {
				Class.forName(DRIVER_NAME);
				// 2, DBMS 연결
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				// 3. Statement 생성
				stmt = conn.createStatement(); // 워크시트 열기
				result = stmt.executeUpdate(query);
				// 오토커밋 해제 방법
//				conn.setAutoCommit(false);
				if( result > 0) {
//					conn.commit();
					System.out.println("데이터 삭제가 완료되었습니다.");
				} else {
//					conn.rollback();
					System.out.println("데이터 삭제가 완료되지 않았습니다.");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	public void selectRun() {
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String user = "KH";
//		String password = "KH";
		String query = "SELECT * FROM EMPLOYEE";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 1. 드라이버 등록 : 컴퓨터와 스마트폰이 서로 연결할때 드라이버가 필요하듯이
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // 접속하기
			stmt = conn.createStatement(); //워크시트 열기
			rset = stmt.executeQuery(query); // 컨트롤 + 엔터
			// 후처리 (컬럼에 무슨값이 있는지 안보이기 때문에)
			while(rset.next()) {
				System.out.println("사번 : " + rset.getString("EMP_NAME") + "\t이름 : " + rset.getString("EMP_NAME") + "\t월급 : " + rset.getString("SALARY"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
