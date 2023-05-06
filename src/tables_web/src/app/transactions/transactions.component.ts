import {Component, OnInit} from '@angular/core';
import {environment} from "../../environments/environments";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";


interface TransactionData {
  id: number;
  sender: string;
  receiver: string;
  totalAmount: number;
  paidAmount: number;
}

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  transactionData: TransactionData[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    console.log("Received ID " + id)
    this.http.get<TransactionData[]>(environment.API_URL + '/parent/' + id + '/installments').subscribe(data => {
      this.transactionData = data;
    });
  }
}
