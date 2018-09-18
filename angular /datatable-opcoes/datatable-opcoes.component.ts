import {AfterViewInit, Component, Input, OnDestroy, Renderer2} from "@angular/core";

@Component({

    selector: 'app-datatable-opcoes',

    templateUrl: './datatable-opcoes.component.html',

    styleUrls: ['./datatable-opcoes.component.scss']
})
export class DatatableOpcoesComponent implements AfterViewInit {

    isMinimizado: boolean;

    constructor(private renderer: Renderer2) {}

    /**
     * Método responsável por inicializar a opção da datatable
     *
     * @author Breno Prata - 25/07/2018
     */
    ngAfterViewInit() {

        setTimeout(() => {
            this.inicializarMobile();
        }, 1);
    }

    /**
     * Método responsável por alternar entre mostrar e esconder as opções da datatable
     *
     * @author Breno Prata - 25/07/2018
     */
    mostrarEsconderOpcoes() {

        this.isMinimizado = !this.isMinimizado;

        const elemento =  document.querySelector('#datatable-item-esconder').getElementsByTagName('button').item(0);

        if (this.isMinimizado) {
            this.renderer.addClass(elemento, 'item-mostrar');
            this.renderer.removeClass(elemento, 'item-esconder');
            return;
        }
        this.renderer.addClass(elemento, 'item-esconder');
        this.renderer.removeClass(elemento, 'item-mostrar');
        return;
    }

    /**
     * Método responsável por inicializar o componente para os dispositivos mobile
     *
     * @author Breno Prata - 25/07/2018
     */
    inicializarMobile () {

        const elementos = document.querySelector('#app-datatable-opcoes').getElementsByTagName('app-datatable-opcoes-item');
        const qtdItens = document.getElementById('app-datatable-opcoes').children.length - 1;

        for (let cont = 0; cont < elementos.length; cont++) {

            this.renderer.setStyle(elementos.item(cont), 'width', `${100 / qtdItens}%`);
        }
    }

}