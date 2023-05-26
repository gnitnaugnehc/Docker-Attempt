import { ApolloLink } from '@apollo/client/core';
import { HttpLink } from 'apollo-angular/http';
import { InMemoryCache } from '@apollo/client/core';

export function createApolloOptions(httpLink: HttpLink): any {
  return {
    link: ApolloLink.from([
      httpLink.create({
        uri: 'http://localhost:8080/graphql',
      }),
    ]),
    cache: new InMemoryCache(),
  };
}
