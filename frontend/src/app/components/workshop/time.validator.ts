import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function timeRangeValidator(startTimeKey: string, endTimeKey: string): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
        const startTime = formGroup.get(startTimeKey)?.value;
        const endTime = formGroup.get(endTimeKey)?.value;

        if (startTime && endTime && startTime >= endTime) {
            return { timeRangeInvalid: true };
        }
        return null;
    };
}