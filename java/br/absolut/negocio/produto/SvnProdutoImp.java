package br.absolut.negocio.produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.produto.DaoProduto;
import br.absolut.persistencia.produto.Produto;

public class SvnProdutoImp implements SvnProduto{
	private DaoProduto daoProduto;

	public SvnProdutoImp() {
	}

	public Produto altera(Produto produto) {
		return this.daoProduto.update(produto);
	}

	public List<Produto> consultaPorParametros(DtoParametroConsulta parametros) {
		List<Object[]> listaParam = new ArrayList<Object[]>();
		Object[] param;

		if (parametros != null) {
			if (parametros.getCodBarra() != null
					&& !parametros.getCodBarra().isEmpty()) {
				param = new Object[2];
				param[0] = "codBarra";
				param[1] = parametros.getCodBarra();

				listaParam.add(param);
			}
			if (parametros.getCodTipoProduto() != null
					&& parametros.getCodTipoProduto() != 0) {
				param = new Object[2];
				param[0] = "tipoProduto.cod";
				param[1] = parametros.getCodTipoProduto();

				listaParam.add(param);
			}
			if (parametros.getDescricao() != null
					&& !parametros.getDescricao().isEmpty()) {
				param = new Object[2];
				param[0] = "descricao";
				param[1] = parametros.getDescricao() + "%";

				listaParam.add(param);
			}
			if (parametros.getFabricante() != null
					&& !parametros.getFabricante().isEmpty()) {
				param = new Object[2];
				param[0] = "fabricante";
				param[1] = parametros.getFabricante() + "%";

				listaParam.add(param);
			}
		}

		return this.daoProduto.recuperaProdutosPorParametro(listaParam);
	}
	
	public List<Produto> recuperaProdutosComBaixoEstoque() {
		return this.daoProduto.recuperaProdutosComBaixoEstoque();
	}

	public void exclui(Long codigo) {
		Produto produto = this.daoProduto.findById(codigo);

		if (produto != null)
			this.daoProduto.delete(produto);

	}

	public void excluiLogicamente(Long codigo) {
		Produto produto = this.daoProduto.findById(codigo);

		if (produto != null) {
			produto.setDtExclusao(new Date());
			this.daoProduto.update(produto);
		}

	}

	public Produto inclui(Produto produto) {
		return this.daoProduto.save(produto);
	}

	public Produto recuperaPorId(Long codigo) {
		return this.daoProduto.findById(codigo);
	}

	public List<Produto> recuperaTodos() {
		return this.daoProduto.listAll();
	}

	public void setDaoProduto(DaoProduto daoProduto) {
		this.daoProduto = daoProduto;
	}
}
