import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { HttpLink } from '@apollo/client';
import { APOLLO_OPTIONS, Apollo, ApolloModule } from 'apollo-angular';
import { createApolloOptions } from 'src/config/apollo-config';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductFormComponent } from './components/product-form/product-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductDetailsComponent,
    ProductFormComponent,
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    ApolloModule,
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [
    {
      provide: APOLLO_OPTIONS,
      useFactory: createApolloOptions,
      deps: [HttpLink],
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(apollo: Apollo) {}
}
