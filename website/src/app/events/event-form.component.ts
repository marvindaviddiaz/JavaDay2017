import {EventService} from './event.service';
import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {NotificationsService} from 'angular2-notifications';
import {EventRequest} from "./event-request.model";
import {NavigationUtilService} from "../shared/util/navigation-util.service";

@Component({
    selector: 'app-client-form',
    templateUrl: './event-form.component.html',
    providers: [EventService]
})
export class EventFormComponent implements OnInit, OnDestroy {

    form: FormGroup;
    private subscription: Subscription;

    event: string;
    category: string;
    entity: EventRequest = {};

    title: string;

    isNew: boolean;
    isSuccess = false;
    refNumber : number;

    constructor(
        private route: ActivatedRoute,
        private service: EventService,
        private router: Router,
        private navigationUtilService: NavigationUtilService,
        private _notifications: NotificationsService) { }

    ngOnInit() {
        this.subscription = this.route.url.subscribe(
            (params: any[]) => {

                this.isNew = true;
                this.title = 'Booking';

                this.event = params[1].path;
                this.category = params[2].path;

                // LOAD FORM

                this.form = new FormGroup({
                    'event': new FormControl(this.event, [Validators.required]),
                    'category': new FormControl(this.category, [Validators.required]),
                    'dpi': new FormControl('',[Validators.required]),
                    'name': new FormControl('', [Validators.required]),
                    'email': new FormControl('', [Validators.required]),
                    'quantity': new FormControl('', [Validators.required]),
                });

                this.form.get('event').disable();
                this.form.get('category').disable();
            }
        );
    }

    private lockForm() {
        this.form.get('event').disable();
        this.form.get('category').disable();
        this.form.get('dpi').disable();
        this.form.get('name').disable();
        this.form.get('email').disable();
        this.form.get('quantity').disable();
    }

    onSubmit() {
        console.log('onSubmit');
        if (this.form.valid) {
            this.entity = this.form.value;
            // uneditable fields
            this.entity.event = this.form.get('event').value;
            this.entity.category = this.form.get('category').value;

            this.service.create(this.entity).subscribe(
                (data: any) => this.successSubmit(data),
                (message: string) => this._notifications.error('Error', message));

        }
    }

    private successSubmit(ref: number) {
        this.isSuccess = true;
        this.refNumber = ref;
        this.lockForm();
    }

    onCancel() {
        this.router.navigate(['/events']);
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
