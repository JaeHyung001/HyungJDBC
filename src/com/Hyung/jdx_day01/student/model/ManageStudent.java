package com.Hyung.jdx_day01.student.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageStudent implements ManageInterface{

	// 메인에서 바로 실행하는 것이 아니기 때문에 static이 필요없다.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "HYUNGJDBC";
	private final String PASSWORD = "HYUNGJDBC";
	private List<Student> sList;
	
	public ManageStudent() {
//		sList = new ArrayList<Student>(); //DB에 연결해서 누적이 되지 않기 위해
//		후처리쪽에 sList = new ArrayList<Student>()를 써서 초기화 시켜줌	
	}

	@Override
	public Student searchOneByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '"+name+"'";
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// 후처리
			if(rset.next()) {
				String studentName = rset.getString("STUDENT_NAME");
				int firstScore = rset.getInt("FIRST_SCORE");
				int secondScore = rset.getInt("SECOND_SCORE");
				// rset에서 가져온 필드를 여러개 리턴하지 못하므로
				// 객체 여러개를 담아서 리턴해야함
				Student student = new Student(studentName, firstScore, secondScore);
				return student;
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		for(int i = 0 ; i < sList.size() ; i++) {
//			if(sList.get(i).getName().equals(name)) { // sList ArrayList의 i번째 인덱스의 이름이 입력받은 name과 같으면
//				return sList.get(i); // 해당 객체를 반환
//			} else {
//				
//			}
//		}
		return null;
	}

	@Override
	public int searchIndexByName(String name) {
		for(int i = 0 ; i < sList.size(); i++) {
			if(sList.get(i).getName().equals(name)) {
				return i; // 인덱스만 반환
			}
		}
		return -1; // index가 0이면 첫번째 값이기 때문에 -1로 리턴
	}

	@Override
	public int addStudent(Student student) {
//		sList.add(student); // 정보가 입력된 student 객체를 sList에 저장
		// 어레이 리스트에 추가 .add
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. Statement 실행 : 워크시트 열기
			stmt = conn.createStatement();
			String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getName()+"', "+student.getFirstScore()+", "+student.getSecondScore() +")";
			result = stmt.executeUpdate(query); 
			// executeUpdate가 쿼리문을 실행하고 실행이 성공한 만큼의 
			// 정수값을 반환하기 때문에 ex) 5행이 추가되었습니다. 
//			if(result > 0) {
//				System.out.println("학생정보 추가 완료");
//			} else {
//				System.out.println("학생정보 추가 실패");
//			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public void setStudent(int index, Student student) {
		sList.set(index, student); //  위치 값 변경
	}
	
	@Override
	public int setStudent(Student student) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "UPDATE STUDENT_TBL SET FIRST_SCORE = '"+ student.getFirstScore() +"', SECOND_SCORE = '"+student.getSecondScore()+"' WHERE STUDENT_NAME = '"+student.getName()+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("학생성적 수정 완료");
			} else {
				System.out.println("학생성적 수정 완료");				
			}
			conn.close();
			stmt.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
//		String query = "UPDATE STUDENT_TBL SET FIRST_SCORE = '"+student.getFirstScore()+"', SECOND_SCORE = '"+student.getSecondScore()+"' WHERE STUDENT_NAME = '"+student.getName()+"'";
//		
//		Connection conn = null;
//		Statement stmt = null;
//		int result = 0;
//		
//		try {
//			Class.forName(DRIVER_NAME);
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);
//			if(result >0) {
//				System.out.println("데이터 수정 완료");
//			} else {
//				System.out.println("데이터 수정 실패");
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}

	@Override
	public void removeStudent(int index) {
		sList.remove(index); // 받아온 sList의 index i번째를 삭제
	}
	
	public List<Student> getAllStudents() { 
		// 학생 전체 출력을 위한 StudentApp에서 
		// view에서 sList를 사용하기 위한 메소드
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL";
		try {
			Class.forName(DRIVER_NAME); // 드라이버 등록
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DBMS 연결
			stmt = conn.createStatement(); // 워크시트 열기
			rset = stmt.executeQuery(query); // 쿼리문 실행 후 결과 받기
			// 후처리
			// rset에 있는 필드의 값을 Student의 필드에 하나씩 넣어줌
			// Student 객체는 List에 저장해서 보내줌
			sList = new ArrayList<Student>(); 
			// getAllStudents() 메소드가 동작할때마다 초기화해서 값이 누적되지 않도록 함.
			
			while(rset.next()) {
//				System.out.println("이름 : " + rset.getString("STUDENT_NAME") + 
//						"1차 성적 : " + rset.getInt("FIRST_SCORE") +
//						"2차 성적 : " + rset.getInt("SECOND_SCORE"));
				Student student = new Student();
				student.setName(rset.getString("STUDENT_NAME"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
				sList.add(student);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
	}

	@Override
	public int removeStudent(String name) {
//		// TODO Auto-generated method stub
//		String query = "DELETE FROM STUDENT_TBL WHERE STUDENT_NAME = '"+name+"'";
//		
//		try {
//			Class.forName(DRIVER_NAME);
//			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD );
//			Statement stmt = conn.createStatement();
//			
//			int result = stmt.executeUpdate(query);
//			if(result > 0) {
//				System.out.println("학생정보 삭제 완료");
//			} else {
//				System.out.println("학생정보 삭제 실패");
//			}
//					
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Connection conn = null; // 상단의 final의 안전한 자원해제를 위해 try 밖에다 사용
		Statement stmt = null;
		String query = "Delete From STUDENT_TBL WHERE STUDENT_NAME = '"+name+"';";
		int result = 0; // SELECT가 아니기 때문에 ResultSet rset = null; 을 사용하지 않음.
		try {
			Class.forName(DRIVER_NAME); // 드라이버 등록
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DBMS 연결. 드라이버매니저 클래스에 겟커넥션이라는 메소드를 통해 DBMS를 연결.
			stmt = conn.createStatement(); // 워크시트 열기
			result = stmt.executeUpdate(query); // 쿼리문을 실행하는 메소드로 실행 후 결과 받기
			// 쿼리문 실행할 때 Select 일때는 executeQuery, DML일때는 executeUpdate
			if(result > 0) {
				System.out.println("학생정보 삭제 완료");
			} else {
				System.out.println("학생정보 삭제 실패");
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
		return result;
		
	}

	@Override
	public void modifyStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
