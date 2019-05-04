import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  getBuildings(){
    return this.http.get('http://localhost:8080/buildings/all')
  }

  getBuilding(buildingId){
    return this.http.get('http://localhost:8080/buildings/'+ buildingId)
  }
}
