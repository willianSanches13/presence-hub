import { Directive, ElementRef, Renderer2, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Directive({
    selector: '[appHideNavbar]'
})
export class HideNavbarDirective implements OnInit {

    constructor(private el: ElementRef, private renderer: Renderer2, private router: Router) { }

    ngOnInit() {
        this.router.events.pipe(
            filter(event => event instanceof NavigationEnd)
        ).subscribe((event: NavigationEnd) => {
            const sidenav = this.el.nativeElement.querySelector('mat-sidenav');
            if (event.url === '/login' || event.url === '/users') {
                this.renderer.setStyle(sidenav, 'display', 'none');
            } else {
                this.renderer.removeStyle(sidenav, 'display');
            }
        });
    }
}