import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private isWrongImportSource;
  isWrongImport: any;

  private errorMessageSource; 
  errorMessage:any;


  constructor(private http: HttpClient,private router: Router) { 
    let defValue=false;
    this.isWrongImportSource = new BehaviorSubject(defValue);
    this.isWrongImport = this.isWrongImportSource.asObservable();

    let defError=null;
    this.errorMessageSource = new BehaviorSubject(defError);
    this.errorMessage = this.errorMessageSource.asObservable();
  }


  setIsWrongImport(status){
    this.isWrongImportSource.next(status);
  }

  
  setErrorMessage(message){
    this.errorMessageSource.next(message);
  }

  getBuildings(){
    return this.http.get('http://localhost:8080/buildings/all')
  }

  getBuilding(buildingId){
    return this.http.get('http://localhost:8080/buildings/'+ buildingId)
  }

  postBuilding(building){
    this.http.post('http://localhost:8080/add-building', building)
      .subscribe(() => { this.router.navigate(['']); });
  }

  deleteBuilding(buildingId){
    this.http.delete("http://localhost:8080/buildings/" + buildingId)
    .subscribe(() => { this.router.navigate(['']); });
  }

  postConsumptions(excelToStringContent,buildingId) {
    this.http.post("http://localhost:8080/buildings?id=" + buildingId, excelToStringContent)
      .subscribe((response: Response) => {
        if(response !== null){
          this.setIsWrongImport(true);
          this.setErrorMessage(response); 
        }
        else{
         window.location.reload();
        }
       });
  }

  getNotifications(){
    return this.http.get('http://localhost:8080/algorithm',{responseType: 'text'})
  }

}
