package com.Hyung.day03.pstmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.Hyung.day03.pstmt.member.common.JDBCTemplate;
import com.Hyung.day03.pstmt.member.model.vo.Member;

public class MemberDAO {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "HYUNGJDBC";
	private final String PASSWORD = "HYUNGJDBC";
	
	// JDBC 연결
		public List<Member> selectList(Connection conn) throws SQLException {
			Statement stmt = null;
			ResultSet rset = null;
			List<Member> mList = null; 
			String query = "SELECT * FROM MEMBER_TBL ORDER BY MEMBER_ID ASC";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();
			// 후처리
			while(rset.next()) {
				Member member = this.rsetToMember(rset);
				mList.add(member);
			}
		return mList;
	}
		public Member selectOneById(String memberId, Connection conn) throws SQLException {
//			Statement stmt = null; // stmt
			PreparedStatement pstmt = null; // pstmt
			ResultSet rset = null;
			Member member = null;
//			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?"; // 위치홀더, 값이 들어갈 위치
			

//				stmt = conn.createStatement();
//				rset = stmt.executeQuery(query);
			pstmt = conn.prepareStatement(query); // 컴파일
			pstmt.setString(1, memberId); // memberId가 쿼리문 ?에 들어가게 하는 문장
			rset = pstmt.executeQuery();
			// 후처리 rset -> Member || List<Member> 
			if(rset.next()) {
				member = this.rsetToMember(rset);
			}
				rset.close();
				pstmt.close();
				conn.close();
				return member;		
		}
		public int insertMember(Member member, Connection conn) throws SQLException {
			// insert가 성공한 행의 개수
//			Statement stmt = null;
			PreparedStatement pstmt = null;
			String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
			int result = 0;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId()); // 1, 2, 3, 4 : 물음표 ?의 순서
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, String.valueOf(member.getGender())); // char + 문자열 = 문자열
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
//			stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);	
			result = pstmt.executeUpdate();	
		 
			pstmt.close();
			conn.close();
			return result;
		}
		
		public int updateMember(Member member, Connection conn) throws SQLException {
//			Statement stmt = null;
			PreparedStatement pstmt = null;
			String query = "UPDATE MEMBER_TBL SET Member_PWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
			int result = 0;

				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, member.getMemberPwd());
				pstmt.setString(2, member.getEmail());
				pstmt.setString(3, member.getPhone());
				pstmt.setString(4, member.getAddress());
				pstmt.setString(5, member.getHobby());
				pstmt.setString(6, member.getMemberId());
//				stmt = conn.createStatement();
//				result = stmt.executeUpdate(query);	
				result = pstmt.executeUpdate();	
				
				pstmt.close();
				conn.close();

			return result;
		}
		public int deleteMember(String memberId, Connection conn) throws SQLException {
			PreparedStatement pstmt = null;
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
			int result = 0;			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
				
			return result;			
		}
		
		private Member rsetToMember(ResultSet rset) throws SQLException {
			String memberId = rset.getString("MEMBER_ID");
			String memberPwd = rset.getString("MEMBER_PWD");
			String memberName = rset.getString("MEMBER_NAME");
			char gender = rset.getString("GENDER").charAt(0);
			int age = rset.getInt("AGE");
			String email = rset.getString("EMAIL");
			String phone = rset.getString("PHONE");
			String address = rset.getString("ADDRESS");
			String hobby = rset.getString("HOBBY");
			Date enrollDate = rset.getDate("ENROLL_DATE");
			Member member = new Member(memberId, memberPwd, memberName
					, gender, age, email, phone, address, hobby, enrollDate);
			return member;
		}
}
