package kr.green.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 리스트라면 서비스 클래스의 메소드를 호출하여 Paging클래스 객체를 만들어서 저장
		request.setAttribute("p", 1);
		request.setAttribute("s", 1);
		request.setAttribute("b", 1);
		return "list";
	}
}
