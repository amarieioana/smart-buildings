import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-buildings',
  templateUrl: './buildings.component.html',
  styleUrls: ['./buildings.component.scss']
})
export class BuildingsComponent implements OnInit {

  buildings$: Object;
  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getBuildings().subscribe(
      data => this.buildings$ = data
    )
  }

}
