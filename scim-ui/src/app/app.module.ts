import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectService } from './core/projectsService';

@NgModule({
  declarations: [
    AppComponent,
    ProjectDetailComponent,
    ProjectListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NgbModule,
    RouterModule.forRoot(
      [
        {
          path: 'projectdetail/:id',
          component: ProjectDetailComponent
        },
        {
          path: 'projectlist',
          component: ProjectListComponent
        },
        {
          path: '',
          component: ProjectListComponent
        }
      ]
    ),
  ],
  providers: [ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
