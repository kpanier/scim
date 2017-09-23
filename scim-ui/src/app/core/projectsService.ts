import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Project } from './model';

@Injectable()
export class ProjectService {

  constructor(private http: Http) {
  }

  getProjects(): Promise<Project[]> {
    return this.http.get('http://localhost:8090/api/projects').toPromise().then(
      response => response.json() as Project[]
    ).catch(this.handleError);
  }

  getProhject(name: String): Promise<Project> {
    return this.http.get('http://localhost:8090/api/projects/' + name).toPromise().then(
      response => response.json() as Project
    ).catch(this.handleError);
  }

  post(project: Project) {
    this.http.post('http://localhost:8090/api/projects/', project).toPromise().catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for development purposes only
    return Promise.reject(error.message || error);
  }
}
