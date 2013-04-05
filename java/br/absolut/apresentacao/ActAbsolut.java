package br.absolut.apresentacao;

import java.io.Serializable;
import java.util.Enumeration;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActAbsolut implements Serializable {

	private static final long serialVersionUID = 1485693278244039299L;

	public static String getContextoSistema() {
		return "/neoorus";
	}

	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private DtoUsuario dtoUsuario;
	private int permissaoAcesso;

	private String styleMsg;

	public ActAbsolut() {
	}

	public ActAbsolut(int permissaoAcesso) {
		this.permissaoAcesso = permissaoAcesso;
		dtoUsuario = (DtoUsuario) getHttpServletRequest().getSession()
				.getAttribute("dtoUsuario");

	}

	protected void addMsgAlerta(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "  " + mensagem,
						null));
	}

	protected void addMsgErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "  " + mensagem,
						null));
	}

	protected void addMsgInfo(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "  " + mensagem,
						null));
	}

	public DtoUsuario getDtoUsuario() {
		return dtoUsuario;
	}

	protected HttpServletRequest getHttpServletRequest() {
		httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		if (httpServletRequest == null) {
			throw new RuntimeException("ServletRequest � nula.");
		}
		return httpServletRequest;
	}

	protected HttpServletResponse getHttpServletResponse() {
		httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		return httpServletResponse;
	}

	public int getPermissaoAcesso() {
		return permissaoAcesso;
	}

	public String getStyleMsg() {
		return styleMsg;
	}

	protected String recuperaParametro(String nomeParametro) {
		return recuperaParametro(nomeParametro, true);
	}

	protected String recuperaParametro(String nomeParametro, ActionEvent ae) {
		return recuperaParametro(nomeParametro, true, ae);
	}

	protected String recuperaParametro(String nomeParametro,
			boolean seValorObrigatorio) {
		String valorParametro = null;
		String nomeParametroHidden = "hid"
				+ nomeParametro.substring(0, 1).toUpperCase()
				+ nomeParametro.substring(1);

		// tenta recuperar o par�metro a partir da URL
		if (getHttpServletRequest().getParameter(nomeParametro) != null) {
			valorParametro = getHttpServletRequest()
					.getParameter(nomeParametro);
		}

		// se ainda n�o encontrou, tenta recuperar o par�metro oculto a
		// partir
		// da URL
		if ((valorParametro == null)
				&& (getHttpServletRequest().getParameter(nomeParametroHidden) != null)) {
			valorParametro = getHttpServletRequest().getParameter(
					nomeParametroHidden);
		}

		// se ainda n�o encontrou, tenta recuperar o par�metro a partir de
		// um
		// atributo na p�gina
		if ((valorParametro == null)
				&& (getHttpServletRequest().getAttribute(nomeParametro) != null)) {
			valorParametro = (String) getHttpServletRequest().getAttribute(
					nomeParametro);
		}

		// se ainda n�o achou, tenta recuperar o par�metro a partir de um
		// campo
		// oculto na p�gina
		if (valorParametro == null) {
			valorParametro = recuperarAtributoTela(nomeParametroHidden);
		}

		// verifica a obrigatoriedade do par�metro
		if ((valorParametro == null) && seValorObrigatorio) {
			throw new RuntimeException(
					"N�o foi passado como par�metro na URL um valor para "
							+ nomeParametro);
		}

		return valorParametro;
	}

	protected String recuperaParametro(String nomeParametro,
			boolean seValorObrigatorio, ActionEvent ae) {

		String valorParametro = null;

		// verifica se o ActionEvent foi passado
		if (ae != null) {

			// busca valor na lista de par�metros
			UIComponent uiComponent = ae.getComponent().findComponent(
					nomeParametro);
			if (uiComponent != null) {
				Object object = ((UIParameter) uiComponent).getValue();
				if (object != null) {
					valorParametro = object.toString();
				}
			}
		}

		// se n�o encontrou, busca valor na lista de objetos do request
		if (valorParametro == null) {
			return recuperaParametro(nomeParametro, seValorObrigatorio);
		}

		return valorParametro;
	}

	@SuppressWarnings("unchecked")
	protected String recuperarAtributoTela(String nomeAtributo) {
		String valor = "";
		HttpServletRequest req = this.getHttpServletRequest();
		for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
			String nomeParametro = (String) e.nextElement();
			if (nomeParametro != null) {
				if (nomeParametro.equalsIgnoreCase(nomeAtributo)
						|| nomeParametro.endsWith(":" + nomeAtributo)) {
					valor = req.getParameter(nomeParametro);
					if ((valor == null) || valor.isEmpty()) {
						valor = (String) req.getAttribute(nomeParametro);
					}

					return valor;
				}
			}
		}
		return null;
	}

	protected void retiraObjetoSessao(String objeto) {
		this.getHttpServletRequest().getSession().removeAttribute(objeto);

	}

	public void setDtoUsuario(DtoUsuario dtoUsuario) {
		this.dtoUsuario = dtoUsuario;
	}

	public void setPermissaoAcesso(int permissaoAcesso) {
		this.permissaoAcesso = permissaoAcesso;
	}

	public void setStyleMsg(String styleMsg) {
		this.styleMsg = styleMsg;
	}

}
