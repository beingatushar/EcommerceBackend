package com.beingatushar.ecommercebackend.exception

class ErrorInfo {
    String message

    ErrorInfo() {
        this("Something went wrong")
    }

    ErrorInfo(String message) {
        this.message = message
    }
}
