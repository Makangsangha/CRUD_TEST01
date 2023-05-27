package kr.or.ddit.service.login;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.StateCheck;
import kr.or.ddit.dao.login.ILoginDAO;
import kr.or.ddit.vo.MemberVO;

@Service
public class LoginServiceImpl implements ILoginService {

	@Inject
	private ILoginDAO dao;
	
	@Override
	public StateCheck idCheck(String memId) {
		StateCheck check;
		String check_ = dao.idCheck(memId);
		if(check_ == null) {
			check =  StateCheck.OK;
		}else {
			check = StateCheck.FAIL;
		}
		return check;
	}

	@Override
	public StateCheck insertMember(MemberVO member) {
		StateCheck check;
		int check_ = dao.insertMember(member);
		if(check_ > 0) {
			check = StateCheck.OK;
		}else {			
			check = StateCheck.FAIL;
		}
		return check;
	}

	@Override
	public MemberVO idPwCheck(MemberVO member) {
		MemberVO check = dao.idPwCheck(member);
		if(check != null) {
			return check;
		}else {			
			return null;
		}
	}
	


}
