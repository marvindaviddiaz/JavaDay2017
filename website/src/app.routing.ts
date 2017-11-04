
import {RouterModule, Routes} from "@angular/router";
import {EventComponent} from "./app/events/event.component";
import {EventFormComponent} from "./app/events/event-form.component";


const APP_ROUTES: Routes = [
    { path: '', redirectTo: '/events', pathMatch: 'full' },
    { path: 'events', component: EventComponent },
    { path: 'events/:event/:category', component: EventFormComponent },
];

export const routing = RouterModule.forRoot( APP_ROUTES, { useHash: true } );

