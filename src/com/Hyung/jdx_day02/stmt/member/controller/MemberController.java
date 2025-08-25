package com.Hyung.jdx_day02.stmt.member.controller;

import java.util.List;

import com.Hyung.jdx_day02.stmt.member.model.dao.MemberDAO;
import com.Hyung.jdx_day02.stmt.member.model.vo.Member;

public class MemberController {
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public Member findOneById(String memberId) {
		return null;
	}
	public List<Member> showMemberList() {
		List<Member> mList = mDao.selectList();
		return mList;
	}
	public int registerMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}
	public int updateMember(Member member) {
		return 0;
	}
	public int deleteMember(String member) {
		return 0;
	}
}
