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


// ���͸� ������ ��û�� ������ ���� - ��� ��û�� ���͸� ����.
// ������ ����� ���� @WebServlet�� ����ϰ� ���͸� ����� ���� @WebFilter�� ����Ѵ�.
@WebFilter(urlPatterns="/*")
public class _16_PerformanceFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// �ʱ�ȭ �۾�
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. ��ó�� �۾�
		long startTime = System.currentTimeMillis();
		
		// 2. ���� �Ǵ� ���� ���͸� ȣ��
		chain.doFilter(request, response);
		
		// 3. ��ó�� �۾�
		HttpServletRequest req = (HttpServletRequest)request;
		String referer = req.getHeader("referer");	// ��� ��û�� �ߴ��� �� �� �ִ�.
		String method = req.getMethod();
		
		System.out.println("["+referer+"]  -> " + method + " [" + req.getRequestURI() + "]");
//		System.out.println(" �ҿ�ð� = " + (System.currentTimeMillis() - startTime) + "ms");
		
		// 2���� �����̰�, 1���� 3���� ���ϴ� �۾��� �ۼ����ָ� �ȴ�.
	}

	@Override
	public void destroy() {
		// ���� �۾�
	}

}
