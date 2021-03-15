import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import domtoimage from 'dom-to-image';
import * as jsPDF from 'jspdf';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Estudio } from 'src/app/models/estudio';
import { EstudioService } from 'src/app/services/estudio.service';

@Component({
    templateUrl: 'estudio-previo-editar.component.html'
})

export class EstudioPrevioEditarComponent implements OnInit {
    mensajeError: string;
    title: string;

    estudioForm: FormGroup;
    estudio: Estudio;

    fecha = new FormControl();
    centroCostos = new FormControl();
    necesidad = new FormControl();
    descripcion = new FormControl();
    valor = new FormControl();
    verificacion = new FormControl();

    private sub: Subscription;
    @ViewChild('content') content: ElementRef;

    
    constructor(private estudioService: EstudioService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder) { }

    ngOnInit() { 
        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');
                this.getEstudio(id);
            }
        );
        this.estudioForm = this.fb.group({
            acuerdo: '',
            unidad: '',
            firma: '',
            solicitud: this.fb.group({
                idSolicitud: ''
            })

        });
    }

    getEstudio(id: number) {
        this.estudioService.getEstudio(id).subscribe({
            next: (estudio: Estudio) => this.displayEstudio(estudio),
            error: err => this.mensajeError = err
        });
    }

    onSaveComplete(): void{
        this.estudioForm.reset();
        this.router.navigate(['/estudio']);
    }

    displayEstudio(estudio: Estudio): void {
        if(this.estudioForm){
            this.estudioForm.reset();
        }
        this.estudio = estudio;
        // this.title  = `Editar estudio: ${this.estudio.solicitud.nombreProyecto}`;
        // this.fecha.setValue(this.estudio.solicitud.fecha);
        // this.centroCostos .setValue(this.estudio.solicitud.centroCostos);
        // this.necesidad.setValue(this.estudio.solicitud.necesidad);
        // this.descripcion.setValue(this.estudio.solicitud.descripcion);
        // this.valor.setValue(this.estudio.solicitud.valor);
        // this.verificacion.setValue(this.estudio.solicitud.verificacion);
        // this.estudioForm.patchValue({
        //     acuerdo: estudio.acuerdo,
        //     unidad: estudio.unidad,
        //     firma: estudio.unidad,
        //     director: estudio.director,
        //     solicitud: {
        //         idSolicitud: estudio.solicitud.idSolicitud
        //     }
        // });
    }

    save(): void {
        
    }

    // public downloadPdf(){
    //     var node = document.getElementById('content');

    //     var img;
    //     var filename;
    //     var newImage;


    //     domtoimage.toPng(node, { bgcolor: '#fff' })

    //       .then(function(dataUrl) {

    //         img = new Image();
    //         img.src = dataUrl;
    //         newImage = img.src;

    //         img.onload = function(){

    //         var pdfWidth = img.width;
    //         var pdfHeight = img.height;

    //           // FileSaver.saveAs(dataUrl, 'my-pdfimage.png'); // Save as Image

    //           var doc;

    //           if(pdfWidth > pdfHeight)
    //           {
    //             doc = new jsPDF('l', 'px', [pdfWidth , pdfHeight]);
    //           }
    //           else
    //           {
    //             doc = new jsPDF('p', 'px', [pdfWidth , pdfHeight]);
    //           }


    //           var width = doc.internal.pageSize.getWidth();
    //           var height = doc.internal.pageSize.getHeight();


    //           doc.addImage(newImage, 'PNG',  10, 10, width, height);
    //           filename = 'mypdf_' + '.pdf';
    //           doc.save(filename);

    //         };


    //       })
    //       .catch(function(error) {

    //        // Error Handling

    //       });


    // }
}