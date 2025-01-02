import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';

@Directive({
    selector: '[appHover]'
})
export class HoverDirective {

    constructor(private el: ElementRef, private renderer: Renderer2) {}

    @HostListener('mouseenter') onMouseEnter() {
        this.renderer.addClass(this.el.nativeElement, 'expanded');
    }

    @HostListener('mouseleave') onMouseLeave() {
        this.renderer.removeClass(this.el.nativeElement, 'expanded');
    }
}