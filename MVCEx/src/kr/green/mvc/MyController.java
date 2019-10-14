package kr.green.mvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "configFile", value = "/WEB-INF/command.properties", description = "명령어와 맵핑된 클래스이름저장")
		})
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 모든 명령과 그 명령이 실행될 클래스의 인스턴스를 저장할 맵 선언 
	private Map<String, CommandHandler> commandMap = new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {
		// 서블릿이 메모리로 로드되면 설정 파일을 읽어서 
		// 명령과 명령을 처리할 핸들러 클래스의 객체를 맵에다 모두 저장한다.
		
		// 설정 파일의 이름을 초기화 파라메터에서 읽자!!!
		String configFile = config.getInitParameter("configFile");
		if(configFile==null) configFile="/WEB-INF/command.properties";
		// Properties 객체 생성
		Properties prop = new Properties();
		// 서버의 절대 경로
		String path = config.getServletContext().getRealPath(configFile);
		FileInputStream fis = null;
		try {
			// 파일 읽기
			fis = new FileInputStream(path);
			// 파일 내용을 Properties 객체로 읽기
			prop.load(fis);
			
			// Properties객체의 내용에 딸 객체를 생성해서 맵에 저장한다.
			Iterator<Object> it = prop.keySet().iterator(); // 키 반복자 얻기
			while(it.hasNext()) { // 반복
				String command = (String) it.next(); // 명령 얻기
				String commandHandlerName = prop.getProperty(command); // 객체 이름 얻기
				Class handlerClass = Class.forName(commandHandlerName); // 지정 이름의 클래스를 메모리로 로딩
				CommandHandler commandHandler = (CommandHandler) handlerClass.newInstance(); // 객체 생성
				commandMap.put(command, commandHandler);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
			}catch (Exception e) {
				;
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 명령을 해석해서 
		String command = request.getRequestURI(); // /컨텍스트패스/파일명
		if(command.indexOf(request.getContextPath())==0) { // 컨텍스트패스 시작되면
			command = command.substring(request.getContextPath().length()); // 그 이후를 잘라낸다.
		}
		System.out.println("명령 : " + command);
		// 명령을 이용하여 맵에서 원하는 객체를 얻어온다.
		CommandHandler handler = commandMap.get(command);
		if(handler==null) {
			handler = new NullHandler();
		}
		
		// 결과를 받고 
		// 결과를 영역에 저장하고  
		
		// 위의 2가지는 Handler클래서에서 처리
		String viewPage = "";
		try {
			viewPage = handler.process(request, response);
			System.out.println("/WEB-INF/view/" + viewPage + ".jsp");
		}catch (Exception e) {
			throw new ServletException(e); 
		}
		// 원하는 페이지로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/" + viewPage + ".jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
