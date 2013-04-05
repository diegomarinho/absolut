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

import br.absolut.apresentacao.DtoUsuario;

/**
 * Servlet Filter implementation class ControleSessaoFilter
 */
public class ControleSessaoFilter implements Filter {
	private static final String FILTER_APPLIED = "_security_filter_applied";

	public ControleSessaoFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		HttpSession session = (HttpSession) hreq.getSession();

		hreq.getPathInfo();
		String paginaAtual = hreq.getRequestURL().toString();
		if (request.getAttribute(FILTER_APPLIED) == null && paginaAtual != null
				&& !paginaAtual.endsWith("login.faces")
				&& !paginaAtual.contains("/erro/")
				&& !paginaAtual.contains("/a4j/")
				&& paginaAtual.endsWith(".faces")) {
			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			
			DtoUsuario dtoUsuario = (DtoUsuario) session.getAttribute("dtoUsuario");
			if(dtoUsuario == null) {
				hresp.sendRedirect("/absolut/web/apresentacao/erro/usuarioNaoLogado.faces");
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
