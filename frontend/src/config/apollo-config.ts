import { ApolloLink } from '@apollo/client/core';
import { HttpLink } from 'apollo-angular/http';
import { InMemoryCache } from 'apollo-cache-inmemory';

export function createApolloOptions(httpLink: HttpLink) {
  const http = httpLink.create({ uri: 'http://localhost:8080/graphql' });
  const link = ApolloLink.from([http]);

  return {
    link,
    cache: new InMemoryCache(),
  };
}
