package kr.green.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") // 필터 자동 등록(Annotation)
public class MyFilter1 implements Filter {

    public MyFilter1() {
    	System.out.println("MyFilter1 Constructor");
    }

	public void destroy() {
		System.out.println("MyFilter1 destroy()");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter1 doFilter 요청변경");
		chain.doFilter(request, response);
		System.out.println("MyFilter1 doFilter 응답변경");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("MyFilter1 init()");
	}

}
