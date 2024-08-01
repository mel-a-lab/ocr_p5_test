import { TestBed } from '@angular/core/testing';
import { BehaviorSubject } from 'rxjs';
import { take } from 'rxjs/operators';
import { SessionService } from './session.service';
import { SessionInformation } from '../interfaces/sessionInformation.interface';

describe('SessionService', () => {
  let service: SessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should initialize with isLogged as false', () => {
    expect(service.isLogged).toBe(false);
  });

  it('should initialize with sessionInformation as undefined', () => {
    expect(service.sessionInformation).toBeUndefined();
  });

  it('should return isLogged as an observable', (done) => {
    service.$isLogged().pipe(take(1)).subscribe((isLogged) => {
      expect(isLogged).toBe(false);
      done();
    });
  });

  it('should log in a user', () => {
    const user: SessionInformation = {
      id: 1,
      admin: true,
      token: 'sample-token',
      type: 'admin',
      username: 'testuser',
      firstName: 'Test',
      lastName: 'User'
    };

    service.logIn(user);
    expect(service.sessionInformation).toEqual(user);
    expect(service.isLogged).toBe(true);
  });

  it('should log out a user', () => {
    service.logOut();
    expect(service.sessionInformation).toBeUndefined();
    expect(service.isLogged).toBe(false);
  });

  it('should emit the new isLogged value when logging in', (done) => {
    const user: SessionInformation = {
      id: 1,
      admin: true,
      token: 'sample-token',
      type: 'admin',
      username: 'testuser',
      firstName: 'Test',
      lastName: 'User'
    };

    service.$isLogged().pipe(take(1)).subscribe((isLogged) => {
      expect(isLogged).toBe(false); // Before logging in, the value should be false
    });

    service.$isLogged().pipe(take(2)).subscribe((isLogged) => {
      if (isLogged) {
        expect(isLogged).toBe(true); // After logging in, the value should be true
        done();
      }
    });

    service.logIn(user);
  });

  it('should emit the new isLogged value when logging out', (done) => {
    const user: SessionInformation = {
      id: 1,
      admin: true,
      token: 'sample-token',
      type: 'admin',
      username: 'testuser',
      firstName: 'Test',
      lastName: 'User'
    };

    service.logIn(user); // Ensure the user is logged in before testing log out

    service.$isLogged().pipe(take(2)).subscribe({
      next: (isLogged) => {
        if (!isLogged) {
          expect(isLogged).toBe(false);
          done();
        }
      },
      error: (err) => {
        done(err);
      }
    });

    service.logOut();
  });
});
