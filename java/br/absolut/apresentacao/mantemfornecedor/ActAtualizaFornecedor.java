package br.absolut.apresentacao.mantemfornecedor;

import javax.faces.event.ActionEvent;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActAtualizaFornecedor extends Seguranca {
	private static final long serialVersionUID = 7077893812127160033L;
	private CtlMantemFornecedor ctlFornecedor;
	private DtoFornecedor dtoFornecedor = new DtoFornecedor();
	private boolean ehAlteracao;
	private String codFornecedor;

	public ActAtualizaFornecedor() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);

		verificaSeEhAlteracao();
	}
	
	public String salvar() throws Exception {
		if(dtoFornecedor.getCod() != null && dtoFornecedor.getCod() != 0) {
			ctlFornecedor.alteraFornecedor(dtoFornecedor);
			addMsgInfo("Fornecedor alterado com sucesso!");
		} else {
			ctlFornecedor.incluiFornecedor(dtoFornecedor);
			addMsgInfo("Fornecedor cadastrado com sucesso!");
		}

		return "";
	}

	public void limpar(ActionEvent evt) {
		if (dtoFornecedor != null) {
			dtoFornecedor.setCnpj("");
			dtoFornecedor.setNome("");
			dtoFornecedor.setTelefone("");	
		} else {
			dtoFornecedor = new DtoFornecedor();
		}
	}

	private void verificaSeEhAlteracao() throws Exception {
		ActConsultaFornecedor actConsulta = (ActConsultaFornecedor) this
				.getHttpServletRequest().getSession().getAttribute(
						"actConsultaFornecedor");
		if (actConsulta != null) {
			if (actConsulta.getCodFornecedor() != null
					&& !actConsulta.getCodFornecedor().isEmpty()) {
				if (ctlFornecedor == null)
					ctlFornecedor = actConsulta.getCtlFornecedor();

				dtoFornecedor = ctlFornecedor.recuperaFornecedor(new Long(actConsulta
						.getCodFornecedor()));
				codFornecedor = dtoFornecedor.getCod().toString();
				this.retiraObjetoSessao("actConsultaFornecedor");

				ehAlteracao = true;
			}
		}

	}

	public DtoFornecedor getDtoFornecedor() {
		return dtoFornecedor;
	}

	public void setDtoFornecedor(DtoFornecedor dtoFornecedor) {
		this.dtoFornecedor = dtoFornecedor;
	}

	public String getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(String codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public void setCtlFornecedor(CtlMantemFornecedor ctlFornecedor) {
		this.ctlFornecedor = ctlFornecedor;
	}

	public boolean isEhAlteracao() {
		return ehAlteracao;
	}

	public void setEhAlteracao(boolean ehAlteracao) {
		this.ehAlteracao = ehAlteracao;
	}

}
