import {  Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
 })

export class loader implements OnInit {
  isNavigated=false
  sub=new BehaviorSubject({resived:false,number:0,statut:0})
  ngOnInit(): void {

  }
}
