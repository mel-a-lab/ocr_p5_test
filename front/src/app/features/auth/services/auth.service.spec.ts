import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthService } from './auth.service';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { expect } from '@jest/globals';



describe('AuthService', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });

    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('register() envoie une requête POST sur /api/auth/register', () => {
    const payload: RegisterRequest = {
      email: 'test@test.com',
      firstName: 'Test',
      lastName: 'User',
      password: 'password123',
    };

    service.register(payload).subscribe();

    const req = httpMock.expectOne('api/auth/register');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(payload);

    req.flush(null);
  });

  it('login() envoie une requête POST sur /api/auth/login et retourne la session', () => {
    const payload: LoginRequest = {
      email: 'test@test.com',
      password: 'password123',
    };

    const mockSession: SessionInformation = {
      token: 'fake-token',
      type: 'Bearer',
      id: 1,
      username: 'test@test.com',
      firstName: 'Test',
      lastName: 'User',
      admin: false,
    };

    service.login(payload).subscribe((session) => {
      expect(session).toEqual(mockSession);


    });

    const req = httpMock.expectOne('api/auth/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(payload);



    req.flush(mockSession);
  });
});
