package com.Hyung.jdx_day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.Hyung.jdx_day02.stmt.member.controller.MemberController;
import com.Hyung.jdx_day02.stmt.member.model.vo.Member;

public class MemberView {
	private MemberController mController;
	
	public MemberView() {
		mController = new MemberController();
	}
	// Run에서 시작할 메소드
	public void startProgram() {
		Member member;
		int result;
		finish:
		while(true) {
			int choice = this.printMenu();
			switch(choice) {
			case 1: 
				member = addMember();
				result = mController.registerMember(member);
				if(result > 0) {
					printMessage("회원 가입 완료");
				} else {
					printMessage("회원 가입 불가");
				}
				break;
			case 2: 
				List<Member> mList = mController.showMemberList();
				break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 0: 
				printMessage("프로그램을 종료합니다.");
				break finish;
			default : this.printMessage("1 ~ 5 사이의 수를 입력해주세요.");
			}
		}
		
	}
	// 아이디 검색시 아이디 입력받기
	private String inputMemberId() {
		return null;
	}
	// 학생 정보 추가시 학생 정보 입력받기
	private Member addMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======== 회원 정보 입력 ========");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().toUpperCase().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 전화번호 엔터 후 주소가 씹히는걸 방지
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member(memberId, memberPwd, memberName, 
				gender, age, email, phone, address, hobby);
		return member;
	}
	// 학생 정보 수정시 수정 정보 입력받기
	private Member ModifyMember(String memberId) {
		return null;
	}
	// 학생 1개 정보 출력
	private void printOne(Member member) {
		
	}
	// 학생 전체 정보 출력
	private void printAllMember(List<Member> mList) {
		System.out.println("======== 회원 전체 정보 ========");
		System.out.println("아이디\t\t이름\t\t이메일\t\t\t전화번호\t\t주소");
		for(Member member: mList) {
			System.out.println(member.getMemberId()+"\t"+member.getMemberName()
			+"\t\t"+member.getEmail()+"\t"+member.getPhone()+"\t\t"+member.getAddress());
		}
	}
	// 메시지 출력
	private void printMessage(String message) {
		System.out.println(message);
	}
	// 메뉴 출력
	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======== 회원 관리 프로그램 ========");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디)");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.println("메뉴 선택 : ");
		return sc.nextInt();
		// 1. 회원가입
		// 2. 회원 전체 조회
		// 3. 회원 검색(아이디)
		// 4. 회원 정보 수정
		// 4. 회원 정보 삭제
		// 0. 프로그램 종료
	}
}
