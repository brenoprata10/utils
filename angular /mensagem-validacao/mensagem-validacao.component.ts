import {Component, Input, OnChanges, Renderer2, SimpleChanges} from "@angular/core";
import {FormControl} from "@angular/forms";

@Component({

    selector: 'app-mensagem-validacao',

    templateUrl: './mensagem-validacao.component.html'

})
export class MensagemValidacaoComponent {

    @Input() formControl: FormControl;

    isMostrarCampoObrigatorio(): boolean {

        if (!this.formControl) {
            return false;
        }

        return this.formControl.hasError('required') && this.formControl.touched;
    }

    isMostrarMesIncorreto(): boolean {

        if (!this.formControl) {
            return false;
        }

        return this.formControl.hasError('mes') && this.formControl.touched;
    }

    isMostrarAnoIncorreto(): boolean {

        if (!this.formControl) {
            return false;
        }

        return this.formControl.hasError('ano') && this.formControl.touched;
    }

    isMostrarPadraoInvalido(): boolean {

        if (!this.formControl) {
            return false;
        }

        return this.formControl.hasError('pattern') && this.formControl.touched;
    }

    isMostrarValorMinimo(): boolean {

        if (!this.formControl) {
            return false;
        }

        return this.formControl.hasError('min') && this.formControl.touched;
    }

    get mensagemValidacaoAno(): string {

        if (this.formControl.getError('ano').valorMaximo) {
            return `Valores permitidos: ${this.formControl.getError('ano').valorMinimo} até ${this.formControl.getError('ano').valorMaximo}`;
        }

        return `Valor mínimo: ${this.formControl.getError('ano').valorMinimo}`;
    }

}