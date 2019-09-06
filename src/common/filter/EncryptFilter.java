package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import common.wrapper.EncryptWrapper;

@WebFilter(filterName = "encrypt", servletNames = { "CheckPwd", "LoginServlet", "SignUpServlet" })
public class EncryptFilter implements Filter {

	public EncryptFilter() {
		System.out.println("EncryptFilter 작동");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest) request;
		EncryptWrapper ew = new EncryptWrapper(hsr);

		chain.doFilter(ew, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
