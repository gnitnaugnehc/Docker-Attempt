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
              createdAt
              tags {
                id
                name
              }
            }
          }
        `
      })
      .valueChanges.pipe(
        map(result => result.data.getAllProducts)
      );
  }

  getProduct(id: string): Observable<any> {
    return this.apollo
      .watchQuery<any>({
        query: gql`
          query($id: ID!) {
            getProduct(id: $id) {
              id
              name
              description
              price
              createdAt
              tags {
                id
                name
              }
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
          query($tags: [String!]!) {
            searchProductsByTagNames(tags: $tags) {
              id
              name
              description
              price
              createdAt
              tags {
                id
                name
              }
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
          mutation($name: String!, $description: String!, $price: Float!, $tags: [String!]!) {
            createProduct(name: $name, description: $description, price: $price, tags: $tags) {
              id
              name
              description
              price
              createdAt
              tags {
                id
                name
              }
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

  updateProduct(id: string, name: string, description: string, price: number, tags: string[]): Observable<any> {
    return this.apollo
      .mutate<any>({
        mutation: gql`
          mutation($id: ID!, $name: String!, $description: String!, $price: Float!, $tags: [String!]!) {
            updateProduct(id: $id, name: $name, description: $description, price: $price, tags: $tags) {
              id
              name
              description
              price
              createdAt
              tags {
                id
                name
              }
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

  deleteProduct(id: string): Observable<boolean> {
    return this.apollo
      .mutate<any>({
        mutation: gql`
          mutation($id: ID!) {
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
