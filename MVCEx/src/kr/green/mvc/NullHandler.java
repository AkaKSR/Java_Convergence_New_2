package kr.green.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모든 핸들러 클래스는 CommandHandler 인터페이스를 구현해야 한다.
public class NullHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 여기에서 로직을 처리하고
		request.setAttribute("message", "알수 없는 명령입니다.");
		
		// 뷰페이지 이름을 리턴하도록 작성한다.
		return "error";
	}
}
