package service;

import java.util.HashMap;
import java.util.Map;

import exception.InvalidMemberIdException;
import member.Member;

public class MemberService {
	private Map<String, Member> memberMap;
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	private MemberService() {
		memberMap = new HashMap<String, Member>();
	}
	
	public Member getMember(String memberId) throws InvalidMemberIdException {
		if(!memberMap.containsKey(memberId)) {
			throw new InvalidMemberIdException("Member Id provided is invalid");
		}
		return memberMap.get(memberId);
	}
	public void addMember(Member member) {
		memberMap.put(member.getMemberId(), member);
	}
}
