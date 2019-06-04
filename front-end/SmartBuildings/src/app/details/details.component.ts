import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ImportPopUpComponent } from '../import-pop-up/import-pop-up.component';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {

  building$ : Object;
  id : String;
  arrayBuffer: any;
  file: File;
  excelToStringContent: any;
  wrongImport: any;
  errorMessage: any = "";

  constructor(private data: DataService, private route: ActivatedRoute) {
    this.route.params.subscribe( params => this.building$ = params.id );
   }

  ngOnInit() {
    this.data.getBuilding(this.building$).subscribe(
      data => this.building$ = data
    );
    this.id = this.route.snapshot.paramMap.get("id");
  }

  onDelete(){
    this.data.deleteBuilding(this.id);
  }

  getWrongImport() {
    this.data.isWrongImport.subscribe(isWrongImport => this.wrongImport = isWrongImport);
  }

  getErrorMessage() {
    this.data.errorMessage.subscribe(errorMessage => this.errorMessage = errorMessage);
  }

  incomingfile(event) {
    this.file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsBinaryString(this.file);
    reader.onloadend = (e) => {
      this.data.postConsumptions(btoa(reader.result as string), this.id);   
    }
    this.getErrorMessage();
    this.getWrongImport();
  }

  onCancel() {
    let popUp = document.getElementById("import-pop-up");
    popUp.classList.add("hidden");
  }

}
