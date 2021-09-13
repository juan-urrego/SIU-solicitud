import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../auth/auth.service';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SolInterceptorService implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{

    if(!this.authService.isLogged()) {
      return next.handle(req)
    }

    let intReq = req;
    const token = this.authService.getToken();
    intReq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token)});
    
    return next.handle(intReq).pipe(catchError((error: HttpErrorResponse) => {
      console.log('error', error.status);
      if(error.status === 401) {
        console.log("sesion expirada");
      }
      this.authService.logOut();
      return throwError(error);
    }));
  }
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: SolInterceptorService, multi: true}];
