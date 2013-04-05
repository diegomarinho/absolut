package br.absolut.util.filter.report;

import java.text.MessageFormat;

/**
 * Exce��o lan�ada quando um � gerado um relat�rio vazio.
 * 
 * @author Fabr�cio Fachini
 * @since 07/07/2009
 */
public class RelatorioVazioException extends Exception {

	private static final long serialVersionUID = 6237137021840305983L;

	/** Mensagem de erro padrão. */
	private static String MENSAGEM = "relatorio.vazio";

	/**
	 * Construtor para UsuarioExistenteException
	 * @param nome do usuário já existente
	 */
	public RelatorioVazioException() {
		super(MessageFormat.format(MENSAGEM, ""));
	}

}