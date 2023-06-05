package application.scalar;

import java.time.LocalDateTime;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class TimestampScalar {

    private static final Coercing<LocalDateTime, String> COERCING = new Coercing<LocalDateTime, String>() {
        @Override
        public String serialize(Object dataFetcherResult) {
            if (dataFetcherResult instanceof LocalDateTime) {
                return ((LocalDateTime) dataFetcherResult).toString();
            } else if (dataFetcherResult instanceof String) {
                return (String) dataFetcherResult;
            }
            throw new CoercingSerializeException("Unable to serialize " + dataFetcherResult + " as Timestamp.");
        }

        @Override
        public LocalDateTime parseValue(Object input) {
            if (input instanceof String) {
                return LocalDateTime.parse((String) input);
            }
            throw new CoercingParseValueException("Unable to parse variable value " + input + " as Timestamp.");
        }

        @Override
        public LocalDateTime parseLiteral(Object input) {
            if (input instanceof StringValue) {
                String value = ((StringValue) input).getValue();
                return LocalDateTime.parse(value);
            }
            throw new CoercingParseLiteralException("Unable to parse literal " + input + " as Timestamp.");
        }
    };

    public static GraphQLScalarType create() {
        return GraphQLScalarType.newScalar()
                .name("Timestamp")
                .description("Timestamp scalar type")
                .coercing(COERCING)
                .build();
    }
}
