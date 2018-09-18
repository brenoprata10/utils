package br.gov.mpog.siest.indicadores.service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IRelatorioService {

    JasperReport carregarRelatorio(String caminhoArquivo);

    JasperReport carregarRelatorio(JasperDesign jasperDesign);

    JasperDesign carregarDesignRelatorio(String caminhoArquivo);

    void setMarginReport(JasperDesign jasperDesign, Integer top, Integer right, Integer bottom, Integer left);

    void exportarParaPDFStream(JasperReport jasperReport, Map<String, Object> parametros, JRDataSource datasource, HttpServletResponse response);

    void exportarParaExcel(JasperReport jasperReport, Map<String, Object> parametros, JRDataSource datasource, HttpServletResponse response);
}
