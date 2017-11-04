import { Event } from './event.model';
import { Injectable } from '@angular/core';
import { Http, BaseRequestOptions, URLSearchParams, Response, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/Rx';
import {HttpUtilService} from "../shared/util/http-util.service";
import {EventRequest} from "./event-request.model";

@Injectable()
export class EventService {

    private resourceUrl = '/search/search';
    private resourceBookUrl = '/book/booking';

    constructor(
        private http: Http,
        private httpUtil: HttpUtilService) { }


    query(req?: any): Observable<Response> {
        return this.http.get(this.resourceUrl, this.createRequestOption(req))
            .map((data: Response) => data.json())
            .catch(this.httpUtil.handleError);
    }

    create(entity: EventRequest): Observable<number> {
        return this.http.post(this.resourceBookUrl, Object.assign({}, entity))
            .map((data: Response) => data.json())
            .catch(this.httpUtil.handleError);
    }

    private createRequestOption(req?: any): BaseRequestOptions {
        let options: BaseRequestOptions = new BaseRequestOptions();
        if (req) {
            let params: URLSearchParams = new URLSearchParams();
            params.set('event', req.search);
            options.search = params;
        }
        return options;
    }

}
