package br.absolut.teste;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.absolut.negocio.fornecedor.SvnFornecedor;

public class FornecedorTest extends TestCase {
	private SvnFornecedor svn;

	public FornecedorTest() {
	}

	public void testFornecedor() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		svn = (SvnFornecedor) ctx.getBean("fornecedorSvn");
		
		svn.exclui(new Long(2));
		
		
		
	}
}
