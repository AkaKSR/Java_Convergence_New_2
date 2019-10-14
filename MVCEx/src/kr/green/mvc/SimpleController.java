package kr.green.mvc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Simple")
public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SimpleController() { // 생성자
    	
    }

    // GET방식의 요청이 있을때 실행되는 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		req.setCharacterEncoding("UTF-8");
		// 1. 요청 정보를 받는다.
		String cmd = req.getParameter("cmd");
		if (cmd==null) cmd="none";
		// 2. 받은 정보에 따라 필요한 로직을 수행한다.
		String result = "";
		switch (cmd) {
		case "date":
			// 3. 실제 로직 수행
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
			result = sdf.format(new Date()) + "<br>실행된 명령: " + cmd;
			break;
			
		case "time":
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
			result = sdf2.format(new Date()) + "<br>실행된 명령: " + cmd;
			break;
			
		case "now":
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 hh:mm:ss");
			result = sdf3.format(new Date()) + "<br>실행된 명령: " + cmd;
			break;

		case "hello":
			result = "안녕하세요!! 반갑습니다. MVC!!<br>실행된 명령: " + cmd;
			break;
			
		default:
			result = "알수 없는 명령입니다.<br>실행된 명령: " + cmd;
			break;
		}
		// 4. 요청 결과를 session이나 request에 저장한다.
		request.setAttribute("result", result);
		// 5. 보여줄 페이지로 포워딩한다.
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/result.jsp");
		dispatcher.forward(request, response);
	}

	// POST방식의 요청이 있을때 실행되는 메서드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
