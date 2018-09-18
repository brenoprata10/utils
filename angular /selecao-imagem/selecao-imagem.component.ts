import {Component, EventEmitter, Input, OnInit, Output, Renderer2} from "@angular/core";
import {SelecaoImagem} from "./selecao-imagem";

@Component({
    selector: 'app-selecao-imagem',

    templateUrl: './selecao-imagem.component.html',

    styleUrls: ['./selecao-imagem.component.scss']
})
export class SelecaoImagemComponent implements OnInit {

    @Input() imagens: SelecaoImagem[];
    @Input() selecaoMultipla: boolean = false;

    @Output() onImagemSelecionada = new EventEmitter<any>();

    imagensSelecionadas: SelecaoImagem[];

    constructor(private renderer: Renderer2) {}

    ngOnInit() {

        this.imagensSelecionadas = [];
    }

    /**
     * Método responsável por selecionar a imagem
     *
     * @param {MouseEvent} event
     * @param valor
     *
     * @author Breno Prata - 22/08/2018
     */
    selecionarImagem(event: MouseEvent, valor) {

        if (!this.selecaoMultipla) {

            this.limparImagemSelecionada(valor);
        }

        if (this.imagensSelecionadas.filter(imagem => imagem === valor).length > 0) {

            this.imagensSelecionadas.splice(this.imagensSelecionadas.indexOf(valor), 1);
            this.renderer.removeClass(event.target, 'imagem-selecionada');
            this.emitirEvento(valor);
            return;
        }

        this.imagensSelecionadas.push(valor);

        this.renderer.addClass(event.target, 'imagem-selecionada');

        this.emitirEvento(valor);
    }

    /**
     * Método responsável por emitir o evento de alteração
     *
     * @param valor
     *
     * @author Breno Prata - 31/08/2018
     */
    emitirEvento (valor) {

        this.onImagemSelecionada.emit({imagensSelecionadas: this.imagensSelecionadas, imagemSelecionada: valor});
    }

    /**
     * Método responsável por limpar a imagem selecionada
     *
     * @author Breno Prata - 22/08/2018
     */
    limparImagemSelecionada(valor) {

        this.imagensSelecionadas.splice(this.imagensSelecionadas.indexOf(valor), 1);

        const imagensSelecionadas = document.getElementsByClassName('imagem-selecionada');

        if (imagensSelecionadas) {

            for (let cont = 0; cont < imagensSelecionadas.length; cont++) {

                this.renderer.removeClass(imagensSelecionadas.item(cont), 'imagem-selecionada');
            }
        }
    }

    /**
     * Método responsável por limpar todas as imagens selecionadas
     *
     * @author Breno Prata - 31/08/2018
     */
    limparTodasImagens() {

        this.imagensSelecionadas = [];
    }

}