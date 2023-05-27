package kr.or.ddit.dao.login;

import kr.or.ddit.vo.MemberVO;

public interface ILoginDAO {

	public String idCheck(String memId);

	public int insertMember(MemberVO member);

	public MemberVO idPwCheck(MemberVO member);

}
