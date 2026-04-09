package com.beingatushar.ecommercebackend

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class EcommerceBackendApplication {
    static void main(String[] args) {
        // Load .env file BEFORE Spring starts
        try {
            Dotenv dotenv = Dotenv.load()
            dotenv.entries().each { entry ->
                System.setProperty(entry.key, entry.value)
                println("Loaded: ${entry.key}")
            }
            println("✅ .env file loaded successfully")
        } catch (Exception e) {
            println("⚠️ No .env file found, using system environment variables")
        }

        SpringApplication.run(EcommerceBackendApplication, args)
    }
}