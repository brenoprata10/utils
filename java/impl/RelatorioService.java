package br.gov.mpog.siest.indicadores.service.impl;

import br.gov.mpog.siest.indicadores.exception.IndicadoresException;
import br.gov.mpog.siest.indicadores.service.IRelatorioService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class RelatorioService implements IRelatorioService {

    private static final String ERRO_RELATORIO = "Não foi possível carregar o relatório!";
    private static final String ERRO_RELATORIO_LOG = "Erro ao gerar relatório: {}";

    /**
     * Método responsável por exportar o PDF para um stream
     *
     * @param jasperReport
     * @param parametros
     * @param datasource
     * @param response
     *
     * @author Breno Prata - 20/08/2018
     */
    public void exportarParaPDFStream(JasperReport jasperReport, Map<String, Object> parametros, JRDataSource datasource, HttpServletResponse response) {

        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource);

            if (Objects.nonNull(response) && Objects.nonNull(response.getOutputStream())) {

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            }

        } catch (JRException | IOException exception) {

            log.error(ERRO_RELATORIO_LOG, exception);
            throw new IndicadoresException("Não foi possível exportar o relatório!");
        }
    }

    /**
     * Método responsável por exportar o arquivo para um excel
     *
     * @param jasperReport
     * @param parametros
     * @param datasource
     * @param response
     *
     * @author Breno Prata - 20/08/2018
     */
    public void exportarParaExcel(JasperReport jasperReport, Map<String, Object> parametros, JRDataSource datasource, HttpServletResponse response) {

        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource);

            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setCellHidden(true);
            configuration.setShrinkToFit(true);
            configuration.setIgnorePageMargins(true);
            configuration.setFontSizeFixEnabled(true);
            configuration.setRemoveEmptySpaceBetweenRows(true);
            configuration.setRemoveEmptySpaceBetweenColumns(true);
            exporter.setConfiguration(configuration);

            if (Objects.nonNull(response.getOutputStream())) {
                exporter.exportReport();
            }

        } catch (JRException | IOException exception) {

            log.error(ERRO_RELATORIO_LOG, exception);
            throw new IndicadoresException("Não foi possível exportar o relatório!");
        }
    }

    /**
     * Método responsável por carregar o relatório
     *
     * @param caminhoArquivo
     * @return
     *
     * @author Breno Prata - 20/08/2018
     */
    public JasperReport carregarRelatorio(String caminhoArquivo) {

        try {

            InputStream inputStream = this.getClass().getResourceAsStream(caminhoArquivo);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

            return JasperCompileManager.compileReport(jasperDesign);

        } catch (JRException exception) {

            log.error(ERRO_RELATORIO_LOG, exception);
            throw new IndicadoresException(ERRO_RELATORIO);
        }
    }

    /**
     * Método responsável por carregar o relatório
     *
     * @param caminhoArquivo
     * @return
     *
     * @author Breno Prata - 20/08/2018
     */
    public JasperReport carregarRelatorio(JasperDesign jasperDesign) {

        try {

            return JasperCompileManager.compileReport(jasperDesign);

        } catch (JRException exception) {

            log.error(ERRO_RELATORIO_LOG, exception);
            throw new IndicadoresException(ERRO_RELATORIO);
        }
    }

    /**
     * Método responsável por carregar o relatório
     *
     * @param caminhoArquivo
     * @return
     *
     * @author Breno Prata - 20/08/2018
     */
    public JasperDesign carregarDesignRelatorio(String caminhoArquivo) {

        try {

            InputStream inputStream = this.getClass().getResourceAsStream(caminhoArquivo);

            return JRXmlLoader.load(inputStream);

        } catch (JRException exception) {

            log.error(ERRO_RELATORIO_LOG, exception);
            throw new IndicadoresException(ERRO_RELATORIO);
        }
    }

    /**
     * Método responsável por settar as margens do relatório
     *
     * @param jasperDesign
     *
     * @author Breno Prata - 04/09/2018
     */
    public void setMarginReport(JasperDesign jasperDesign, Integer top, Integer right, Integer bottom, Integer left) {

        jasperDesign.setRightMargin(right);
        jasperDesign.setLeftMargin(left);
        jasperDesign.setTopMargin(top);
        jasperDesign.setBottomMargin(bottom);
    }

}
