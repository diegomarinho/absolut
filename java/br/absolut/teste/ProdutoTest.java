package br.absolut.teste;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.absolut.negocio.produto.SvnProduto;
import br.absolut.persistencia.produto.Produto;

public class ProdutoTest extends TestCase {
	private SvnProduto svnProduto;
	public ProdutoTest() {
	}

	public void testProduto() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		svnProduto = (SvnProduto) ctx.getBean("produtoSvn");
		
		svnProduto.recuperaTodos();
		Produto produto = svnProduto.recuperaPorId(new Long(4));
		
		System.out.println(produto.getCod().toString());
		System.out.println(produto.getDescricao());
		System.out.println(produto.getTipoProduto().getCod().toString());
		System.out.println(produto.getTipoProduto().getDescricao());
		
	}
}
