package kr.or.ddit.service.login;

import kr.or.ddit.StateCheck;
import kr.or.ddit.vo.MemberVO;

public interface ILoginService {
	
	public StateCheck idCheck(String memId);

	public StateCheck insertMember(MemberVO member);

	public MemberVO idPwCheck(MemberVO member);
}
