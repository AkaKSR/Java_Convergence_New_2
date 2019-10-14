package kr.green.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter2 implements Filter {

    public MyFilter2() {
    	System.out.println("MyFilter2 Constructor");
    }

	public void destroy() {
		System.out.println("MyFilter2 destroy()");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter2 doFilter 요청변경");
		chain.doFilter(request, response);
		System.out.println("MyFilter2 doFilter 응답변경");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("MyFilter2 init()");
	}

}
