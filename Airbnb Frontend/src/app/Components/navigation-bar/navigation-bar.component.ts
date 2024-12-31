import { Component, Inject, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'Navigation-Bar',
  imports: [RouterModule],
  templateUrl: './navigation-bar.component.html',
  styleUrl: './navigation-bar.component.css'
})
export class NavigationBarComponent {

  constructor(private router: Router) {}


  Add_House(event:Event){


    this.router.navigate(['/addHouse'])

  }

}
