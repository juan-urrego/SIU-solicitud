import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
import { AuthService } from '../../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SolInterceptorService implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
    let intReq = req;
    const token = this.authService.getToken();
    if(token != null){
      intReq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token)});
    }
    return next.handle(intReq);
  }
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: SolInterceptorService, multi: true}];
