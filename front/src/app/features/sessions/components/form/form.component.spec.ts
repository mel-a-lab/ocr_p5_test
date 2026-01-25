import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import {  ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';
import { SessionService } from 'src/app/services/session.service';
import { SessionApiService } from '../../services/session-api.service';
import { of } from 'rxjs';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';



import { FormComponent } from './form.component';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;

  const mockSessionService = {
    sessionInformation: {
      admin: true
    }
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({

      imports: [
        RouterTestingModule.withRoutes([{ path: 'sessions', component: FormComponent }]),
        HttpClientModule,
        MatCardModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatSnackBarModule,
        MatSelectModule,
        NoopAnimationsModule
      ],
      providers: [
        { provide: SessionService, useValue: mockSessionService },
        SessionApiService
      ],
      declarations: [FormComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;

    const router = TestBed.inject(Router);
    jest.spyOn(router, 'navigate').mockResolvedValue(true as any);

    fixture.detectChanges();
  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should call create on submit when onUpdate is false', () => {

    component.onUpdate = false;

    expect(component.sessionForm).toBeTruthy();

    const api = TestBed.inject(SessionApiService);
    const createSpy = jest.spyOn(api, 'create').mockReturnValue(of({} as any));

    component.submit();

    expect(createSpy).toHaveBeenCalled();
  });

  it('should call update on submit when onUpdate is true', () => {
    // 1) on force le mode update
    component.onUpdate = true;
    (component as any).id = '123';

    expect(component.sessionForm).toBeTruthy();

    const api = TestBed.inject(SessionApiService);
    const updateSpy = jest.spyOn(api, 'update').mockReturnValue(of({} as any));

    component.submit();

    expect(updateSpy).toHaveBeenCalledWith('123', component.sessionForm?.value);
  });

  it('should redirect to /sessions when user is not admin', () => {
    mockSessionService.sessionInformation.admin = false;

    const routerSpy = jest.spyOn((component as any).router, 'navigate');

    component.ngOnInit();

    expect(routerSpy).toHaveBeenCalledWith(['/sessions']);

    mockSessionService.sessionInformation.admin = true;

  });

  it('should go into update mode in ngOnInit when url includes "update"', () => {
    // 1) on force l’URL à contenir "update"
    Object.defineProperty((component as any).router, 'url', {
      get: () => '/sessions/update/123',
    });

    (component as any).route = {
      snapshot: { paramMap: { get: () => '123' } }
    };

    const api = TestBed.inject(SessionApiService);
    const mockSession = {
      name: 'S',
      date: new Date().toISOString(),
      teacher_id: 1,
      description: 'D',
    } as any;

    const detailSpy = jest.spyOn(api, 'detail').mockReturnValue(of(mockSession));


    component.ngOnInit();


    expect(component.onUpdate).toBe(true);
    expect(detailSpy).toHaveBeenCalledWith('123');
    expect(component.sessionForm).toBeTruthy();
  });



});
