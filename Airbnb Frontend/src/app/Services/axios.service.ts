import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root',
})
export class AxiosService {
  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080';
    //axios.defaults.headers.post['Content-Type'] = 'multipart/form-data'; // Default Content-Type
  }

  getToken(): string | null {
    return window.localStorage.getItem('JWTToken');
  }

  request(
    method: string,
    url: string,
    data: any,
    customHeaders: Record<string, string> = {}
  ): Promise<any> {
    // Default headers
    let headers: Record<string, string> = {
      ...customHeaders,
    };

    // Add Authorization header if token exists
    if (this.getToken() !== null) {
      headers['Authorization'] = 'Bearer ' + this.getToken();
    }

    return axios({
      method: method,
      url: url,
      data: data,
      headers: headers,
    });
  }
}
