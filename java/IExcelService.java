package br.gov.mpog.siest.indicadores.service;

import jxl.WorkbookSettings;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

public interface IExcelService {

    /**
     * Método responsável por criar um WorkBook, estrutura inicial necessária para gerar o Excel,
     * seguindo a configuração padrão
     *
     * @param nomeArquivo - Nome do arquivo que será exportado Ex: 'teste.xlsx'
     *
     * @return WritableBook configurado.
     *
     * @author Breno Prata - 10/09/2018
     */
    WritableWorkbook criarWorkBook(String nomeArquivo);

    /**
     * Método responsável por criar um workbook, estrutura inicial necessária para gerar o Excel
     *
     * @param nomeArquivo - Nome do arquivo que será exportado Ex: 'teste.xlsx'
     * @param workbookSettings - Objeto de configuração do workbook.
     *
     * @see http://jexcelapi.sourceforge.net/resources/javadocs/2_6_10/docs/jxl/WorkbookSettings.html
     *
     * @return WritableBook configurado.
     *
     * @author Breno Prata - 10/09/2018
     */
    WritableWorkbook criarWorkBook(String nomeArquivo, WorkbookSettings workbookSettings);

    /**
     * Método responsável por exportar o excel
     *
     * @param workbook - Workbook a ser exportado
     * @param nomeArquivo - Nome do arquivo
     * @param response - Response que receberá o arquivos
     *
     * @author Breno Prata - 10/09/2018
     */
    void exportarWorkBook(WritableWorkbook workbook, String nomeArquivo, HttpServletResponse response);

    /**
     * Método responsável por criar uma planilha, um workbook pode ter várias planilhas
     *
     * @param writableWorkbook - Workbook onde se deve adicionar a planilha
     * @param nomeSheet - Nome da planilha
     *
     * @return WritableSheet configurada
     *
     * @author Breno Prata - 10/09/2018
     */
    WritableSheet criarSheet(WritableWorkbook writableWorkbook, String nomeSheet);

    /**
     * Método responsável por adicionar uma cell na planilha
     *
     * @param writableSheet - Planilha onde será adicionada a cell
     * @param cell - Cell a ser adicionada, estas podem ser: Label, Number, DateTime, Formula, WritableImage
     *
     * @see Label
     * @see Number
     * @see DateTime
     * @see Formula
     * @see WritableImage
     *
     * @author Breno Prata - 10/09/2018
     */
    void adicionarCell(WritableSheet writableSheet, WritableCell cell);

    /**
     * Método responsável por adicionar uma imagem na planilha
     *
     * @param writableSheet - Planilha onde será adicionada a imagem
     * @param writableImage - Imagem a ser adicionada
     *
     * @author Breno Prata - 10/09/2018
     */
    void adicionarImagem(WritableSheet writableSheet, WritableImage writableImage);

    /**
     * Método responsável por criar uma label, utilizando a configuração inicial
     *
     * @param coluna - Coluna onde a label será adicionada
     * @param linha - Linha onde a label será adicionada
     * @param label - Label a ser adicionada
     * @return Label configurada
     *
     * @author Breno Prata - 10/09/2018
     */
    Label criarLabel(int coluna, int linha, String label);

    /**
     * Método responsável por criar uma label, em negrito
     *
     * @param coluna
     * @param linha
     * @param label
     * @return
     *
     * @author Breno Prata - 11/09/2018
     */
    Label criarLabelNegrito(int coluna, int linha, String label);

    /**
     * Método responsável por criar uma label, em negrito
     *
     * @param coluna - Coluna onde a label será adicionada
     * @param linha - Linha onde a label será adicionada
     * @param label - Label a ser adicionada
     * @param writableCellFormat - Objeto de configuração da cell
     * @return
     *
     * @author Breno Prata - 11/09/2018
     */
    Label criarLabelNegrito(int coluna, int linha, String label, WritableCellFormat writableCellFormat);

    /**
     * Método responsável por criar uma label
     *
     * @param coluna - Coluna onde a label será adicionada
     * @param linha - Linha onde a label será adicionada
     * @param label - Label a ser adicionada
     * @param writableCellFormat - Objeto de configuração da cell
     * @return Label configurada
     *
     * @author Breno Prata - 10/09/2018
     */
    Label criarLabel(int coluna, int linha, String label, WritableCellFormat writableCellFormat);

