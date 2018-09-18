package br.gov.mpog.siest.indicadores.service.impl;

import br.gov.mpog.siest.indicadores.exception.IndicadoresException;
import br.gov.mpog.siest.indicadores.service.IRelatorioService;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioServiceTest {

    @InjectMocks
    private RelatorioService relatorioService;

    @Mock
    private JasperReport jasperReport;

    @Test(expected = IndicadoresException.class)
    public void carregarRelatorio() {

        relatorioService.carregarRelatorio("/teste");
    }

    @Test(expected = IndicadoresException.class)
    public void carregarRelatorio1() {

        relatorioService.carregarRelatorio(new JasperDesign());
    }

    @Test(expected = IndicadoresException.class)
    public void carregarDesignRelatorio() {

        relatorioService.carregarDesignRelatorio("/teste");
    }
}
