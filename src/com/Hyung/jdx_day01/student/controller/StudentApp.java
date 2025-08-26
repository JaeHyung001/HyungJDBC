package com.Hyung.jdx_day01.student.controller;

import java.util.List;

import com.Hyung.jdx_day01.student.model.ManageStudent;
import com.Hyung.jdx_day01.student.model.Student;
import com.Hyung.jdx_day01.student.view.ViewStudent;

public class StudentApp {
	public static void main(String[] args) {
		ManageStudent manage = new ManageStudent();
		ViewStudent view = new ViewStudent();
		Student student;
		String name;
		int index;
		int result;
		
//		1. 학생 정보 입력
//		2. 학생 정보 이름 검색
//		3. 학생 정보 전체 출력
//		4. 학생 정보 수정
//		5. 학생 정보 삭제
//		6. 재평가 대상 여부 확인

		finish:
		while(true) {
			int menu = view.printMenu();
			switch(menu) {
			case 1: 
				student = view.inputStudent(); // 학생 정보를 입력받는 메소드
				result = manage.addStudent(student); // 입력받은 student 객체를 
											// addStudent 메소드를 통해 sList에 저장
				if(result > 0) {
					view.displayMsg("학생정보 추가 완료");
				} else {
					view.displayMsg("학생정보 추가 실패");
				}
				break;
			case 2: 
				name = view.inputName(); // 입력받은 이름을 name에 저장 후 
				student = manage.searchOneByName(name); // name으로 검색한 뒤
				if(student != null) {
					view.printStudent(student); // 해당 student의 정보 출력					
				} else {
					view.displayMsg("회원 정보가 존재하지 않습니다.");
				}
				break;
			case 3: 
				List<Student> sList = manage.getAllStudents();
				view.printStudents(sList); // 학생 정보 전체를 출력하는 메소드 
				break;
			case 4: 
				name = view.inputName(); // 수정할 이름을 입력 받고
				student = view.modifyStudent(name); // 수정할 점수를 입력받아 
													  // 해당 student에 넘겨 준 뒤
//				index = manage.searchIndexByName(name); // 수정하는 메소드가 index가 필요하므로
														// 이름으로 index값을 가져옴
//				manage.setStudent(index, student); // index와 student를 넘겨서 해당 위치의
												   // 객체를 replace 함.
				result = manage.setStudent(student);
				if(result >0) {
					view.displayMsg("데이터 수정 완료");
				} else {
					view.displayMsg("데이터 수정 실패");
				}
				break;
			case 5: 
				name = view.inputName(); // 삭제할 이름을 입력 받고
				index = manage.searchIndexByName(name); // 입력받은 이름을 sList에서 
														// 삭제하기 위해 index가 필요하여
														// 이름으로 인덱스를 찾기
				result = manage.removeStudent(name); // 인덱스를 전달하여 sList에서 해당 객체 삭제
				// 테이블에 있는 이름으로 삭제하기 위해 index -> name으로 변경
				if(result > 0) {
					view.displayMsg ("학생정보 삭제 완료");
				} else {
					System.out.println("학생정보 삭제 실패");
				}
				break;
			case 6: break;
			case 0: 
				view.displayMsg("프로그램이 종료되었습니다"); break finish;
			default :
				view.displayMsg("1 ~ 6 사이의 수를 입력하세요");
				break;
			}
		}
	}

}
