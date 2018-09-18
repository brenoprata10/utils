package br.gov.mpog.siest.indicadores.service.impl;

import br.gov.mpog.siest.indicadores.service.ICorService;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.util.List;
import java.util.Random;

@Service
public class CorService implements ICorService {

    /**
     * Método responsável por recuperar uma cor randômica
     *
     * @param listaCoresNaoPermitidas - Array que conterá as cores não permitidas
     * @param valorMaximoPermitido
     * @param valorMinimoPermitido
     * @return
     *
     * @author Breno Prata - 03/09/2018
     */
    public String gerarCorRandomica(List<String> listaCoresNaoPermitidas, Integer valorMaximoPermitido, Integer valorMinimoPermitido) {

        String cor;
        Random random = new Random();

        int corRed;
        int corGreen;
        int corBlue;

        do {

            corRed = random.nextInt(valorMaximoPermitido);
            corGreen = random.nextInt(valorMaximoPermitido);
            corBlue = random.nextInt(valorMaximoPermitido);

            cor = "rgb("
                .concat(String.valueOf(corRed).concat(", ")
                .concat(String.valueOf(corGreen)).concat(", ")
                .concat(String.valueOf(corBlue))).concat(")");

        } while(!isCorDentroLimite(corRed, corGreen, corBlue, valorMaximoPermitido, valorMinimoPermitido) ||
            isCorPresenteLista(listaCoresNaoPermitidas, cor));

        return cor;
    }

    /**
     * Método responsável por verificar se a cor está presente na lista
     *
     * @param listaCores
     * @param cor
     * @return
     *
     * @author Breno Prata - 03/09/2018
     */
    private Boolean isCorPresenteLista(List<String> listaCores, String cor) {

        return listaCores.stream().anyMatch(corNaoPermitida -> corNaoPermitida.equalsIgnoreCase(cor));
    }

    /**
     * Método responsável por verificar se uma cor está dentro do limite solicitado quanto ao RGB
     *
     * @param cor
     * @param valorMaximo
     * @param valorMinimo
     * @return
     *
     * @author Breno Prata - 03/09/2018
     */
    private Boolean isCorDentroLimite(int corRed, int corGreen, int corBlue, Integer valorMaximo, Integer valorMinimo) {

        if (corRed > valorMaximo || corRed < valorMinimo) {

            return false;
        }

        if (corGreen > valorMaximo || corGreen < valorMinimo) {

            return false;
        }

        return !(corBlue > valorMaximo || corBlue < valorMinimo);
    }
}
