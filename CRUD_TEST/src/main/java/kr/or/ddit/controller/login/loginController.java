package kr.or.ddit.controller.login;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.StateCheck;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
public class loginController {

	@Inject
	ILoginService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		log.info("loginForm() 실행...!");
		return "pages/ddit_signin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO member, Model model, HttpServletRequest request) {
		log.info("login() 실행...!");
		member.setMemId(member.getMemId().trim());
		member.setMemPw(member.getMemPw().trim());
		HttpSession session = request.getSession();
		String url = "";
		if(member.getMemId().trim().equals("")&&member.getMemId()==null&&
				member.getMemPw().trim().equals("")&&member.getMemPw()==null) {	
			model.addAttribute("message", "누락된 입력 정보가 존재합니다.");
			url = "pages/ddit_signin";
		}else {		
			MemberVO loginMember = service.idPwCheck(member);
			if(loginMember != null) {
				session.setAttribute("login", loginMember);
				url = "redirect: /board/boardList";
			}else {
				model.addAttribute("message", "존재하지 않는 회원입니다.");
				url = "pages/ddit_signin";
			}
		}
		return url;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)	
	public String logout(HttpServletRequest request) {
		log.info("logout() 실행...!");
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		return "redirect:/board/boardList";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm() {
		log.info("joinForm() 실행...!");
		return "pages/ddit_signup";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(MemberVO member, Model model) {
		log.info("join() 실행...!");

		String url = "";

		if (member.getMemId().trim() == "" && member.getMemPw().trim() == "" && member.getMemName().trim() == ""
				&& member.getMemPhone().trim() == "" && member.getMemEmail().trim() == ""
				&& member.getMemGender().trim() == "" && member.getMemAgree().trim() == "" && member.getMemId() == null
				&& member.getMemEmail() == null && member.getMemGender() == null && member.getMemName() == null
				&& member.getMemPhone() == null && member.getMemPw() == null) {
			model.addAttribute("loginError", "누락된 입력 정보가 존재합니다.");
			url = "pages/ddit_signup";
		} else if (!member.getMemAgree().equals("Y")) {
			model.addAttribute("message", "가입이 실패되었습니다. 다시 시도해주세요.");
			url = "pages/ddit_signup";
		} else {
			StateCheck check = service.idCheck(member.getMemId());
			if (check == StateCheck.FAIL) {
				model.addAttribute("message", "중복된 아이디가 존재합니다.");
				url = "pages/ddit_signup";
			} else {
				if (member.getMemGender().equals("M")) {
					member.setMemGender("남자");
				} else if (member.getMemGender().equals("F")) {
					member.setMemGender("여자");
				}
				member.setMemId(member.getMemId().trim());
				member.setMemPw(member.getMemPw().trim());
				member.setMemName(member.getMemName().trim());
				member.setMemPhone(member.getMemPhone().trim());
				member.setMemEmail(member.getMemEmail().trim());
				member.setMemGender(member.getMemGender().trim());
				member.setMemAgree(member.getMemAgree().trim());

				StateCheck check2 = service.insertMember(member);
				if (check2 == StateCheck.FAIL) {
					model.addAttribute("message", "가입이 실패되었습니다. 다시 시도해주세요.");
					url = "pages/ddit_signup";
				} else if (check2 == StateCheck.OK) {
					model.addAttribute("message", "가입이 완료되었습니다.");
					url = "pages/ddit_signin";
				}
			}
		}

		return url;
	}

}
