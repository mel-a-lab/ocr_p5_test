import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SessionApiService } from './session-api.service';
import { Session } from '../interfaces/session.interface';
import { expect } from '@jest/globals';


describe('SessionApiService', () => {
  let service: SessionApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });

    service = TestBed.inject(SessionApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should get all sessions', () => {
    service.all().subscribe();

    const req = httpMock.expectOne('api/session');
    expect(req.request.method).toBe('GET');

    req.flush([]);
  });

  it('should get session detail', () => {
    service.detail('1').subscribe();

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('GET');

    req.flush({});
  });

  it('should delete session', () => {
    service.delete('2').subscribe();

    const req = httpMock.expectOne('api/session/2');
    expect(req.request.method).toBe('DELETE');

    req.flush(null);
  });

  it('should create session', () => {
    const session = {} as Session;

    service.create(session).subscribe();

    const req = httpMock.expectOne('api/session');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toBe(session);

    req.flush(session);
  });

  it('should update session', () => {
    const session = {} as Session;

    service.update('3', session).subscribe();

    const req = httpMock.expectOne('api/session/3');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toBe(session);

    req.flush(session);
  });

  it('should participate', () => {
    service.participate('4', '10').subscribe();

    const req = httpMock.expectOne('api/session/4/participate/10');
    expect(req.request.method).toBe('POST');

    req.flush(null);
  });

  it('should unParticipate', () => {
    service.unParticipate('5', '11').subscribe();

    const req = httpMock.expectOne('api/session/5/participate/11');
    expect(req.request.method).toBe('DELETE');

    req.flush(null);
  });
});
