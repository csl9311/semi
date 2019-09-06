package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "encoding", urlPatterns = { "/" })
public class CommonFilter implements Filter {

    public CommonFilter() {
        System.out.println("CommonFilter 작동");
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1. 뷰에서 전달 받은 값에 한글이 있는 경우
		request.setCharacterEncoding("UTF-8");
		
		//2. 뷰로 전달할 값에 한글이 있는 경우
		response.setContentType("text/html; charset=UTF-8");

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
