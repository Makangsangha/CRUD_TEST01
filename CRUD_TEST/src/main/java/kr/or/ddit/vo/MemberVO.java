package kr.or.ddit.vo;

import lombok.Data;

@Data
public class MemberVO {
	private String memNo;
	private String memId;
	private String memPw;
	private String memName;
	private String memGender;
	private String memPhone;
	private String memEmail;
	private String memAgree;
}
