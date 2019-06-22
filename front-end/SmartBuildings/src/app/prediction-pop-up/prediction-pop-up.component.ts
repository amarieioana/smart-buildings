import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-prediction-pop-up',
  templateUrl: './prediction-pop-up.component.html',
  styleUrls: ['./prediction-pop-up.component.scss']
})
export class PredictionPopUpComponent implements OnInit {

  @Output() cancel = new EventEmitter<boolean>()

  @Input() singlePredictionMessage: string;

  constructor() { }

  ngOnInit() {
  }

  onCancel() {
    this.cancel.emit(true);
  }
}
