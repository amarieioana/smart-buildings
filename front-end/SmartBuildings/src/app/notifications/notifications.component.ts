import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {

  notifications$: Array<String>;
  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getNotifications().subscribe(
      data => { this.notifications$ = data.split("In "); this.notifications$.shift();}
    )
  }

  myFunction(event: any) {
    let input, filter, ul, li, a, i, txtValue;
    filter = event.target.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        txtValue = li[i].innerText
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

}
