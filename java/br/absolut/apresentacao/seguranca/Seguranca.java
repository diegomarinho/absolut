package br.absolut.apresentacao.seguranca;

import java.io.IOException;

import br.absolut.apresentacao.ActAbsolut;

public class Seguranca extends ActAbsolut {
	private static final long serialVersionUID = -6039301273162876414L;
	public static final int PERFIL_ACESSO_GERENTE = 1;
	public static final int PERFIL_ACESSO_FUNCIONARIO = 2;
	public static final int PERFIL_ACESSO_PUBLICO = 3;

	public Seguranca(int permissaoAcesso) throws Exception {
		super(permissaoAcesso);
		//		try {
		//			this.verificaSeUsuarioLogado();
			this.verificaPermissaoAcesso();
		//		} catch(Exception e) {
		//			this.exibeTelaErro();
		//		}

	}

	@SuppressWarnings("unused")
	private void verificaSeUsuarioLogado() throws IOException {
		if (this.getPermissaoAcesso() != PERFIL_ACESSO_PUBLICO) {
			if (this.getDtoUsuario() == null) {
				this.getHttpServletResponse().sendRedirect("/absolut/web/apresentacao/erro/usuarioNaoLogado.faces");
			}
		}
	}

	private void verificaPermissaoAcesso() throws IOException {
		if (this.getDtoUsuario() != null) {
			if (this.getPermissaoAcesso() < Integer.parseInt(this
					.getDtoUsuario().getAcesso().toString())) {
				this.getHttpServletResponse().sendRedirect("/absolut/web/apresentacao/erro/usuarioSemPermissao.faces");
			}
		}
	}

	public void exibeTelaErro() throws Exception {
		this.getHttpServletResponse().sendRedirect("/absolut/web/apresentacao/erro/erro.faces");
		
	}
	
}
