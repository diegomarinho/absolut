package br.absolut.util.filter.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class ReportUtil {

	public static final String FORMAT_HTML = "HTML";
	public static final String FORMAT_PDF = "PDF";
	public static final String FORMAT_XLS = "XLS";

	private static byte[] exportReportToBytes(JasperPrint jasperPrint,
			JRExporter exporter) throws JRException {
		byte[] output;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();

		output = baos.toByteArray();
		return output;
	}

	private static byte[] gerarArquivo(String nomeJasper,
			List<? extends Object> listObjetos,
			Map<String, Object> mapParametros, String outputFormat)
			throws RelatorioVazioException, JRException {

		Locale locale = Locale.getDefault();
		locale = new Locale("pt", "BR");
		Locale.setDefault(locale);

		// Localizando o caminho dos relatÛrios.
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String caminhoReal = request.getSession().getServletContext()
				.getRealPath("")
				+ File.separatorChar;

		Map<String, Object> parameters = new HashMap<String, Object>();

		// Especifica o caminho do SubReport, o mesmo dever· ficar no mesmo
		// diretÛrio do Report principal.
		parameters.put("subCaminho", caminhoReal + "report"
				+ File.separatorChar);

		// Especifica o caminho da imagem, a imagem deve ficar dentro de uma
		// pasta chamada img dentro do diretÛrio onde est„o os relatÛrios.
		parameters.put("imgCaminho", caminhoReal + "common"
				+ File.separatorChar + "image" + File.separatorChar);

		// Preenchendo uma lista de parÍmetros utilizados para geraÁ„o do
		// relatÛrio.
		if (mapParametros != null) {
			parameters.putAll(mapParametros);
		}

		JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoReal
				+ "report" + File.separatorChar + nomeJasper, parameters,
				new JRBeanCollectionDataSource(listObjetos));
		System.out.println("<<<<< QUANTIDADE DE PAGS >>>>> :::: "
				+ jasperPrint.getPages().size());

		if ((jasperPrint.getPages().size() == 0) || (listObjetos.size() < 1)) {
			throw new RelatorioVazioException();
		} else {
			if (outputFormat.equals(FORMAT_PDF)) {
				return JasperExportManager.exportReportToPdf(jasperPrint);
			} else if (outputFormat.equals(FORMAT_XLS)) {
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setParameter(
						JRXlsAbstractExporterParameter.SHEET_NAMES,
						new String[] { "RelatÛrio" });
				return exportReportToBytes(jasperPrint, exporter);
			} else if (outputFormat.equals(FORMAT_HTML)) {
				JRHtmlExporter exporter = new JRHtmlExporter();
				exporter.setParameter(
						JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
						Boolean.FALSE);
				exporter.setParameter(
						JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						Boolean.FALSE);
				return exportReportToBytes(jasperPrint, exporter);
			} else {
				throw new JRException("erro.gerar.relatorio");
			}
		}

	}

	/**
	 * MÈtodo respons·vel por gerar o relatÛrio.
	 * 
	 * @param String
	 *            contendo o nome do arquivo.
	 * @param List
	 *            contendo a lista de objetos que ir„o popular o relatÛrio.
	 * @throws RelatorioVazioException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void gerarRelatorio(String nomeArquivo,
			List<? extends Object> listObjetos) throws RelatorioVazioException,
			JRException, IOException {
		gerarRelatorio(nomeArquivo, listObjetos, null, FORMAT_PDF);
	}

	/**
	 * MÈtodo respons·vel por gerar o relatÛrio.
	 * 
	 * @param String
	 *            contendo o nome do arquivo.
	 * @param List
	 *            contendo a lista de objetos que ir„o popular o relatÛrio.
	 * @param Map
	 *            contendo os par‚metros que foram utilizados para gerar o
	 *            relatÛrio.
	 * @throws RelatorioVazioException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void gerarRelatorio(String nomeArquivo,
			List<? extends Object> listObjetos,
			Map<String, Object> mapParametros) throws RelatorioVazioException,
			JRException, IOException {
		gerarRelatorio(nomeArquivo, listObjetos, mapParametros, FORMAT_PDF);
	}

	/**
	 * MÈtodo respons·vel por gerar o relatÛrio.
	 * 
	 * @param String
	 *            contendo o nome do arquivo.
	 * @param List
	 *            contendo a lista de objetos que ir„o popular o relatÛrio.
	 * @param Map
	 *            contendo os par‚metros que foram utilizados para gerar o
	 *            relatÛrio.
	 * @param String
	 *            com o formato de saÌda do relatÛrio.
	 * @throws RelatorioVazioException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void gerarRelatorio(String nomeArquivo,
			List<? extends Object> listObjetos,
			Map<String, Object> mapParametros, String outputFormat)
			throws RelatorioVazioException, JRException, IOException {

		String nomeJasper = nomeArquivo + ".jasper";

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		byte[] output = gerarArquivo(nomeJasper, listObjetos, mapParametros,
				outputFormat);

		response.setHeader("Cache-Control", "max-age=60");
		if (outputFormat.equals(FORMAT_HTML)) {
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ nomeArquivo + ".html\"");
		} else if (outputFormat.equals(FORMAT_PDF)) {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ nomeArquivo + ".pdf\"");
		} else if (outputFormat.equals(FORMAT_XLS)) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ nomeArquivo + ".xls\"");
		}
		OutputStream out = response.getOutputStream();
		InputStream inputStream = new ByteArrayInputStream(output);

		byte[] buf = new byte[output.length];
		int count;
		while ((count = inputStream.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		inputStream.close();
		out.flush();
		out.close();

		FacesContext.getCurrentInstance().responseComplete();

	}

	/**
	 * MÈtodo respons·vel por gerar o relatÛrio.
	 * 
	 * @param String
	 *            contendo o nome do arquivo.
	 * @param List
	 *            contendo a lista de objetos que ir„o popular o relatÛrio.
	 * @param String
	 *            com o formato de saÌda do relatÛrio.
	 * @throws RelatorioVazioException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void gerarRelatorio(String nomeArquivo,
			List<? extends Object> listObjetos, String outputFormat)
			throws RelatorioVazioException, JRException, IOException {
		gerarRelatorio(nomeArquivo, listObjetos, null, outputFormat);
	}

	private ReportUtil() {
		super();
	}
}
