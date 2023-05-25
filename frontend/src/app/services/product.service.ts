import { Injectable } from '@angular/core';
import { Apollo, gql } from 'apollo-angular';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private apollo: Apollo) { }

  getAllProducts(): Observable<any> {
    return this.apollo
      .watchQuery<any>({
        query: gql`
          query {
            getAllProducts {
              id
              name
              description
              price
              tags
            }
          }
        `
      })
      .valueChanges.pipe(
        map(result => result.data.getAllProducts)
      );
  }

  getProduct(id: number): Observable<any> {
    return this.apollo
      .watchQuery<any>({
        query: gql`
          query($id: Long!) {
            getProduct(id: $id) {
              id
              name
              description
              price
              tags
            }
          }
        `,
        variables: {
          id: id
        }
      })
      .valueChanges.pipe(
        map(result => result.data.getProduct)
      );
  }

  searchProductsByTags(tags: string[]): Observable<any> {
    return this.apollo
      .watchQuery<any>({
        query: gql`
          query($tags: [String]!) {
            searchProductsByTagNames(tags: $tags) {
              id
              name
              description
              price
              tags
            }
          }
        `,
        variables: {
          tags: tags
        }
      })
      .valueChanges.pipe(
        map(result => result.data.searchProductsByTagNames)
      );
  }

  createProduct(name: string, description: string, price: number, tags: string[]): Observable<any> {
    return this.apollo
      .mutate<any>({
        mutation: gql`
          mutation($name: String!, $description: String!, $price: Float!, $tags: [String]!) {
            createProduct(name: $name, description: $description, price: $price, tags: $tags) {
              id
              name
              description
              price
              tags
            }
          }
        `,
        variables: {
          name: name,
          description: description,
          price: price,
          tags: tags
        }
      })
      .pipe(
        map(result => result.data.createProduct)
      );
  }

  updateProduct(id: number, name: string, description: string, price: number, tags: string[]): Observable<any> {
    return this.apollo
      .mutate<any>({
        mutation: gql`
          mutation($id: Long!, $name: String!, $description: String!, $price: Float!, $tags: [String]!) {
            updateProduct(id: $id, name: $name, description: $description, price: $price, tags: $tags) {
              id
              name
              description
              price
              tags
            }
          }
        `,
        variables: {
          id: id,
          name: name,
          description: description,
          price: price,
          tags: tags
        }
      })
      .pipe(
        map(result => result.data.updateProduct)
      );
  }

  deleteProduct(id: number): Observable<boolean> {
    return this.apollo
      .mutate<any>({
        mutation: gql`
          mutation($id: Long!) {
            deleteProduct(id: $id)
          }
        `,
        variables: {
          id: id
        }
      })
      .pipe(
        map(result => result.data.deleteProduct)
      );
  }
}
