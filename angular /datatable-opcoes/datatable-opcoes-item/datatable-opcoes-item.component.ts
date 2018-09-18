import {Component, Input} from "@angular/core";

@Component({

    selector: 'app-datatable-opcoes-item',

    templateUrl: './datatable-opcoes-item.component.html',

    styleUrls: ['./datatable-opcoes-item.component.scss']
})
export class DatatableOpcoesItemComponent {

    @Input('icon') icon: string;

    @Input('title') title: string;

    @Input('styleClass') styleClass: string

}