import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { BuildingsComponent } from './buildings/buildings.component';
import { DetailsComponent } from './details/details.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { AddBuildingComponent } from './add-building/add-building.component';

import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ImportPopUpComponent } from './import-pop-up/import-pop-up.component';
import { DownloadComponent } from './download/download.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    BuildingsComponent,
    DetailsComponent,
    NotificationsComponent,
    AddBuildingComponent,
    ImportPopUpComponent,
    DownloadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
