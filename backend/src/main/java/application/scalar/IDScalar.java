package application.scalar;

import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class IDScalar {

    private static final Coercing<Long, Long> COERCING = new Coercing<Long, Long>() {
        @Override
        public Long serialize(Object dataFetcherResult) {
            if (dataFetcherResult instanceof Long) {
                return (Long) dataFetcherResult;
            } else if (dataFetcherResult instanceof String) {
                return Long.parseLong((String) dataFetcherResult);
            }
            throw new CoercingSerializeException("Unable to serialize " + dataFetcherResult + " as ID.");
        }

        @Override
        public Long parseValue(Object input) {
            if (input instanceof Long) {
                return (Long) input;
            } else if (input instanceof String) {
                return Long.parseLong((String) input);
            }
            throw new CoercingParseValueException("Unable to parse variable value " + input + " as ID.");
        }

        @Override
        public Long parseLiteral(Object input) {
            if (input instanceof IntValue) {
                return ((IntValue) input).getValue().longValue();
            } else if (input instanceof StringValue) {
                return Long.parseLong(((StringValue) input).getValue());
            }
            throw new CoercingParseLiteralException("Unable to parse literal " + input + " as ID.");
        }
    };

    public static GraphQLScalarType create() {
        return GraphQLScalarType.newScalar()
                .name("ID")
                .description("ID scalar type")
                .coercing(COERCING)
                .build();
    }
}
