import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import {UserForm} from "./user.form";
import {UserService} from "./user.service";
import {User} from "./user.model";

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  userForm: FormGroup<UserForm>;

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userForm = this.fb.group<UserForm>({
      login: this.fb.control('', Validators.required),
      password: this.fb.control('', [
        Validators.required,
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$')
      ]),
      confirmPassword: this.fb.control('', Validators.required),
      email: this.fb.control('', [Validators.required, Validators.email]),
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup): { [s: string]: boolean } | null {
    if (form.get('password')?.value !== form.get('confirmPassword')?.value) {
      return { passwordMismatch: true };
    }
    return null;
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
    this.router.navigate(['/users']);
  }
}