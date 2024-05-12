import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Brand } from './brand.interface';
import { Observable } from 'rxjs';
import { Model } from './model.interface';

@Injectable({
  providedIn: 'root',
})
export class ModelsService {
  constructor(private http: HttpClient) {}

  addModel(modelName: string, brandId: number) {
    const params = {
      modelName: modelName,
      brandId: brandId,
    };

    return this.http.post('http://localhost:8080/api/model', params);
  }

  getModelsByBrand(brandId: number): Observable<Model[]> {
    return this.http.get<Model[]>('');
  }
}
