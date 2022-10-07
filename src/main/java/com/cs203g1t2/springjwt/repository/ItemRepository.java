// package com.cs203g1t2.springjwt.repository;

// import com.cs203g1t2.springjwt.models.Item;
// import com.cs203g1t2.springjwt.repository.projection.*;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface ItemRepository extends JpaRepository<Item, Long> {

    List<ItemProjection> findAllByOrderByIdAsc();

    Boolean existsByItemName(String Item_Name);

    Boolean existsByBrand(String Brand);
    
//     @Query("SELECT item FROM Item item ORDER BY item.id ASC")
//     Page<ItemProjection> findAllByOrderByIdAsc(Pageable pageable);

//     List<Item> findByBrandOrderByPriceDesc(String brand);

//     List<Item> findByIdIn(List<Long> itemsIds);

//     @Query("SELECT item FROM Item item WHERE item.id IN :itemsIds")
//     List<ItemProjection> getItemsByIds(List<Long> itemsIds);

//     @Query("SELECT item FROM Item item " +
//             "WHERE (coalesce(:brands, null) IS NULL OR item.brand IN :brands) " +
//             "AND (coalesce(:priceStart, null) IS NULL OR item.price BETWEEN :priceStart AND :priceEnd) " +
//             "ORDER BY CASE WHEN :sortByPrice = true THEN item.price ELSE -item.price END ASC")
//     Page<ItemProjection> findItemsByFilterParams(
//             List<String> items, 
//             Integer priceStart, 
//             Integer priceEnd, 
//             boolean sortByPrice,
//             Pageable pageable);

//     @Query("SELECT item FROM Item item " +
//             "WHERE UPPER(item.brand) LIKE UPPER(CONCAT('%',:text,'%')) " +
//             "ORDER BY item.price DESC")
//     Page<ItemProjection> findByBrand(String text, Pageable pageable);

//     @Query("SELECT item FROM Item item " +
//             "WHERE UPPER(item.itemName) LIKE UPPER(CONCAT('%',:text,'%')) " +
//             "ORDER BY item.price DESC")
//     Page<ItemProjection> findByItemName(String text, Pageable pageable);

// }