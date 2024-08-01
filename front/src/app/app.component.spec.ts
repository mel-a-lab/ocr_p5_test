import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { AppComponent } from './app.component';
import { SessionService } from './services/session.service';

describe('AppComponent', () => {
  let fixture: any;
  let app: any;
  let mockRouter: any;
  let mockSessionService: any;

  beforeEach(async () => {
    mockRouter = {
      navigate: jest.fn()
    };
    mockSessionService = {
      logOut: jest.fn(),
      $isLogged: jest.fn().mockReturnValue(of(true))
    };

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatToolbarModule
      ],
      declarations: [
        AppComponent
      ],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: SessionService, useValue: mockSessionService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    app = fixture.componentInstance;
  });

  it('should create the app', () => {
    // @ts-ignore
    expect(app).toBeTruthy();
  });

  it('should return the isLogged observable', (done) => {
    app.$isLogged().subscribe((isLogged: boolean) => {
      // @ts-ignore
      expect(isLogged).toBe(true);
      done();
    });
  });

  it('should call sessionService.logOut and navigate on logout', () => {
    app.logout();
    // @ts-ignore
    expect(mockSessionService.logOut).toHaveBeenCalled();
    // @ts-ignore
    expect(mockRouter.navigate).toHaveBeenCalledWith(['']);
  });
});
