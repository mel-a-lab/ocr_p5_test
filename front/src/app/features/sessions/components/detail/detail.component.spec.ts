import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { of } from 'rxjs';
import { SessionService } from '../../../../services/session.service';
import { SessionApiService } from '../../services/session-api.service';
import { TeacherService } from '../../../../services/teacher.service';
import { DetailComponent } from './detail.component';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { RouterTestingModule } from '@angular/router/testing';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

describe('DetailComponent', () => {
  let component: DetailComponent;
  let fixture: ComponentFixture<DetailComponent>;
  let sessionService: SessionService;
  let sessionApiService: jest.Mocked<SessionApiService>;
  let teacherService: jest.Mocked<TeacherService>;

  const mockSessionService = {
    sessionInformation: {
      admin: true,
      id: 1
    }
  };

  const mockSession = {
    id: '1',
    teacher_id: 1,
    users: [1, 2, 3]
  };

  const mockTeacher = {
    id: 1,
    name: 'Teacher Name'
  };

  beforeEach(async () => {
    sessionApiService = {
      delete: jest.fn().mockReturnValue(of({})),
      participate: jest.fn().mockReturnValue(of({})),
      unParticipate: jest.fn().mockReturnValue(of({})),
      detail: jest.fn().mockReturnValue(of(mockSession))
    } as unknown as jest.Mocked<SessionApiService>;

    teacherService = {
      detail: jest.fn().mockReturnValue(of(mockTeacher))
    } as unknown as jest.Mocked<TeacherService>;

    await TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        MatSnackBarModule,
        ReactiveFormsModule,
        MatCardModule,
        MatIconModule,
        RouterTestingModule
      ],
      declarations: [DetailComponent],
      providers: [
        { provide: SessionService, useValue: mockSessionService },
        { provide: SessionApiService, useValue: sessionApiService },
        { provide: TeacherService, useValue: teacherService },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => '1'
              }
            }
          }
        }
      ],
    }).compileComponents();

    sessionService = TestBed.inject(SessionService);
    fixture = TestBed.createComponent(DetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch session on init', () => {
    const fetchSessionSpy = jest.spyOn(component as any, 'fetchSession');
    component.ngOnInit();
    expect(fetchSessionSpy).toHaveBeenCalled();
  });

  it('should call delete on sessionApiService when delete is called', () => {
    component.delete();
    expect(sessionApiService.delete).toHaveBeenCalledWith(component.sessionId);
  });

  it('should call participate on sessionApiService when participate is called', () => {
    const fetchSessionSpy = jest.spyOn(component as any, 'fetchSession');
    component.participate();
    expect(sessionApiService.participate).toHaveBeenCalledWith(component.sessionId, component.userId);
    expect(fetchSessionSpy).toHaveBeenCalled();
  });

  it('should call unParticipate on sessionApiService when unParticipate is called', () => {
    const fetchSessionSpy = jest.spyOn(component as any, 'fetchSession');
    component.unParticipate();
    expect(sessionApiService.unParticipate).toHaveBeenCalledWith(component.sessionId, component.userId);
    expect(fetchSessionSpy).toHaveBeenCalled();
  });

  it('should fetch session and teacher details', () => {
    component.fetchSession();
    expect(sessionApiService.detail).toHaveBeenCalledWith(component.sessionId);
    expect(teacherService.detail).toHaveBeenCalledWith(mockSession.teacher_id.toString());
    expect(component.session).toEqual(mockSession);
    expect(component.teacher).toEqual(mockTeacher);
  });
});
