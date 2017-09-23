import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ProjectListComponent } from './project-list.component';
import { FormsModule } from '@angular/forms';
import { ProjectService } from '../core/projectsService';
import { Project } from '../core/model';

class ProjectServiceStub {
  getProjects(): Promise<Project[]> {
    return Promise.resolve({});
  }
}

describe('ProjectListComponent', () => {
  let component: ProjectListComponent;
  let fixture: ComponentFixture<ProjectListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [ ProjectListComponent ],
      providers: [
        { provide: ProjectService, useClass: ProjectServiceStub }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
