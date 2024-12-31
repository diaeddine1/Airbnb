import { Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { HomepageComponent } from './Components/homepage/homepage.component';
import { AddHouseComponent } from './Components/add-house/add-house.component';

export const routes: Routes = [
    {path:'',component:LoginComponent},
    {path:'Register',component:RegisterComponent},
    {path:'Homepage',component:HomepageComponent},
    {path:'addHouse',component:AddHouseComponent}
];
