import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio } from 'src/app/estudio/estudio';
import { EstudioService } from 'src/app/estudio/estudio.service';
import { AuthService } from '../auth/auth.service';
import { ParametroAcuerdoService } from '../configuracion/parametro/acuerdo/parametroAcuerdo.service';
import { Parametro } from '../configuracion/parametro/parametro';
import { UnidadAcademicaService } from '../configuracion/parametro/unidad-academica/unidadAcademica.service';
import { Usuario } from '../usuario/usuario';
import { UserService } from '../usuario/usuario.service';

@Component({
    templateUrl: 'estudio-editar.component.html'
})

export class EstudioEditarComponent implements OnInit {
    mensajeError: string;
    title: string;
    rol: string;
    urlUser: any;
    urlDirector: any;
    estudioForm: FormGroup;
    estudio: Estudio;
    directorActivo: Usuario;
    unidadesAcademicas: Parametro[];

    get detalleTramiteDtos(): FormArray {
        return this.estudioForm.get('_detalleTramiteDtos') as FormArray;
    }

    constructor(private estudioService: EstudioService,
        private router: Router,
        private route: ActivatedRoute,
        private parametroAcuerdoService: ParametroAcuerdoService,
        private authService: AuthService,
        private usuarioService: UserService,
        private unidadAcademicaService: UnidadAcademicaService) { 
            this.rol = this.authService.getRole();
        }

    ngOnInit() { 
        this.route.data.subscribe(data => {            
            this.estudio = data['resolvedData'].estudio;
            this.estudioForm = data['resolvedData'].form;
        });
        console.log(this.estudio.unidadAcademica);
        this.getUnidadesAcademicas();
        this.getDirectorActivo();
        //Get director activo

        if (this.estudio.firmaDirector){
            //Get director(firma)
            this.getDirectorByFirma(this.estudio.firmaDirector);
            // Get firma para director  (imagen)
            this.getFirma(this.directorActivo.id, this.rol);
        }
        if (this.estudio.firmaUsuario){
            this.getFirma(this.estudio.solicitud.usuario.id, this.rol);
            //Get firma para usuario (imagen)
        }
        if (this.estudio.estado.estadoNombre == "CREADA") {
            this.getParametroAcuerdo();
        }

    }

    getParametroAcuerdo() {
        return this.parametroAcuerdoService.getParametroAcuerdoActivo().subscribe({
            next: parametro => this.estudioForm.get("acuerdo").setValue(parametro.descripcion),
            error: error => this.mensajeError = error
        });
    }

    getUnidadesAcademicas() {
        return this.unidadAcademicaService.getUnidadAcademicas().subscribe({
            next: parametros => this.unidadesAcademicas = parametros,
            error: error => this.mensajeError = error
        })
    }

    getDirectorActivo() {
        return this.usuarioService.getDirectorActivo().subscribe({
            next: directorActivo => this.directorActivo = directorActivo,
            error: error => this.mensajeError = error
        });
    }

    getFirma(id: number, rol: string) {
        this.usuarioService.getUsuarioFirma(id).subscribe({
            next: image => this.createImageFromBlob(image, rol),
            error: error => this.mensajeError = error
        });
    }

    createImageFromBlob(image: Blob, rol: string) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            if(rol == 'director'){
                this.urlUser = reader.result;
            }else{
                this.urlDirector = reader.result
            }
        }, false);
        if (image) {
            reader.readAsDataURL(image);
        }
    }

    getDirectorByFirma(firma: string) {
        return this.usuarioService.getUserByFirma(firma).subscribe({
            next: directorActivo => {
                this.directorActivo = directorActivo;
            },
            error: error => this.mensajeError = error
        });
    }

    signDirector(): void {
        if(!this.estudio.firmaDirector){
            this.estudioForm.get('firmaDirector').setValue(this.directorActivo.firma)
            const p = {...this.estudio, ...this.estudioForm.value}
            this.estudioService.updateEstudio(p).subscribe({
                next: () => this.getFirma(this.directorActivo.id, this.rol),
                error: error => this.mensajeError = error
            });
        }
        //save con string de director activo
        //Get firma
    }

    signUser(): void{
        //save con string de usuario que está en solicitud
        //Get firma
    }



    onSaveComplete(): void{
        this.estudioForm.reset();
        this.router.navigate(['/estudio']);
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