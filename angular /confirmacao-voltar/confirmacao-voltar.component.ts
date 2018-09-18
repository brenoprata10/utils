import {Component, Input} from "@angular/core";
import {ConfirmationService} from "primeng/api";
import {Router} from "@angular/router";

@Component({

    selector: 'app-confirmacao-voltar',

    templateUrl: './confirmacao-voltar.component.html'
})
export class ConfirmacaoVoltarComponent {

    @Input() routerLinkVoltar;

    constructor(private router: Router,
                private confirmationService: ConfirmationService) {}

    confimarPercaAlteracoes() {
        this.confirmationService.confirm({
            message: 'Existem alterações pendentes, as alterações serão perdidas. Deseja continuar?',
            header: 'Confirmação',
            icon: 'ui-icon-help',
            acceptLabel: 'Sim',
            rejectLabel: 'Não',
            accept: () => {
                this.router.navigate(this.routerLinkVoltar);
            },
            reject: () => {
            }
        });
    }

}