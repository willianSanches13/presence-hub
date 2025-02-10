import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from './user.service';
import { User } from './user.model';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {
  userForm: FormGroup;

  constructor(
      private fb: FormBuilder,
      private userService: UserService,
      private router: Router
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', [
        Validators.required,
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$')
      ]],
      confirmPassword: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup): { [key: string]: boolean } | null {
    return form.get('password').value === form.get('confirmPassword').value ? null : { passwordMismatch: true };
  }

  createUser(): void {
    if (this.userForm.valid) {
      const user: User = this.userForm.getRawValue();
      this.userService.create(user).subscribe(() => {
        this.userService.showMessage('User criado!');
        this.router.navigate(['/login']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/login']);
  }
}