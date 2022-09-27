// package com.cs203g1t2.springjwt.controllers;

// import java.util.List;

// import org.springframework.data.domain.Pageable;
// import org.springframework.data.web.PageableDefault;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.cs203g1t2.springjwt.dto.*;
// import com.cs203g1t2.springjwt.dto.item.*;
// import com.cs203g1t2.springjwt.mapper.*;
// import com.cs203g1t2.springjwt.services.graphql.GraphQLProvider;

// import graphql.ExecutionResult;
// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/api/v1/items")
// public class ItemController {

//     private final ItemMapper itemMapper;
//     private final GraphQLProvider graphQLProvider;

//     @GetMapping
//     public ResponseEntity<List<ItemResponse>> getAllItems(@PageableDefault(size = 15) Pageable pageable) {
//         HeaderResponse<ItemResponse> response = itemMapper.getAllItems(pageable);
//         return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
//     }

//     @GetMapping("/{itemId}")
//     public ResponseEntity<FullItemResponse> getItemById(@PathVariable Long itemId) {
//         return ResponseEntity.ok(itemMapper.getItemById(itemId));
//     }

//     @PostMapping("/ids")
//     public ResponseEntity<List<ItemResponse>> getItemsByIds(@RequestBody List<Long> itemsIds) {
//         return ResponseEntity.ok(itemMapper.getItemsByIds(itemsIds));
//     }

//     @PostMapping("/search")
//     public ResponseEntity<List<ItemResponse>> findItemsByFilterParams(@RequestBody ItemSearchRequest filter,
//                                                                             @PageableDefault(size = 15) Pageable pageable) {
//         HeaderResponse<ItemResponse> response = itemMapper.findItemsByFilterParams(filter, pageable);
//         return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
//     }


//     @PostMapping("/search/brand")
//     public ResponseEntity<List<ItemResponse>> findByBrand(@RequestBody ItemSearchRequest filter) {
//         return ResponseEntity.ok(itemMapper.findByBrand(filter.getBrand()));
//     }

//     @PostMapping("/search/text")
//     public ResponseEntity<List<ItemResponse>> findByInputText(@RequestBody SearchTypeRequest searchType,
//                                                                  @PageableDefault(size = 15) Pageable pageable) {
//         HeaderResponse<ItemResponse> response = itemMapper.findByInputText(searchType.getSearchType(), searchType.getText(), pageable);
//         return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
//     }

//     @PostMapping("/graphql/ids")
//     public ResponseEntity<ExecutionResult> getItemsByIdsQuery(@RequestBody GraphQLRequest request) {
//         return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
//     }

//     @PostMapping("/graphql/items")
//     public ResponseEntity<ExecutionResult> getAllItemsByQuery(@RequestBody GraphQLRequest request) {
//         return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
//     }

//     @PostMapping("/graphql/item")
//     public ResponseEntity<ExecutionResult> getItemByQuery(@RequestBody GraphQLRequest request) {
//         return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
//     }
// }
