import {NgModule} from "@angular/core";
import {CalculadoraComponent} from "./calculadora.component";
import {ItemCalculadoraComponent} from "./item-calculadora/item-calculadora.component";
import {OperacaoComponent} from "./operacao/operacao.component";
import {PrimengImportsModule} from "../primeng-imports.module";
import {BrowserModule} from "@angular/platform-browser";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({

    exports: [
        OperacaoComponent,
        CalculadoraComponent,
        ItemCalculadoraComponent,
    ],

    imports: [
        FormsModule,
        CommonModule,
        BrowserModule,
        ReactiveFormsModule,
        PrimengImportsModule,
    ],

    declarations: [
        OperacaoComponent,
        CalculadoraComponent,
        ItemCalculadoraComponent,
    ]
})
export class CalculadoraModule {
}