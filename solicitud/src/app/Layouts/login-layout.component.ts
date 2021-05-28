import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-layout',
  template: `
  <div class="jumbotron">
    <router-outlet></router-outlet>
</div>
  `,
  styles: []
})
export class LoginLayoutComponent {}