package br.absolut.apresentacao.mantemcliente;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActAtualizaCliente extends Seguranca {
	private static final long serialVersionUID = 1936046787017831495L;
	private CtlMantemCliente ctlCliente;
	private DtoCliente dtoCliente = new DtoCliente();
	private List<SelectItem> listaEstado;
	private boolean ehAlteracao;
	private String codCliente;

	public ActAtualizaCliente() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);

		verificaSeEhAlteracao();

	}

	public String salvar() throws Exception {
		if (dtoCliente.getCod() != null && dtoCliente.getCod() != 0) {
			ctlCliente.alteraCliente(dtoCliente);
			addMsgInfo("Cliente alterado com sucesso!");
		} else {
			ctlCliente.incluiCliente(dtoCliente);
			addMsgInfo("Cliente cadastrado com sucesso!");
		}

		return "consultaCliente";
	}

	public void limpar(ActionEvent evt) {
		if (dtoCliente != null) {
			dtoCliente.setBairro("");
			dtoCliente.setCelular("");
			dtoCliente.setCep("");
			dtoCliente.setCidade("");
			dtoCliente.setCpfCnpj("");
			dtoCliente.setEndereco("");
			dtoCliente.setEstado("");
			dtoCliente.setIdentidade("");
			dtoCliente.setNome("");
			dtoCliente.setTelefone("");
		} else {
			dtoCliente = new DtoCliente();
		}
	}

	private void verificaSeEhAlteracao() throws Exception {
		ActConsultaCliente actConsulta = (ActConsultaCliente) this
				.getHttpServletRequest().getSession().getAttribute(
						"actConsultaCliente");
		if (actConsulta != null) {
			if (actConsulta.getCodCliente() != null
					&& !actConsulta.getCodCliente().isEmpty()) {
				if (ctlCliente == null)
					ctlCliente = actConsulta.getCtlCliente();

				dtoCliente = ctlCliente.recuperaCliente(new Long(actConsulta
						.getCodCliente()));
				codCliente = dtoCliente.getCod().toString();
				this.retiraObjetoSessao("actConsultaCliente");

				ehAlteracao = true;
			}
		}
	}

	public DtoCliente getDtoCliente() {
		return dtoCliente;
	}

	public void setDtoCliente(DtoCliente dtoCliente) {
		this.dtoCliente = dtoCliente;
	}

	public List<SelectItem> getListaEstado() throws Exception {
		if (listaEstado == null || listaEstado.isEmpty())
			listaEstado = ctlCliente.recuperaListaUf();
		
		return listaEstado;
	}

	public void setListaEstado(List<SelectItem> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public boolean isEhAlteracao() {
		return ehAlteracao;
	}

	public void setEhAlteracao(boolean ehAlteracao) {
		this.ehAlteracao = ehAlteracao;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public void setCtlCliente(CtlMantemCliente ctlCliente) {
		this.ctlCliente = ctlCliente;
	}

}
