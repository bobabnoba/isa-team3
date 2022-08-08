import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-client-sidebar',
  templateUrl: './client-sidebar.component.html',
  styleUrls: ['./client-sidebar.component.css']
})
export class ClientSidebarComponent implements OnInit {

  toggle: boolean = false;

  constructor(private _authService: AuthService, private _router: Router) { }

  ngOnInit(): void {
  }

  toggleMe() {
    this.toggle = !this.toggle;
  }

  logout() {
    this._authService.logout();
    this._router.navigate(['/login']);
  }

}
