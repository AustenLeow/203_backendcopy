// package com.cs203g1t2.springjwt.mapper;

// import com.cs203g1t2.springjwt.models.Item;
// import com.cs203g1t2.springjwt.dto.*;
// import com.cs203g1t2.springjwt.dto.item.*;
// import com.cs203g1t2.springjwt.enums.SearchItem;
// import com.cs203g1t2.springjwt.exception.InputFieldException;
// import com.cs203g1t2.springjwt.repository.projection.ItemProjection;
// import com.cs203g1t2.springjwt.services.ItemService;
// import lombok.RequiredArgsConstructor;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Component;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.List;

// @Component
// @RequiredArgsConstructor
// public class ItemMapper {

//     private final CommonMapper commonMapper;
//     private final ItemService itemService;

//     public FullItemResponse getItemById(Long itemId) {
//         return commonMapper.convertToResponse(itemService.getItemById(itemId), FullItemResponse.class);
//     }

//     public List<ItemResponse> getItemsByIds(List<Long> itemsId) {
//         return commonMapper.convertToResponseList(itemService.getItemsByIds(itemsId), ItemResponse.class);
//     }

//     public HeaderResponse<ItemResponse> getAllItems(Pageable pageable) {
//         Page<ItemProjection> items = itemService.getAllItems(pageable);
//         return commonMapper.getHeaderResponse(items.getContent(), items.getTotalPages(), items.getTotalElements(), ItemResponse.class);
//     }

//     public HeaderResponse<ItemResponse> findItemsByFilterParams(ItemSearchRequest filter, Pageable pageable) {
//         Page<ItemProjection> items = itemService.findItemsByFilterParams(filter.getBrands(), 
//                 filter.getPrices(), filter.getSortByPrice(), pageable);
//         return commonMapper.getHeaderResponse(items.getContent(), items.getTotalPages(), items.getTotalElements(), ItemResponse.class);
//     }

//     public List<ItemResponse> findByBrand(String brand) {
//         return commonMapper.convertToResponseList(itemService.findByBrand(brand), ItemResponse.class);
//     }
    
//     public HeaderResponse<ItemResponse> findByInputText(SearchItem searchType, String text, Pageable pageable) {
//         Page<ItemProjection> items = itemService.findByInputText(searchType, text, pageable);
//         return commonMapper.getHeaderResponse(items.getContent(), items.getTotalPages(), items.getTotalElements(), ItemResponse.class);
//     }

//     public FullItemResponse saveItem(ItemRequest itemRequest, MultipartFile file, BindingResult bindingResult) {
//         if (bindingResult.hasErrors()) {
//             throw new InputFieldException(bindingResult);
//         }
//         Item item = commonMapper.convertToEntity(itemRequest, Item.class);
//         return commonMapper.convertToResponse(itemService.saveItem(item, file), FullItemResponse.class);
//     }

//     public String deleteItem(Long itemId) {
//         return itemService.deleteItem(itemId);
//     }
// }
