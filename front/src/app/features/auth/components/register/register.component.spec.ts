import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { expect } from '@jest/globals';
import { AuthService } from '../../services/auth.service';
import { throwError } from 'rxjs';


import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async () => {
    // await TestBed.configureTestingModule({
    //   declarations: [RegisterComponent],
    //   imports: [
    //     BrowserAnimationsModule,
    //     HttpClientModule,
    //     ReactiveFormsModule,
    //     MatCardModule,
    //     MatFormFieldModule,
    //     MatIconModule,
    //     MatInputModule
    //   ]
    // })
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      providers: [
        {
          provide: AuthService,
          useValue: {
            register: jest.fn(),
          },
        },
      ],
      imports: [
        BrowserAnimationsModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ]
    })

      .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should set onError to true when register fails', () => {
    const authService = TestBed.inject(AuthService);
    (authService.register as jest.Mock).mockReturnValue(
      throwError(() => new Error('fail'))
    );

    component.submit();

    expect(component.onError).toBe(true);
  });

});
