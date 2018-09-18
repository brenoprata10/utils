import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {MessageService} from "primeng/components/common/messageservice";
import {Mensagem} from "../mensagem";
import {HttpErrorResponse} from "@angular/common/http";

@Injectable()
export class FormUtilsService {

    constructor(private router: Router,
                private messageService: MessageService) {}

    /**
     * Método responsável por mostrar os erros referentes à requisição
     *
     * @param {HttpErrorResponse} httpErrorResponse
     *
     * @author Breno Prata - 25/07/2018
     */
    tratarErro(httpErrorResponse: HttpErrorResponse) {

        if (httpErrorResponse.error.detail) {

            this.messageService.add(Mensagem.error(httpErrorResponse.error.detail, 'Mensagem do Servidor'));
            return;
        }

        this.messageService.add(Mensagem.error(httpErrorResponse.error.description, 'Mensagem do Servidor'));
        return;
    }

    /**
     * Método responsável por finalizar o formulário, redirecionando o usuário para a url em questão
     *
     * @param {string} url
     * @param {string} mensagem
     * @param {{}} params
     *
     * @author Breno Prata - 25/07/2018
     */
    finalizarForm(url: string, mensagem: string = null, params = {}) {

        this.router.navigate([url, params]);

        if (mensagem) {
            this.messageService.add(Mensagem.sucesso(mensagem));
            return;
        }

        this.messageService.add(Mensagem.sucesso());
    }

    /**
     * Método responsável por mover o scroll do navegador para a posição do elemento em questão
     *
     * @param {HTMLElement} element
     *
     * @author Breno Prata - 22/08/2018
     */
    moverScrollParaElemento(element: HTMLElement) {

        setTimeout(() => {
            window.scroll({
                top: window.scrollY + element.getBoundingClientRect().top - 75,
                behavior: 'smooth'
            });
        }, 500);
    }

}