import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Brand } from './brand.interface';
import { Observable } from 'rxjs';
import { Model } from './model.interface';

@Injectable({
  providedIn: 'root',
})
export class BrandsService {
  constructor(private http: HttpClient) {}

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>('http://localhost:8080/api/brand');
  }
}
