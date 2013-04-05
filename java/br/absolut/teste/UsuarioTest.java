package br.absolut.teste;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.absolut.negocio.usuario.SvnUsuario;
import br.absolut.persistencia.usuario.Usuario;

public class UsuarioTest extends TestCase {

	public void testUsuario() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		SvnUsuario svn = (SvnUsuario) ctx.getBean("usuarioSvn");
		String login = "igor.araujo";
		String senha = "271488";
		
		Usuario usuario = svn.verificaLoginSenha(login, senha);
		
		System.out.println(usuario != null ? usuario.getNome(): "");

	}
}
