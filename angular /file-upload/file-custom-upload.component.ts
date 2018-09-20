import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FileUpload} from 'primeng/primeng';

@Component({

  moduleId: module.id,

  selector: 'app-file-custom-upload',

  templateUrl: './file-custom-upload.component.html',

  styleUrls: ['./file-custom-upload.component.scss']

})
export class FileUploadComponent {

  MENSAGEM_PADRAO_FILE_UPLOAD = 'Aguarde! Enviando arquivo';

  @Input() styleClass: string;
  @Input('url') url: string;
  @Input('auto') auto: boolean;
  @Input('mode') mode: string;
  @Input('accept') accept: string;
  @Input('multiple') multiple: boolean;
  @Input('uploadLabel') uploadLabel: string;
  @Input('cancelLabel') cancelLabel: string;
  @Input('maxFileSize') maxFileSize: number;
  @Input('chooseLabel') chooseLabel: string;
  @Input('fileUpload') fileUpload: FileUpload;
  @Input('invalidFileSizeMessageDetail') invalidFileSizeMessageDetail: string;
  @Input('invalidFileSizeMessageSummary') invalidFileSizeMessageSummary: string;

  @Output() onError = new EventEmitter();
  @Output() onSelect = new EventEmitter();
  @Output() onUpload = new EventEmitter();
  @Output() onProgress = new EventEmitter();
  @Output() onBeforeSend = new EventEmitter();
  @Output() onBeforeUpload = new EventEmitter();

  arquivosSelecionados;
  progressoUpload: number;
  bloquearUpload: boolean;
  nomeArquivoSelecionado: string;
  mensagemUpload = this.MENSAGEM_PADRAO_FILE_UPLOAD;
  isErroAnexo = false;

  onUploadCustom(event) {
    this.bloquearUpload = false;
    this.onUpload.emit(event);
  }

  onBeforeSendCustom(event) {
    this.bloquearUpload = true;
    this.progressoUpload = 0;
    this.onBeforeSend.emit(event);
  }

  onProgressCustom(event) {
    this.progressoUpload = event.progress;
    this.onProgress.emit(event);
  }

  onSelectCustom(event) {
    this.arquivosSelecionados = event.files[0];
    this.nomeArquivoSelecionado = this.arquivosSelecionados.name;
    if (this.arquivosSelecionados && this.arquivosSelecionados.name.length > 30) {
      this.nomeArquivoSelecionado = event.files[0].name.substring(0, 30).concat(`.${this.arquivosSelecionados.type.split('/')[1]}`);
    }
    this.onSelect.emit(event);
  }

  onErrorCustom(event) {
    this.mensagemUpload = 'Não foi possível realizar o envio!';
    this.isErroAnexo = true;
    this.progressoUpload = 0;
    this.onError.emit(event);
  }

  onBeforeUploadCustom(event) {
    this.onBeforeUpload.emit(event);
  }

  unlockBlockUI() {
    this.isErroAnexo = false;
    this.bloquearUpload = false;
    this.mensagemUpload = this.MENSAGEM_PADRAO_FILE_UPLOAD;
  }

}
