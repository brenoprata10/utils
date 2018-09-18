import {Component, OnInit, Renderer2} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({

    selector: 'app-calculadora',

    templateUrl: './calculadora.component.html',

    styleUrls: ['./calculadora.component.scss']
})
export class CalculadoraComponent implements OnInit {

    ACENTOS = 'áâãàéêèíìóôõòú';

    CONDICIONAL_SE = 'SE\\({1,2}[\\w\\d\\s><=!+-/*)]*\\)\\s';
    CONDICIONAL_ENTAO = 'ENTAO\\({1,2}[\\w\\d\\s><=!+-/*)]*\\)\\s';
    CONDICIONAL_SENAO = 'SENAO\\({1,2}[\\w\\d><=!+-/*)]*\\)';
    CONDICIONAL = `${this.CONDICIONAL_SE}${this.CONDICIONAL_ENTAO}${this.CONDICIONAL_SENAO}`;

    CONSTANTE = `&[\\w,.ç/${this.ACENTOS}]*`;
    VARIAVEL = `\\$[\\w,.ç/${this.ACENTOS}]*`;
    RUBRICA = `#\\w{1}_\\d*_\\w{2}[-+/*]?\\d?_\\w{4}[-+/*]?\\d?_\\d*`;

    formGroup: FormGroup;

    valorInput: string;

    countId: number;

    constructor(private fb: FormBuilder,
                private renderer: Renderer2) {

    }

    /**
     * Método responsável por inicializar o componente
     *
     * @author Breno Prata - 28/06/2018
     */
    ngOnInit() {

        this.inicializarVariaveis();

        this.construirForm();
    }

    /**
     * Método responsável por inicializar os campos da calculadora
     *
     * @author Breno Prata - 28/06/2018
     */
    inicializarVariaveis() {

        this.countId = 0;
    }

    /**
     * Método responsável por construir o Formulário
     *
     * @author Breno Prata - 02/06/2018
     */
    construirForm() {

        this.formGroup = this.fb.group({
            calculadora: new FormArray([])
        });
    }

    /**
     * Método responsável por adicionar uma nova operação na calculadora
     *
     * @param operacao
     *
     * @author Breno Prata - 28/06/2018
     */
    adicionarOperacao(operacao) {

        if (!operacao) {
            return;
        }

        const formArray = this.formArrayCalculadora;

        formArray.push(this.getFormGroupOperacao(operacao));

        this.formGroup.setControl('calculadora', formArray);

        this.validarFormula();
    }

    /**
     * Método responsável por adicionar uma série de operações
     *
     * @author Breno Prata - 04/06/2018
     */
    adicionarOperacoes(operacoes) {

        for (const operacao of operacoes.split(';')) {
            this.adicionarOperacao(operacao);
        }
    }

    /**
     * Método responsável por construir e retornar um formGroup com a operação desejada
     *
     * @param operacao
     * @returns {FormGroup}
     *
     * @author Breno Prata - 28/06/2018
     */
    getFormGroupOperacao(operacao) {
        return new FormGroup({
            id: new FormControl(this.countId++),
            valor: new FormControl(operacao),
            isMostrarOpcoes: new FormControl(false)
        });
    }

    /**
     * Método responsável por recuperar o index do campo da calculadora
     *
     * @param formGroup
     *
     * @author Breno Prata - 28/06/2018
     */
    getIndex(formGroup: FormGroup) {

        const formArray = this.formArrayCalculadora;

        return formArray.value.findIndex(operacao => operacao.id === formGroup.value.id);
    }

    /**
     * Método responsável por excluir uma operação da calculadora
     *
     * @param {number} index
     *
     * @author Breno Prata - 28/06/2018
     */
    excluido(index: number) {

        const formArray = this.formArrayCalculadora;

        formArray.removeAt(index);

        this.formGroup.setControl('calculadora', formArray);

        this.validarFormula();
    }

    /**
     * Método responsável por realizar as operações após ter um item editado
     *
     * @author Breno Prata - 18/09/2018
     */
    itemEditado() {

        this.validarFormula();
    }

    /**
     * Método responsável por mover a operação uma posição para trás
     *
     * @param formGroup
     *
     * @author Breno Prata - 28/06/2018
     */
    moverParaTras(formGroup: FormGroup) {

        const formArray = this.formArrayCalculadora;
        const indexOperacao = this.getIndex(formGroup);
        const valorAnterior = formArray.value[indexOperacao - 1];
        const valorAtual = formArray.value[indexOperacao];
        const novoArray = formArray.value;

        if (indexOperacao === 0) {
            return;
        }

        novoArray[indexOperacao] = valorAnterior;
        novoArray[indexOperacao - 1] = valorAtual;

        this.formGroup.get('calculadora').setValue(novoArray);

        this.validarFormula();
    }

    /**
     * Método responsável por mover a operação uma posição para frente
     *
     * @param formGroup
     *
     * @author Breno Prata - 28/06/2018
     */
    moverParaFrente(formGroup: FormGroup) {

        const formArray = this.formArrayCalculadora;
        const indexOperacao = this.getIndex(formGroup);
        const valorAnterior = formArray.value[indexOperacao + 1];
        const valorAtual = formArray.value[indexOperacao];
        const novoArray = formArray.value;

        if (indexOperacao === this.formGroup.get('calculadora').value.length - 1) {
            return;
        }

        novoArray[indexOperacao] = valorAnterior;
        novoArray[indexOperacao + 1] = valorAtual;

        this.formGroup.get('calculadora').setValue(novoArray);

        this.validarFormula();
    }

    /**
     * Método responsável por limpar os dados da calculadora
     *
     * @author Breno Prata - 02/06/2018
     */
    limparCalculadora() {

        this.construirForm();
        this.valorInput = null;
    }

    /**
     * Método responsável por validar a fórmula da calculadora
     *
     * @author Breno Prata - 18/09/2018
     */
    validarFormula() {

        const calculadoraContainer = document.getElementById('calculadora-container');

        try {

            let formula = this.valorString.replace(new RegExp(/;/g), ' ');
            formula = this.substituirExpressaoFormula(formula, new RegExp(`${this.RUBRICA}`));
            formula = this.substituirExpressaoFormula(formula, new RegExp(`${this.VARIAVEL}`));
            formula = this.substituirExpressaoFormula(formula, new RegExp(`${this.CONSTANTE}`));
            formula = this.substituirExpressaoFormula(formula, new RegExp(`${this.CONDICIONAL}`));

            eval(formula);

            if (calculadoraContainer) {

                this.renderer.removeClass(calculadoraContainer, 'calculadora-formula-invalida');
            }

        } catch (e) {

            if (calculadoraContainer) {

                this.renderer.addClass(calculadoraContainer, 'calculadora-formula-invalida');
            }
        }
    }

    substituirExpressaoFormula(formula: string, regExp: RegExp): string {

        while(formula.match(regExp)) {

            formula = formula.replace(regExp, '4');
        }

        return formula;
    }

    /**
     * Método responsável por retornar o valor da calculadora em String
     *
     * @author Breno Prata - 28/06/2018
     */
    get valorString () {

        let valorForm = '';

        for (const item of this.formGroup.value.calculadora) {
            valorForm = `${valorForm}${item.valor};`;
        }

        return valorForm;
    }

    /**
     * Método responsável por retornar o formArray correspondente à calculadora
     *
     * @returns {FormArray}
     *
     * @author Breno Prata - 28/06/2018
     */
    get formArrayCalculadora(): FormArray {

        return <FormArray> this.formGroup.get('calculadora');
    }

}
