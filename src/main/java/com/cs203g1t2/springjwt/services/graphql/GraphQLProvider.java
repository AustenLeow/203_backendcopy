// package com.cs203g1t2.springjwt.services.graphql;

// import graphql.GraphQL;
// import graphql.schema.GraphQLSchema;
// import graphql.schema.idl.RuntimeWiring;
// import graphql.schema.idl.SchemaGenerator;
// import graphql.schema.idl.SchemaParser;
// import graphql.schema.idl.TypeDefinitionRegistry;
// import lombok.*;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.Resource;
// import org.springframework.stereotype.Component;

// import com.cs203g1t2.springjwt.services.ItemService;

// import javax.annotation.PostConstruct;
// import java.io.File;
// import java.io.IOException;

// @Component
// @RequiredArgsConstructor
// public class GraphQLProvider {

//     private final ItemService itemService;

//     @Value("classpath:graphql/schemas.graphql")
//     private Resource resource;

//     @Getter
//     private GraphQL graphQL;

//     @PostConstruct
//     public void loadSchema() throws IOException {
//         File fileSchema = resource.getFile();
//         TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(fileSchema);
//         RuntimeWiring wiring = buildRuntimeWiring();
//         GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
//         graphQL = GraphQL.newGraphQL(schema).build();
//     }

//     private RuntimeWiring buildRuntimeWiring() {
//         return RuntimeWiring.newRuntimeWiring()
//                 .type("Query", typeWiring -> typeWiring
//                         .dataFetcher("items",itemService.getAllItemsByQuery())
//                         .dataFetcher("itemsIds", itemService.getAllItemsByIdsQuery())
//                         .dataFetcher("item", itemService.getItemByQuery())
//                 )
//                 .build();
//     }
// }