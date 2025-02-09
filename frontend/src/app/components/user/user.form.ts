import { FormControl, FormGroup } from "@angular/forms";

export interface UserForm {
    login: FormControl<string>;
    password: FormControl<string>;
    confirmPassword: FormControl<string>;
    email: FormControl<string>;
}