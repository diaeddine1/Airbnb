import { Component, Input } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { LoginServiceService } from './Services/login-service.service';
import { LoginComponent } from "./Components/login/login.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'Login-Front-End';
 
  
}