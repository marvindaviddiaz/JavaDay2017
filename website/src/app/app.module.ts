import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {NotificationsService, SimpleNotificationsModule} from 'angular2-notifications';

import {AppComponent} from './app.component';
import {HttpUtilService} from "./shared/util/http-util.service";
import {routing} from "../app.routing";
import {EventComponent} from "./events/event.component";
import {EventFormComponent} from "./events/event-form.component";
import {NavigationUtilService} from "./shared/util/navigation-util.service";

@NgModule({
    declarations: [
        AppComponent,
        EventComponent,
        EventFormComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        BrowserAnimationsModule,
        routing,
        ReactiveFormsModule,
        SimpleNotificationsModule
    ],
    providers: [NotificationsService, HttpUtilService, NavigationUtilService],
    bootstrap: [AppComponent]

})
export class AppModule { }
