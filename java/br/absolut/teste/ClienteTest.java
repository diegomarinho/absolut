package br.absolut.teste;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.absolut.negocio.cliente.SvnCliente;

public class ClienteTest extends TestCase {
	private SvnCliente svn;

	public ClienteTest() {
	}

	public void testCliente() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		svn = (SvnCliente) ctx.getBean("clienteSvn");
		
		svn.exclui(new Long(9));

	}
}
