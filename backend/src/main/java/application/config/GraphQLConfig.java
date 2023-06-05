package application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import application.scalar.TimestampScalar;
import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType timestampScalarType = TimestampScalar.create();

        return wiringBuilder -> wiringBuilder.scalar(timestampScalarType);
    }

}
