package com.beingatushar.ecommercebackend.exception.definations;

class ResourceNotFoundException extends RuntimeException {
    ResourceNotFoundException() {
        super("Resource not found")
    }

    ResourceNotFoundException(String message) {
        super(message)
    }
}