    /**
     * Método responsável por criar um DateTime
     *
     * @param coluna - Coluna onde a data será adicionada
     * @param linha - Linha onde a data será adicionada
     * @param date - Data a ser adicionada
     * @return DateTime configurado
     *
     * @author Breno Prata - 10/09/2018
     */
    DateTime criarDateTime(int coluna, int linha, Date date);

    /**
     * Método responsável por criar um DateTime
     *
     * @param coluna - Coluna onde a data será adicionada
     * @param linha - Linha onde a data será adicionada
     * @param date - Data a ser adicionada
     * @param writableCellFormat - Objeto de configuração da cell
     * @return DateTime configurado
     *
     * @author Breno Prata - 10/09/2018
     */
    DateTime criarDateTime(int coluna, int linha, Date date, WritableCellFormat writableCellFormat);

    /**
     * Método responsável por criar um Number, se utilizando a configuração padrão
     *
     * @param coluna - Coluna onde o número será adicionado
     * @param linha - Linha onde o número será adicionado
     * @param numero - Número a ser adicionado
     * @return Number configurado
     *
     * @author Breno Prata - 10/09/2018
     */
    Number criarNumber(int coluna, int linha, Double numero);

    /**
     * Método responsável por criar um Number
     *
     * @param coluna - Coluna onde o número será adicionado
     * @param linha - Linha onde o número será adicionado
     * @param numero - Número a ser adicionado
     * @param formato - Formatação do número,
     *                Ex: Número = 3,142123213123
     *                    Formato = "#.###"
     *                    Output = "3,142"
     * @return Number formatado
     *
     * @author Breno Prata - 10/09/2018
     */
    Number criarNumber(int coluna, int linha, Double numero, String formato);

    /**
     * Método responsável por criar um Number
     *
     * @param coluna - Coluna onde o número será adicionado
     * @param linha - Linha onde o número será adicionado
     * @param numero - Número a ser adicionado
     * @param writableCellFormat - Objeto de configuração da cell
     * @return Number formatado
     *
     * @author Breno Prata - 10/09/2018
     */
    Number criarNumber(int coluna, int linha, Double numero, WritableCellFormat writableCellFormat);

    /**
     * Método responsável por criar uma Formula
     *
     * @param coluna - Coluna onde a fórmula será adicionada
     * @param linha - Linha onde a fórmula será adicionada
     * @param formula - Formula a ser adicionada
     *                Ex: Formula = "E1+E2"
     *                    Output = Os números da posição E1 e E2 serão somados e o resultado será apresentado
     * @return Formula formatada
     *
     * @author Breno Prata - 10/09/2018
     */
    Formula criarFormula(int coluna, int linha, String formula);

    /**
     * Método responsável por criar uma Formula
     *
     * @param coluna - Coluna onde a fórmula será adicionada
     * @param linha - Linha onde a fórmula será adicionada
     * @param formula - Formula a ser adicionada
     *                Ex: Formula = "E1+E2"
     *                    Output = Os números da posição E1 e E2 serão somados e o resultado será apresentado
     * @param writableCellFormat - Objeto de configuração
     * @return Formula formatada
     *
     * @author Breno Prata - 10/09/2018
     */
    Formula criarFormula(int coluna, int linha, String formula, WritableCellFormat writableCellFormat);

    /**
     * Método responsável por criar uma imagem
     *
     * @param coluna - Coluna onde a imagem será adicionada
     * @param linha - Linha onde a imagem será adicionada
     * @param largura - Largura da imagem
     * @param altura - Altura da imagem
     * @param file - Arquivo contendo a imagem
     * @return WritableImage configurada
     *
     * @author Breno Prata - 10/09/2018
     */
    WritableImage criarImagem(int coluna, int linha, int largura, int altura, File file);

    /**
     * Método responsável por autoAjustar o tamanho da cell de acordo com o tamanho de seu conteúdo
     *
     * @param writableSheet - Planilha a ser ajustada
     * @param coluna - Coluna a ser ajustada
     *
     * @author Breno Prata - 11/09/2018
     */
    void autoAjustarColuna(WritableSheet writableSheet, int coluna);
}
