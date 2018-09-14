package br.gov.mpog.siest.indicadores.service.impl;

import br.gov.mpog.siest.indicadores.exception.IndicadoresException;
import br.gov.mpog.siest.indicadores.service.IExcelService;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Service
public class ExcelService implements IExcelService {

    @Override
    public WritableWorkbook criarWorkBook(String nomeArquivo) {

        WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setLocale(new Locale("pt", "BR"));

        return criarWorkBook(nomeArquivo, workbookSettings);
    }

    @Override
    public WritableWorkbook criarWorkBook(String nomeArquivo, WorkbookSettings workbookSettings) {

        try {

            return Workbook.createWorkbook(new File(nomeArquivo), workbookSettings);

        } catch (IOException exception) {

            log.error("{}", exception);
            throw new IndicadoresException("Não foi possível criar o WorkBook");
        }
    }

    @Override
    public void exportarWorkBook(WritableWorkbook workbook, String nomeArquivo, HttpServletResponse response) {

        try {
            workbook.write();
            workbook.close();

            if (Objects.nonNull(response) && Objects.nonNull(response.getOutputStream())) {

                byte[] byteArray = Files.readAllBytes(new File(nomeArquivo).toPath());

                response.setContentLength(byteArray.length);

                OutputStream os = response.getOutputStream();

                os.write(byteArray, 0, byteArray.length);
            }

        } catch (WriteException | IOException exception) {

            log.error("{}", exception);
            throw new IndicadoresException("Não foi possível exportar o arquivo");
        }
    }

    @Override
    public WritableSheet criarSheet(WritableWorkbook writableWorkbook, String nomeSheet) {

        return writableWorkbook.createSheet(nomeSheet, writableWorkbook.getSheets().length);
    }

    @Override
    public void adicionarCell(WritableSheet writableSheet, WritableCell cell) {

        try {

            writableSheet.addCell(cell);

        } catch (WriteException exception) {

            log.error("{}", exception);
            throw new IndicadoresException("Não foi possível adicionar a Cell!");
        }
    }

    @Override
    public void adicionarImagem(WritableSheet writableSheet, WritableImage writableImage) {

        writableSheet.addImage(writableImage);
    }

    @Override
    public Label criarLabel(int coluna, int linha, String label) {

        return criarLabel(coluna, linha, label, getPadraoCell());
    }

    @Override
    public Label criarLabel(int coluna, int linha, String label, WritableCellFormat writableCellFormat) {

        return new Label(coluna, linha, label, writableCellFormat);
    }

    @Override
    public Label criarLabelNegrito(int coluna, int linha, String label) {

        return new Label(coluna, linha, label, new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD)));
    }

    @Override
    public Label criarLabelNegrito(int coluna, int linha, String label, WritableCellFormat writableCellFormat) {

        writableCellFormat.setFont(new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD));

        Label labelJXL = new Label(coluna, linha, label, new WritableCellFormat(writableCellFormat));

        writableCellFormat.setFont(new WritableFont(WritableFont.ARIAL, 10));

        return labelJXL;
    }

    @Override
    public DateTime criarDateTime(int coluna, int linha, Date date) {

        return criarDateTime(coluna, linha, date, getPadraoCell());
    }

    @Override
    public DateTime criarDateTime(int coluna, int linha, Date date, WritableCellFormat writableCellFormat) {

        return new DateTime(coluna, linha, date, writableCellFormat);
    }

    @Override
    public Number criarNumber(int coluna, int linha, Double numero) {

        return criarNumber(coluna, linha, numero, getPadraoCell());
    }

    @Override
    public Number criarNumber(int coluna, int linha, Double numero, String formato) {

        NumberFormat numberFormat = new NumberFormat(formato);

        return criarNumber(coluna, linha, numero, new WritableCellFormat(numberFormat));
    }

    @Override
    public Number criarNumber(int coluna, int linha, Double numero, WritableCellFormat writableCellFormat) {

        return new Number(coluna, linha, numero, writableCellFormat);
    }

    @Override
    public Formula criarFormula(int coluna, int linha, String formula) {

        return criarFormula(coluna, linha, formula, getPadraoCell());
    }

    @Override
    public Formula criarFormula(int coluna, int linha, String formula, WritableCellFormat writableCellFormat) {

        return new Formula(coluna, linha, formula, writableCellFormat);
    }

    @Override
    public WritableImage criarImagem(int coluna, int linha, int largura, int altura, File file) {

        return new WritableImage(coluna, linha, largura, altura, file);
    }

    @Override
    public void autoAjustarColuna(WritableSheet writableSheet, int coluna) {

        CellView cellView = writableSheet.getColumnView(coluna);
        cellView.setAutosize(true);
        writableSheet.setColumnView(coluna, cellView);
    }

    private WritableCellFormat getPadraoCell() {

        try {

            WritableFont writableFont = new WritableFont(WritableFont.ARIAL,
                10);
            WritableCellFormat writableCellFormat = new WritableCellFormat(writableFont);
            writableCellFormat.setWrap(true);

            return writableCellFormat;

        } catch (WriteException exception) {

            log.debug("{}", exception);
            throw new IndicadoresException("Não foi possível criar a label!");
        }
    }
}
