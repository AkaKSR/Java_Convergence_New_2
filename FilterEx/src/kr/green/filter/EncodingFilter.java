package kr.green.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "charSet", value = "UTF-8")
		})
public class EncodingFilter implements Filter {

    public EncodingFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 여기서 CharSet을 지정
		request.setCharacterEncoding(charSet);
		chain.doFilter(request, response);
	}

	private String charSet;
	public void init(FilterConfig fConfig) throws ServletException {
		// 초기화 파라미터는 init에서 읽어 변수에 저장을 한다.
		charSet = fConfig.getInitParameter("charSet");
	}

}
