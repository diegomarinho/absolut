package br.absolut.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ControleAcessoFilter
 */
public class ControleAcessoFilter implements Filter {

	public ControleAcessoFilter() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		HttpSession session = (HttpSession) hreq.getSession();
		
		if(hreq.equals(null));
		if(hresp.equals(null));
		if(session.equals(null));
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
