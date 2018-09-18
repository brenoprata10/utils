import {Component, EventEmitter, HostListener, Input, Output, Renderer2} from "@angular/core";
import {FormGroup} from "@angular/forms";

@Component({
    selector: 'app-item-calculadora',

    templateUrl: './item-calculadora.component.html',

    styleUrls: ['./item-calculadora.component.scss']
})
export class ItemCalculadoraComponent {

    @Input() valor: FormGroup;
    @Input() index;
    @Output() excluido = new EventEmitter();
    @Output() editado = new EventEmitter();
    @Output() moverParaTras = new EventEmitter();
    @Output() moverParaFrente = new EventEmitter();

    isEdicao: boolean;
    isMostrarOpcaoEditar: boolean;
    valorEdicao: string;

    constructor(private renderer: Renderer2) {}

    /**
     * Método responsável por mostrar a opção de edição de determinado item
     *
     * @author Breno Prata - 28/06/2018
     */
    @HostListener('mouseover')
    mostrarOpcaoEditar() {

        this.isMostrarOpcaoEditar = true;
    }

    /**
     * Método responsável por esconder as opções de determinado item
     *
     * @author Breno Prata - 28/06/2018
     */
    @HostListener('mouseleave')
    esconderOpcaoEditar() {

        const elemento = document.getElementsByClassName('item-edicao');

        if (elemento && elemento.item(0)) {
            this.renderer.addClass(elemento.item(0), 'esconder-item');
        }

        setTimeout(() => {
            this.isMostrarOpcaoEditar = false;
        }, 300);
    }

    /**
     * Método responsável por mostrar as opções de determinado item
     *
     */
    mostrarOpcoes() {

        this.valor.get('isMostrarOpcoes').setValue(true);
    }

    /**
     * Método responsável por excluir um determinado item da calculadora
     *
     * @author Breno Prata - 28/06/2018
     */
    excluir() {
        this.excluido.emit(this.index);
    }

    /**
     * Método responsável por mover o item para trás na calculadora
     *
     * @author Breno Prata - 28/06/2018
     */
    moverParaTrasMethod() {

        this.esconderEdicao();
        this.moverParaTras.emit(this.valor);
    }

    /**
     * Método responsável por mover o item para frente na calculadora
     *
     * @author Breno Prata - 05/06/2018
     */
    moverParaFrenteMethod() {

        this.esconderEdicao();
        this.moverParaFrente.emit(this.valor);
    }

    /**
     * Método responsável por inicializar o modo de edição de um item
     *
     * @author Breno Prata - 28/06/2018
     */
    editar(event: any) {

        const tamanhoElemento = event.target.scrollWidth;
        this.valorEdicao = this.valor.value.valor;
        this.isEdicao = true;
        this.injetarWidthElementoOriginal(tamanhoElemento);
    }

    /**
     * Método responsável por injetar o tamanho do width original no modo de edição
     *
     * @param tamanhoElemento
     *
     * @author Breno Prata - 23/08/2018
     */
    injetarWidthElementoOriginal(tamanhoElemento) {

        setTimeout(() => {
            const elementosEdicao = document.getElementsByClassName('item-calculadora-container-edicao');

            for (let cont = 0; cont < elementosEdicao.length; cont++) {

                elementosEdicao.item(cont).setAttribute('style', `width: ${tamanhoElemento}px`);
            }
        }, 30);
    }

    /**
     * Método responsável por confirmar a edição de determinado item
     *
     * @author Breno Prata - 28/06/2018
     */
    efetuarEdicao() {
        this.esconderEdicao();
        this.valor.setValue({
            id: this.valor.value.id,
            valor: this.valorEdicao,
            isMostrarOpcoes: false
        });
        this.editado.emit(this.valor);
    }

    /**
     * Método responsável por esconder a opção de edição do item
     *
     * @author Breno Prata - 28/06/2018
     */
    esconderEdicao() {
        this.isEdicao = false;
        this.isMostrarOpcaoEditar = false;
    }

    /**
     * Método responsável por concluir as alterações referentes ao componente
     *
     * @author Breno Prata - 28/06/2018
     */
    concluirAlteracao() {

        this.esconderEdicao();
        this.valor.get('isMostrarOpcoes').setValue(false);
    }

    get isMostrarOpcoes(): boolean {
        return this.valor.get('isMostrarOpcoes').value;
    }


}