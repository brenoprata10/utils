import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, Renderer2} from "@angular/core";

@Component({
    selector: 'app-operacao',

    template: '<button id="operacao{{label}}" pButton [label]="label" (click)="adicionarOperacao()"></button>'
})
export class OperacaoComponent implements OnInit, AfterViewInit {

    @Input() valor;
    @Input() label: string;

    @Output() valorAdicionado = new EventEmitter();

    constructor(private renderer: Renderer2) {}

    /**
     * Método responsável por inicializar o componente
     *
     * @author Breno Prata - 25/07/2018
     */
    ngOnInit() {

        if (!this.label) {
            this.label = this.valor;
        }
    }

    ngAfterViewInit() {

        if (this.label && this.label.length < 2) {
            this.renderer.addClass(document.getElementById(`operacao${this.label}`), 'btn-operacao');
        }
    }

    adicionarOperacao() {
        this.valorAdicionado.emit(this.valor);
    }

}