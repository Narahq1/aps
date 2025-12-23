package com.sga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgaApplication.class, args);
        System.out.println("\n==============================================");
        System.out.println("🚀 Sistema de Gestão Acadêmica (SGA) iniciado!");
        System.out.println("📚 API REST disponível em: http://localhost:8080");
        System.out.println("🗄️  Console H2: http://localhost:8080/h2-console");
        System.out.println("==============================================\n");
    }
}
