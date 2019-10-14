package kr.green.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    public LoggingFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long startTime = System.nanoTime(); // 시작 시간
		chain.doFilter(request, response);
		long endTime = System.nanoTime(); // 종료 시간
		String file = ((HttpServletRequest)request).getRequestURI();
		System.out.println(file + " 동작 시간: " + (endTime-startTime) + "ns");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
