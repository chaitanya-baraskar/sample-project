import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TableComponent} from "./table/table.component";
import {TransactionsComponent} from "./transactions/transactions.component";

const routes: Routes = [
  {path: '', component: TableComponent},
  {path: 'transactions/:id', component: TransactionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
