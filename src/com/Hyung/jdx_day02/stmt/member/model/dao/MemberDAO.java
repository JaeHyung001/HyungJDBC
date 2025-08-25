package com.Hyung.jdx_day02.stmt.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Hyung.jdx_day02.stmt.member.model.vo.Member;

public class MemberDAO {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "HYUNGJDBC";
	private final String PASSWORD = "HYUNGJDBC";
	
	// JDBC 연결
		public List<Member> selectList() {
			Connection conn =null;
			Statement stmt = null;
			ResultSet rset = null;
			List<Member> mList = null;
			String query = "SELECT * FROM MEMBER_TBL ORDER BY MEMBER_ID ASC";
			
			try {
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				mList = new ArrayList<Member>();
				// 후처리
				while(rset.next()) {
					String memberId = rset.getString("MEMBER_ID");
					String memberPwd = rset.getString("MEMBER_PWD");
					String memberName = rset.getString("MEMBER_NAME");
					char gender = rset.getString("GENDER").charAt(0);
					int age = rset.getInt("AGE");
					String email = rset.getString("EMAIL");
					String phone = rset.getString("PHONE");
					String address = rset.getString("ADDRESS");
					String hobby = rset.getString("HOBBY");
					Date enrollDate = rset.getDate("ENROLL DATE");
					Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
					mList.add(member);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mList;
		}
		public Member selectOneById(String memberId) {
			return null;
		}
		public int insertMember(Member member) {
			// insert가 성공한 행의 개수
			Connection conn = null;
			Statement stmt = null;
			String query = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberId()
															+"','"+member.getMemberPwd()
															+"','"+member.getMemberName()
															+"','"+member.getGender()
															+"',"+member.getAge()
															+",'"+member.getEmail()
															+"','"+member.getPhone()
															+"','"+member.getAddress()
															+"','"+member.getHobby()
															+"', DEFAULT)";
			int result = 0;
			try {
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.createStatement();
				result = stmt.executeUpdate(query);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}
		public int updateMember(Member member) {
			return 0;
		}
		public int deleteMember(String memberId) {
			return 0;			
		}
}
