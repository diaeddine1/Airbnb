import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { House } from '../Components/homepage/homepage.component';

@Injectable({
  providedIn: 'root',
})
export class HouseServiceService {
  private apiUrl = 'http://localhost:8080/house/gethouses';

  constructor(private http: HttpClient) {}

  getHouses(headers: HttpHeaders): Observable<House[]> {
    return this.http.get<House[]>(this.apiUrl, { headers });
  }
}
