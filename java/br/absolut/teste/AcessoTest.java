package br.absolut.teste;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.absolut.negocio.acesso.SvnAcesso;

public class AcessoTest extends TestCase {
	private SvnAcesso svn;
	
	public AcessoTest() {}
	
	public void testAcesso() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		svn = (SvnAcesso) ctx.getBean("acessoSvn");
		
		svn.exclui(new Long(1));

	}
}
