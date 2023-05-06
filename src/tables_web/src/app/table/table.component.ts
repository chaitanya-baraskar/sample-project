import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environments";
import {Router} from "@angular/router";

interface TableData {
  id: number;
  sender: string;
  receiver: string;
  totalAmount: number;
  totalAmountPaid: number;
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  tableData: TableData[] = [];


  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.http.get<TableData[]>(environment.API_URL + '/parent?page=0&size=2').subscribe(data => {
      this.tableData = data;
    });
  }

  loadUserDetails(id: number) {
    this.router.navigate(['/transactions', id]);
  }
}
