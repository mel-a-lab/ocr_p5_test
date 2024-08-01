import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { MeComponent } from './me.component';
import { SessionService } from '../../services/session.service';
import { UserService } from '../../services/user.service';
import { User } from '../../interfaces/user.interface';

// Import global pour Jest
import 'jest';

describe('MeComponent Integration Test', () => {
    let component: MeComponent;
    let fixture: ComponentFixture<MeComponent>;
    let mockRouter: any;
    let mockSessionService: any;
    let mockUserService: any;
    let mockMatSnackBar: any;

    beforeEach(async () => {
        mockRouter = {
            navigate: jest.fn()
        };
        mockSessionService = {
            sessionInformation: {
                admin: true,
                id: 1,
                token: 'sample-token',
                type: 'admin',
                username: 'testuser',
                firstName: 'Test',
                lastName: 'User'
            },
            $isLogged: jest.fn().mockReturnValue(of(true)),
            logOut: jest.fn()
        };
        mockUserService = {
            getById: jest.fn().mockReturnValue(of({
                id: 1,
                firstName: 'John',
                lastName: 'Doe',
                email: 'test@example.com',
                admin: true,
                password: 'test!1234',
                createdAt: new Date()
            } as User)),
            delete: jest.fn().mockReturnValue(of({}))
        };
        mockMatSnackBar = {
            open: jest.fn()
        };

        await TestBed.configureTestingModule({
            declarations: [MeComponent],
            imports: [
                MatSnackBarModule,
                HttpClientModule,
                MatCardModule,
                MatFormFieldModule,
                MatIconModule,
                MatInputModule
            ],
            providers: [
                { provide: Router, useValue: mockRouter },
                { provide: SessionService, useValue: mockSessionService },
                { provide: UserService, useValue: mockUserService },
                { provide: MatSnackBar, useValue: mockMatSnackBar }
            ],
        }).compileComponents();

        fixture = TestBed.createComponent(MeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should fetch user information on init', () => {
        expect(mockUserService.getById).toHaveBeenCalledWith('1');
        expect(component.user).toEqual({
            id: 1,
            firstName: 'John',
            lastName: 'Doe',
            email: 'test@example.com',
            admin: true,
            password: 'test!1234',
            createdAt: expect.any(Date)
        });
    });

    it('should navigate back on back()', () => {
        window.history.back = jest.fn();
        component.back();
        expect(window.history.back).toHaveBeenCalled();
    });

    it('should delete user and navigate on delete()', () => {
        component.delete();
        expect(mockUserService.delete).toHaveBeenCalledWith('1');
        expect(mockMatSnackBar.open).toHaveBeenCalledWith('Your account has been deleted !', 'Close', { duration: 3000 });
        expect(mockSessionService.logOut).toHaveBeenCalled();
        expect(mockRouter.navigate).toHaveBeenCalledWith(['/']);
    });
});
