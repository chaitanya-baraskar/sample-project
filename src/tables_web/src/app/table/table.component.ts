import {Component, Directive, EventEmitter, Input, OnInit, Output, QueryList, ViewChildren} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";

interface TableData {
  id: number;
  sender: string;
  receiver: string;
  totalAmount: number;
  totalAmountPaid: number;
}

interface ParentResponse {
  data: TableData[];
  totalPages: number,
  totalElements: number,
  currentPage: number,
  pageSize: number,
}


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})


export class TableComponent implements OnInit {
  response = {} as ParentResponse;
  page = 0;
  pageSize = 2;
  collectionSize = 0;
  orderByAsc: boolean = true;
  sortColumn = "id"


  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.getParentInfo(0, 2, this.orderByAsc)
  }

  loadUserDetails(id: number) {
    this.router.navigate(['/transactions', id]);
  }

  refreshParentTable() {
    this.getParentInfo((this.page - 1), this.pageSize, this.orderByAsc)
  }

  getParentInfo(page: number, size: number, orderByAsc: boolean) {
    this.http.get<ParentResponse>(environment.API_URL + '/parent?page=' + page + '&size=' + size + '&orderByAsc=' + orderByAsc).subscribe(data => {
      this.response = data;
      this.collectionSize = data.totalElements
    });
  }

  sortBy() {
    this.orderByAsc = !this.orderByAsc;
    this.getParentInfo((this.page - 1), this.pageSize, this.orderByAsc)
  }
}

