import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-import-pop-up',
  templateUrl: './import-pop-up.component.html',
  styleUrls: ['./import-pop-up.component.scss']
})
export class ImportPopUpComponent implements OnInit {

  @Output() cancel = new EventEmitter<boolean>()

  @Input() errorMessage: string;

  constructor() { }

  ngOnInit() {
  }

  onCancel() {
    this.cancel.emit(true);
  }
}
