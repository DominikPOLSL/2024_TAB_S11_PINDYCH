import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, delay } from 'rxjs';
import { Model } from './model.interface';

@Injectable({
  providedIn: 'root',
})
export class ModelsService {
  constructor(private http: HttpClient) {}

  addModel(modelName: string, brandId: string) {
    const params = {
      brandName: brandId,
      modelName: modelName,
    };

    return this.http
      .post('http://localhost:8080/api/model/createBrandModel', params)
      .pipe(delay(1000));
  }

  getModelsByBrand(brandId: number): Observable<Model[]> {
    return this.http.get<Model[]>(
      `http://localhost:8080/api/model/getModelsByBrandId/${brandId}`
    );
  }
}
