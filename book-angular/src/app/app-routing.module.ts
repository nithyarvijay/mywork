import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { CreateBookComponent } from './create-book/create-book.component';


const routes: Routes = [
  { path: '', redirectTo: 'Books', pathMatch: 'full' },
  { path: 'Books', component: BookListComponent },
  { path: 'Books/add', component: CreateBookComponent },
  { path: 'Books/:id', component: BookDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
