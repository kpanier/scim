import { Component, OnInit } from '@angular/core';
import { Project } from '../core/model';
import { ProjectService } from '../core/projectsService';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Project[];
  newProject: Project = new Project();

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.projectService.getProjects().then(p => this.projects = p);
  }

  save() {
    this.projectService.post(this.newProject);
    this.projects.push(this.newProject);
  }

}
