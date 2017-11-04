import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class NavigationUtilService {

  constructor(private router: Router) { }

  notFoundRedirect() {
    console.log('No Encontrado');
    this.router.navigate(['/404']);
  }

}
