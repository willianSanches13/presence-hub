import {FormControl, FormGroup} from "@angular/forms";

export interface LoginForm {
    login: FormControl<string | null>;
    password: FormControl<string>;
}