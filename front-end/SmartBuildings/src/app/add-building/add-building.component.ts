import { Component, OnInit } from '@angular/core';
import { Building } from '../model/building';
import { Product } from '../model/product';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-building',
  templateUrl: './add-building.component.html',
  styleUrls: ['./add-building.component.scss']
})
export class AddBuildingComponent implements OnInit {

  building: Building;
  submitted = false;
  
  constructor(private data: DataService) { 
    this.building= new Building();
    this.building.active=true;
    this.building.products= new Array<Product>();
  }

  ngOnInit() {
  }

  onSubmit() { 
    this.submitted = true; 
    this.data.postBuilding(this.building);
  }

  

}
