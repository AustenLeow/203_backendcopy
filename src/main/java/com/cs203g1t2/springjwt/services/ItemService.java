// package com.cs203g1t2.springjwt.services;

// import com.cs203g1t2.springjwt.models.Item;
// import com.cs203g1t2.springjwt.repository.projection.*;
// import com.cs203g1t2.springjwt.enums.*;
// import com.cs203g1t2.springjwt.repository.*;
// import java.util.ArrayList;

// import graphql.schema.DataFetcher;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.List;

// public class ItemService {

//     @Autowired
//     private ItemRepository itemRepository;

//     public List<Item> getItemByLocationSearch( String location){
//         List<Item> items = itemRepository.findAll();
//         List<Item> ret = new ArrayList<Item>();
//         for ( Object item : items){
//             Item theitem = (Item)item;
//             if (theitem.getLocation().equals(location)){
//                 ret.add(theitem);
//             }
//         }
//         return ret;
//     }
// }

//     Item getItemById(Long ItemId);

//     Page<ItemProjection> getAllItems(Pageable pageable);

//     List<ItemProjection> getItemsByIds(List<Long> ItemsId);

//     Page<ItemProjection> findItemsByFilterParams(List<String> brands, List<Integer> prices, 
//                                              boolean sortByPrice, Pageable pageable);

//     List<Item> findByBrand(String brand);

//     Page<ItemProjection> findByInputText(SearchItem searchType, String text, Pageable pageable);

//     Item saveItem(Item Item, MultipartFile file);

//     String deleteItem(Long ItemId);

//     DataFetcher<Item> getItemByQuery();

//     DataFetcher<List<ItemProjection>> getAllItemsByQuery();

//     DataFetcher<List<Item>> getAllItemsByIdsQuery();

