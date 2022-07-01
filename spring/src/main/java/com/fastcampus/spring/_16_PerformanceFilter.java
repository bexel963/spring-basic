package com.fastcampus.spring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


// 필터를 적용할 요청의 패턴을 지정 - 모든 요청에 필터를 적용.
// 서블릿을 등록할 때는 @WebServlet을 사용하고 필터를 등록할 때는 @WebFilter를 사용한다.
@WebFilter(urlPatterns="/*")
public class _16_PerformanceFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 초기화 작업
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 전처리 작업
		long startTime = System.currentTimeMillis();
		
		// 2. 서블릿 또는 다음 필터를 호출
		chain.doFilter(request, response);
		
		// 3. 후처리 작업
		HttpServletRequest req = (HttpServletRequest)request;
		String referer = req.getHeader("referer");	// 어디서 요청을 했는지 알 수 있다.
		String method = req.getMethod();
		
		System.out.println("["+referer+"]  -> " + method + " [" + req.getRequestURI() + "]");
//		System.out.println(" 소요시간 = " + (System.currentTimeMillis() - startTime) + "ms");
		
		// 2번은 고정이고, 1번과 3번에 원하는 작업을 작성해주면 된다.
	}

	@Override
	public void destroy() {
		// 정리 작업
	}

}
