import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { BuildingsComponent } from './buildings/buildings.component';
import { DetailsComponent } from './details/details.component';
import { NotificationsComponent } from './notifications/notifications.component'
import { AddBuildingComponent } from './add-building/add-building.component'

const routes: Routes = [
  {
    path: '',
    component: BuildingsComponent
  },
  {
    path: 'details/:id',
    component: DetailsComponent
  },
  {
    path: 'add-building',
    component: AddBuildingComponent
  },
  {
    path: 'notifications',
    component: NotificationsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }