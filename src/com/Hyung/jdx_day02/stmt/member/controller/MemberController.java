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
		return mDao.selectOneById(memberId);
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
		int result = mDao.updateMember(member);
		return result;
	}
	public int deleteMember(String member) {
		int result = mDao.deleteMember(member);
		return result;
	}
}
