import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginServiceService {
  private URL = 'http://localhost:8080/user/login';

  constructor(private http: HttpClient) {}

  getToken(postData: { username: string; password: string }): Observable<string> {
    return this.http.post(this.URL, postData, { responseType: 'text' });
  }
}
