import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { FileUploadModule } from 'primeng/fileupload';
import { ConfirmationService, MessageService } from "primeng/api";
import { ButtonModule } from "primeng/button";
import {ToggleButtonModule} from 'primeng/togglebutton';
import {SplitButtonModule} from 'primeng/splitbutton';
import { CardModule } from "primeng/card";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { DialogModule } from "primeng/dialog";
import { InputTextModule } from "primeng/inputtext";
import { InputTextareaModule } from "primeng/inputtextarea";
import { RadioButtonModule } from "primeng/radiobutton";
import { TableModule } from "primeng/table";
import { ToastModule } from "primeng/toast";
import { DropdownModule } from "primeng/dropdown";
import {InputMaskModule} from 'primeng/inputmask';
import {CalendarModule} from 'primeng/calendar';
import {InputNumberModule} from 'primeng/inputnumber';
import {StepsModule} from 'primeng/steps';
import {BadgeModule} from 'primeng/badge';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        StepsModule,        
        FileUploadModule,
        TableModule,
        BadgeModule,
        RadioButtonModule,
        ButtonModule,
        ToggleButtonModule,
        SplitButtonModule,
        DropdownModule,
        CalendarModule,
        InputMaskModule,
        InputNumberModule,
        ToastModule,
        CardModule,
        DialogModule,
        InputTextModule,
        ConfirmDialogModule,
        InputTextareaModule
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        StepsModule,
        FileUploadModule,
        TableModule,
        BadgeModule,
        RadioButtonModule,
        ButtonModule,
        ToggleButtonModule,
        SplitButtonModule,
        DropdownModule,
        CalendarModule,
        InputMaskModule,
        InputNumberModule,
        ToastModule,
        CardModule,
        DialogModule,
        InputTextModule,
        ConfirmDialogModule,
        InputTextareaModule
    ],
    providers: [MessageService, ConfirmationService]
})
export class SharedModule {}