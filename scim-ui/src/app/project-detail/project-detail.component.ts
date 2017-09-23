import { Component, OnInit } from '@angular/core';
import { Project } from '../core/model';
import { ActivatedRoute, Params } from '@angular/router';
import { ProjectService } from '../core/projectsService';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent implements OnInit {

  project: Project = new Project();

    constructor(private route: ActivatedRoute, private solutionService: ProjectService) { }

    ngOnInit() {
      this.route.params.forEach((params: Params) => {
        if (params['id'] !== undefined) {
          this.solutionService.getProhject(params['id']).then(s => {
            this.project = s;
          });
        }
      });
    }

    save() {
      this.solutionService.post(this.project);
    }

}
