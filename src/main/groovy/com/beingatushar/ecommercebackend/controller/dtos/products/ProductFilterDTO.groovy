package com.beingatushar.ecommercebackend.controller.dtos.products

class ProductFilterDTO {
    String search      // Search in name and description
    String category
    Integer minPrice
    Integer maxPrice
    Integer minStock
    Boolean isFeatured
    String sortBy      // name, price, mrp, stock, createdAt
    String sortDir     // asc, desc
    Integer page = 0   // page number (0-based)
    Integer size = 10  // page size

    // Helper method to check if sort is valid
    boolean isValidSortField() {
        sortBy in ['name', 'price', 'mrp', 'stock', 'createdAt', null]
    }

    // Get sort direction as Spring's Sort.Direction
    String getSortDirection() {
        sortDir?.toLowerCase() == 'desc' ? 'DESC' : 'ASC'
    }
}