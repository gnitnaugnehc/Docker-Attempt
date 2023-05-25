package application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import application.scalar.IDScalar;
import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType idScalarType = IDScalar.create();
    
        return wiringBuilder -> wiringBuilder.scalar(idScalarType);
    }
    
}
