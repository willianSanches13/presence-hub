// hide-nav.directive.ts
import { Directive, ElementRef, Renderer2, Input, OnInit } from '@angular/core';

@Directive({
    selector: '[appHideNav]'
})
export class HideNavDirective implements OnInit {
    @Input() appHideNav: boolean;

    constructor(private el: ElementRef, private renderer: Renderer2) {}

    ngOnInit() {
        if (this.appHideNav) {
            this.renderer.setStyle(this.el.nativeElement, 'display', 'none');
        } else {
            this.renderer.removeStyle(this.el.nativeElement, 'display');
        }
    }
}