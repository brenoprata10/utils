package br.gov.mpog.siest.indicadores.service;

import java.util.List;

public interface ICorService {

    String gerarCorRandomica(List<String> listaCoresNaoPermitidas, Integer valorMaximoPermitido, Integer valorMinimoPermitido);
}
