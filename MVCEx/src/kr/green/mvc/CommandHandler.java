package kr.green.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	// request와 response를 받아 보여줄 jsp페이지 이름을 리턴하는 메서드
	public String process(HttpServletRequest request, HttpServletResponse response);
	
}
