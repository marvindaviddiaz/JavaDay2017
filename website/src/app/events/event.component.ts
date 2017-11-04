import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { NotificationsService } from 'angular2-notifications';
import {NOTIFICATIONS_OPTIONS} from "../shared/constants";
import {EventService} from "./event.service";
import {Event} from "./event.model";

@Component({
    selector: 'app-home',
    templateUrl: './event.component.html',
    providers: [EventService]
})
export class EventComponent implements OnInit, OnDestroy {

    notifications_options = NOTIFICATIONS_OPTIONS;

    list: Event[]
    search: string;
    private subscriptionRoute: Subscription;

    constructor(
        private service: EventService,
        private router: Router,
        private route: ActivatedRoute,
        private _notifications: NotificationsService) { }

    ngOnInit() {
        this.subscriptionRoute = this.route.queryParams.subscribe((params: any) => {
                this.search = params.hasOwnProperty('search') ? params['search'] : '';
                this.loadAlll();
            }
        );
    }

    onSubmitSearch(value: string) {
        this.search = value;
        this.reload();
    }

    private loadAlll() {
        /* RETRIEVE DATA FROM SERVER */
        this.service.query({
            search: this.search
        }).subscribe(
            (data: any) => {
                this.list = data;
            },
            (message: string) => {
                this.showError(message);
            }
        );
    }

    private reload() {
        this.router.navigate(['/events'], {
            queryParams:
                {
                    search: this.search
                }
        });
    }

    private showError(error: string) {
        this._notifications.error('Error', error);
    }

    ngOnDestroy() {
        this.subscriptionRoute.unsubscribe();
    }
}
