import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginServiceService } from '../../Services/login-service.service';

@Component({
  selector: 'Register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  email:string = '';
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginServiceService) {}
  
  Register(event:Event):void{
    event.preventDefault()
    const postData = {
      email:this.email,
      username: this.username,
      password: this.password
    };
    console.log("hey")
  }

}
