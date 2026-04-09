package com.beingatushar.ecommercebackend.data.repository

import com.beingatushar.ecommercebackend.data.entities.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false")
    Page<ProductEntity> findAllActive(Pageable pageable);

    @Query("""
        SELECT p FROM ProductEntity p 
        WHERE p.isDeleted = false 
        AND (cast(:searchQuery as string) IS NULL 
             OR LOWER(p.name) LIKE LOWER(CONCAT('%', cast(:searchQuery as string), '%')) 
             OR LOWER(p.description) LIKE LOWER(CONCAT('%', cast(:searchQuery as string), '%')))
        AND (:category IS NULL OR p.category = :category)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        AND (:minStock IS NULL OR p.stock >= :minStock)
        AND (:isFeatured IS NULL OR p.isFeatured = :isFeatured)
    """)
    Page<ProductEntity> findProductsWithFilters(
            @Param("searchQuery") String searchQuery,
            @Param("category") String category,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minStock") Integer minStock,
            @Param("isFeatured") Boolean isFeatured,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p.category FROM ProductEntity p WHERE p.isDeleted = false AND p.category IS NOT NULL")
    List<String> findAllCategories();

    @Query("SELECT MIN(p.price) FROM ProductEntity p WHERE p.isDeleted = false")
    Integer getMinPrice();

    @Query("SELECT MAX(p.price) FROM ProductEntity p WHERE p.isDeleted = false")
    Integer getMaxPrice();

    @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false AND p.category = :category")
    Page<ProductEntity> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<ProductEntity> findByPriceRange(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, Pageable pageable);

    @Query("""
        SELECT p FROM ProductEntity p 
        WHERE p.isDeleted = false 
        AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) 
             OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<ProductEntity> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false AND p.isFeatured = true")
    Page<ProductEntity> findFeaturedProducts(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false AND p.stock < :threshold")
    Page<ProductEntity> findLowStockProducts(@Param("threshold") Integer threshold, Pageable pageable);

    @Query("""
        SELECT p FROM ProductEntity p 
        WHERE p.isDeleted = false 
        AND p.category IN :categories
    """)
    Page<ProductEntity> findByCategories(@Param("categories") List<String> categories, Pageable pageable);

    @Query("SELECT p.category, COUNT(p) FROM ProductEntity p WHERE p.isDeleted = false GROUP BY p.category")
    List<Object[]> countProductsByCategory();

    @Override
    @Query("SELECT p FROM ProductEntity p WHERE p.isDeleted = false")
    List<ProductEntity> findAll();

    @Override
    @Query("SELECT p FROM ProductEntity p WHERE p.id = :id AND p.isDeleted = false")
    Optional<ProductEntity> findById(@Param("id") Long id);
}