package kr.or.ddit.dao.login;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public class LoginDAOImple implements ILoginDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public String idCheck(String memId) {
		return sqlSession.selectOne("Login.idCheck", memId); 
	}

	@Override
	public int insertMember(MemberVO member) {
		return sqlSession.insert("Login.insertMember", member);
	}

	@Override
	public MemberVO idPwCheck(MemberVO member) {
		return sqlSession.selectOne("Login.idPwCheck", member);
	}

}
