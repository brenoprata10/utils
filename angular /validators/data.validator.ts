import {AbstractControl, ValidatorFn} from "@angular/forms";

export function ValidatorMesAnoMask(): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {

        if (!control.value) {
            return null;
        }
        const mes = control.value.toString().split('/')[0];
        const ano = control.value.toString().split('/')[1];

        return mes > 0 && mes < 13 && ano > 1900 ? null : {'mesAnoMask': {value: true}}
    };
}

export function ValidatorMes(valorMinimo: number = 1, valorMaximo: number = 12): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {

        if (!control.value && control.value !== 0) {
            return null;
        }

        return control.value >= valorMinimo
                && control.value <= valorMaximo ? null : {'mes': {value: true, valorMinimo: valorMinimo, valorMaximo: valorMaximo}}
    };
}

export function ValidatorAno(valorMinimo: number = 1980, valorMaximo?: number): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {

        if (!control.value && control.value !== 0) {
            return null;
        }

        if (!valorMaximo) {
            return control.value >= valorMinimo ? null : {'ano': {value: true, valorMinimo: valorMinimo}};
        }

        return control.value >= valorMinimo
            && control.value <= valorMaximo ? null : {'ano': {value: true, valorMinimo: valorMinimo, valorMaximo: valorMaximo}}
    };
}