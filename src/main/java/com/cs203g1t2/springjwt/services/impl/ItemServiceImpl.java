// package com.cs203g1t2.springjwt.services.impl;

// import com.cs203g1t2.springjwt.models.*;
// import com.cs203g1t2.springjwt.enums.SearchItem;
// import com.cs203g1t2.springjwt.exception.ApiRequestException;
// import com.cs203g1t2.springjwt.repository.ItemRepository;
// import com.cs203g1t2.springjwt.repository.projection.ItemProjection;
// import com.cs203g1t2.springjwt.services.ItemService;

// import graphql.schema.DataFetcher;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;

// @Service
// @RequiredArgsConstructor
// public class ItemServiceImpl implements ItemService {

//     private final ItemRepository itemRepository;


//     @Override
//     public Item getItemById(Long itemId) {
//         return itemRepository.findById(itemId)
//                 .orElseThrow(() -> new ApiRequestException("Item not found.", HttpStatus.NOT_FOUND));
//     }

//     @Override
//     public Page<ItemProjection> getAllItems(Pageable pageable) {
//         return itemRepository.findAllByOrderByIdAsc(pageable);
//     }

//     @Override
//     public List<ItemProjection> getItemsByIds(List<Long> itemsId) {
//         return itemRepository.getItemsByIds(itemsId);
//     }

//     @Override
//     public Page<ItemProjection> findItemsByFilterParams(List<String> brands, List<Integer> prices, 
//                                                     boolean sortByPrice, Pageable pageable) {
//         return itemRepository.findItemsByFilterParams(brands, prices.get(0), prices.get(1), sortByPrice, pageable);
//     }

//     @Override
//     public List<Item> findByBrand(String brand) {
//         return itemRepository.findByBrandOrderByPriceDesc(brand);
//     }

//     @Override
//     public Page<ItemProjection> findByInputText(SearchItem searchType, String text, Pageable pageable) {
//         if (searchType.equals(SearchItem.BRAND)) {
//             return itemRepository.findByBrand(text, pageable);
//         } 
//             return itemRepository.findByItemName(text, pageable);
        
//     }

//     @Override
//     @Transactional
//     public Item saveItem(Item item, MultipartFile multipartFile) {
        
//         return itemRepository.save(item);
//     }

 
//     @Override
//     @Transactional
//     public String deleteItem(Long itemId) {
//         Item item = itemRepository.findById(itemId)
//                 .orElseThrow(() -> new ApiRequestException("Item not found.", HttpStatus.NOT_FOUND));
//                 itemRepository.delete(item);
//         return "Item deleted successfully";
//     }

//     @Override
//     public DataFetcher<Item> getItemByQuery() {
//         return dataFetchingEnvironment -> {
//             Long itemId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
//             return itemRepository.findById(itemId).get();
//         };
//     }

//     @Override
//     public DataFetcher<List<ItemProjection>> getAllItemsByQuery() {
//         return dataFetchingEnvironment -> itemRepository.findAllByOrderByIdAsc();
//     }

//     @Override
//     public DataFetcher<List<Item>> getAllItemsByIdsQuery() {
//         return dataFetchingEnvironment -> {
//             List<String> objects = dataFetchingEnvironment.getArgument("ids");
//             List<Long> itemsId = objects.stream()
//                     .map(Long::parseLong)
//                     .collect(Collectors.toList());
//             return itemRepository.findByIdIn(itemsId);
//         };
//     }
// }
