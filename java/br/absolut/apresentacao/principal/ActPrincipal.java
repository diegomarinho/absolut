package br.absolut.apresentacao.principal;

import java.util.List;

import br.absolut.apresentacao.DtoUsuario;
import br.absolut.apresentacao.mantemproduto.DtoProduto;
import br.absolut.apresentacao.mantemusuario.CtlMantemUsuario;
import br.absolut.apresentacao.mantemusuario.DtoAlteraSenha;
import br.absolut.apresentacao.seguranca.Seguranca;

public class ActPrincipal extends Seguranca {
	private static final long serialVersionUID = -8794454523987280667L;
	private CtlPrincipal ctlPrincipal;
	private CtlMantemUsuario ctlUsuario;
	private List<DtoHistVenda> listaHistVenda;
	private List<DtoProduto> listaProduto;
	private DtoAlteraSenha dtoAlteraSenha = new DtoAlteraSenha();
	private int scrollerPage;

	public ActPrincipal() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);
	}

	public String alteraSenha() {
		DtoUsuario dtoUsuario = ctlUsuario.verificaLoginSenha(this
				.getDtoUsuario().getLogin(), this.dtoAlteraSenha
				.getSenhaAtual());
		if (dtoUsuario != null) {
			if (this.dtoAlteraSenha.getNovaSenha().equals(
					this.dtoAlteraSenha.getReNovaSenha())) {
				try {
					ctlUsuario.alteraSenhaUsuario(dtoUsuario.getCod(),
							this.dtoAlteraSenha.getNovaSenha());
					this.addMsgInfo("Senha alterada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
					this.addMsgErro("Ocorreu um erro ao alterar a senha. Tente novamente.");
				}
			} else {
				this.addMsgErro("Os campos 'Nova senha' e 'Repita nova senha' devem ser iguais");
				return "";
			}
		} else {
			this.addMsgErro("Senha atual inválida!");
			return "";
		}
		return "";
	}

	public DtoAlteraSenha getDtoAlteraSenha() {
		return dtoAlteraSenha;
	}

	public List<DtoHistVenda> getListaHistVenda() {
		listaHistVenda = ctlPrincipal.recuperaListaHistoricoVenda();
		return listaHistVenda;
	}

	public List<DtoProduto> getListaProduto() {
		listaProduto = ctlPrincipal.recuperaListaProdutoBaixoEstoque();
		return listaProduto;
	}

	public int getScrollerPage() {
		return scrollerPage;
	}

	public void setCtlPrincipal(CtlPrincipal ctlPrincipal) {
		this.ctlPrincipal = ctlPrincipal;
	}

	public void setCtlUsuario(CtlMantemUsuario ctlUsuario) {
		this.ctlUsuario = ctlUsuario;
	}

	public void setDtoAlteraSenha(DtoAlteraSenha dtoAlteraSenha) {
		this.dtoAlteraSenha = dtoAlteraSenha;
	}

	public void setListaHistVenda(List<DtoHistVenda> listaHistVenda) {
		this.listaHistVenda = listaHistVenda;
	}

	public void setListaProduto(List<DtoProduto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}
}
