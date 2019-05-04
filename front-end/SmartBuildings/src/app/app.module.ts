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

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    BuildingsComponent,
    DetailsComponent,
    NotificationsComponent,
    AddBuildingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
