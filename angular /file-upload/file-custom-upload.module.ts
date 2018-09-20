import {NgModule} from '@angular/core';
import {FileUploadComponent} from './file-custom-upload.component';
import {BlockUIModule, ProgressBarModule, FileUploadModule} from 'primeng/primeng';
import {CommonModule} from "@angular/common";

@NgModule({

  imports: [
    CommonModule,
    BlockUIModule,
    ProgressBarModule,
    FileUploadModule,
  ],

  declarations: [
    FileUploadComponent
  ],

  exports: [
    FileUploadComponent
  ]
})
export class FileCustomUploadModule {}
