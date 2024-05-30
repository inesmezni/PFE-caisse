import {Injectable, Injector} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import {  BehaviorSubject, Observable} from 'rxjs';
import { finalize,  tap} from "rxjs/operators";
import { loader } from './loader';
export enum SubjectType {
  REQUEST,
  RESPONSE,
  UNDEFINED
}
@Injectable({
  providedIn: 'root'
})
export class LoaderInterceptor implements HttpInterceptor {
  obj={resived:false,number:0,statut:0}
  sub=new BehaviorSubject({number:0,status:0})

  constructor(private injector: Injector) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const loaderService = this.injector.get(loader);
    this.obj.statut=this.obj.statut+1
    return this.interceptTime(loaderService, request, next);

  }

  interceptTime(loaderService: loader, req: HttpRequest<any>, next: HttpHandler) {
    let responseTimes = 0;
    const startTimestamp = new Date().getTime();
    const newReq = req.clone({
      headers: req.headers.set("startTimestamp", startTimestamp.toString())
    })

    return next.handle(newReq).pipe(tap((res) => {
      const endTimestamp: number = new Date().getTime();
      const startTimestamp2: number = Number(newReq["headers"].get("startTimestamp"));
      responseTimes = endTimestamp - startTimestamp2;
    }), finalize(() => {
       this.obj.number=this.obj.number+1
         loaderService.sub.next(this.obj)

    }));
  }
}
