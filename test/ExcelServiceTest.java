package br.gov.mpog.siest.indicadores.service.impl;

import br.gov.mpog.siest.indicadores.exception.IndicadoresException;
import jxl.Range;
import jxl.Sheet;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class ExcelServiceTest {

    @InjectMocks
    private ExcelService excelService;

    @Test(expected = IndicadoresException.class)
    public void criarWorkBookComSettings() {
        WorkbookSettings workbookSettings = new WorkbookSettings();
        String nomeArquivo = "";

        excelService.criarWorkBook(nomeArquivo, workbookSettings);
        Assert.fail();
    }

    @Test
    public void exportarWorkBook() {
        String nomeArquivo = "analise-financeira.xls";
        excelService.exportarWorkBook(mockWritableWorkbook(), nomeArquivo, mockResponse());
    }

    @Test(expected = IndicadoresException.class)
    public void exportarWorkBookCausarException() {
        String nomeArquivo = "";
        excelService.exportarWorkBook(mockWritableWorkbook(), nomeArquivo, mockResponse());
    }

    private WritableWorkbook mockWritableWorkbook() {
        WritableWorkbook writableWorkbook = new WritableWorkbook() {
            @Override
            public WritableSheet[] getSheets() {
                return new WritableSheet[0];
            }

            @Override
            public String[] getSheetNames() {
                return new String[0];
            }

            @Override
            public WritableSheet getSheet(int i) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public WritableSheet getSheet(String s) {
                return null;
            }

            @Override
            public WritableCell getWritableCell(String s) {
                return null;
            }

            @Override
            public int getNumberOfSheets() {
                return 0;
            }

            @Override
            public void close() throws IOException, WriteException {

            }

            @Override
            public WritableSheet createSheet(String s, int i) {
                return null;
            }

            @Override
            public WritableSheet importSheet(String s, int i, Sheet sheet) {
                return null;
            }

            @Override
            public void copySheet(int i, String s, int i1) {

            }

            @Override
            public void copySheet(String s, String s1, int i) {

            }

            @Override
            public void removeSheet(int i) {

            }

            @Override
            public WritableSheet moveSheet(int i, int i1) {
                return null;
            }

            @Override
            public void write() throws IOException {

            }

            @Override
            public void setProtected(boolean b) {

            }

            @Override
            public void setColourRGB(Colour colour, int i, int i1, int i2) {

            }

            @Override
            public WritableCell findCellByName(String s) {
                return null;
            }

            @Override
            public Range[] findByName(String s) {
                return new Range[0];
            }

            @Override
            public String[] getRangeNames() {
                return new String[0];
            }

            @Override
            public void removeRangeName(String s) {

            }

            @Override
            public void addNameArea(String s, WritableSheet writableSheet, int i, int i1, int i2, int i3) {

            }

            @Override
            public void setOutputFile(File file) throws IOException {

            }
        };
        return writableWorkbook;
    }

    private HttpServletResponse mockResponse() {
        HttpServletResponse response = new HttpServletResponse() {
            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public boolean containsHeader(String s) {
                return false;
            }

            @Override
            public String encodeURL(String s) {
                return null;
            }

            @Override
            public String encodeRedirectURL(String s) {
                return null;
            }

            @Override
            public String encodeUrl(String s) {
                return null;
            }

            @Override
            public String encodeRedirectUrl(String s) {
                return null;
            }

            @Override
            public void sendError(int i, String s) throws IOException {

            }

            @Override
            public void sendError(int i) throws IOException {

            }

            @Override
            public void sendRedirect(String s) throws IOException {

            }

            @Override
            public void setDateHeader(String s, long l) {

            }

            @Override
            public void addDateHeader(String s, long l) {

            }

            @Override
            public void setHeader(String s, String s1) {

            }

            @Override
            public void addHeader(String s, String s1) {

            }

            @Override
            public void setIntHeader(String s, int i) {

            }

            @Override
            public void addIntHeader(String s, int i) {

            }

            @Override
            public void setStatus(int i) {

            }

            @Override
            public void setStatus(int i, String s) {

            }

            @Override
            public int getStatus() {
                return 0;
            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new ServletOutputStream() {
                    @Override
                    public boolean isReady() {
                        return true;
                    }

                    @Override
                    public void setWriteListener(WriteListener writeListener) {

                    }

                    @Override
                    public void write(int b) throws IOException {

                    }
                };
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return null;
            }

            @Override
            public void setCharacterEncoding(String s) {

            }

            @Override
            public void setContentLength(int i) {

            }

            @Override
            public void setContentLengthLong(long l) {

            }

            @Override
            public void setContentType(String s) {

            }

            @Override
            public void setBufferSize(int i) {

            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public void flushBuffer() throws IOException {

            }

            @Override
            public void resetBuffer() {

            }

            @Override
            public boolean isCommitted() {
                return false;
            }

            @Override
            public void reset() {

            }

            @Override
            public void setLocale(Locale locale) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }
        };
        return response;
    }
}
