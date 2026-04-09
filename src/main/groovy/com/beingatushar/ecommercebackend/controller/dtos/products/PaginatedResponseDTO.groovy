package com.beingatushar.ecommercebackend.controller.dtos.products

class PaginatedResponseDTO<T> {
    List<T> content
    int pageNumber
    int pageSize
    long totalElements
    int totalPages
    boolean last
    boolean first

    PaginatedResponseDTO(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content
        this.pageNumber = pageNumber
        this.pageSize = pageSize
        this.totalElements = totalElements
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize)
        this.last = pageNumber >= totalPages - 1
        this.first = pageNumber == 0
    }
}