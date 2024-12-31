import { Component, inject } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AxiosService } from '../../Services/axios.service';

@Component({
  selector: 'Login',
  imports: [FormsModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  router = inject(Router)
  username: string = '';
  password: string = '';

  constructor(private axiosService: AxiosService) {}

  Login(event: Event): void {
    // Prevent default form submission behavior
    event.preventDefault();

    this.axiosService.request(
      "POST",
      "/user/login",
      {"username":this.username,"password":this.password}
    ).then((response)=>{
      console.log('Login successful!',response);
      window.localStorage.setItem("JWTToken",response.data)
      this.router.navigate(['/Homepage'])
    });

    // this.loginService.getToken(postData).subscribe({
    //   next: (data) => {
    //     console.log('Login successful:', data);
    //     localStorage.setItem('JWTToken',data)
    //     this.router.navigate(['/Homepage'])
    //   },
    //   error: (err) => {
    //     console.error('Login failed:', err);
    //     alert('Login Failed!');
    //   }
    // });
  }

}
