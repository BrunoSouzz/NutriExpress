# 🥗 NutriExpress - API REST Delivery

API RESTful em Spring Boot desenvolvida para a gestão de delivery de comida saudável. O sistema implementa uma arquitetura em camadas completa (Controller -> Service -> Repository -> Banco de Dados) com persistência em PostgreSQL e tratamento de exceções.

## 🛠️ Tecnologias
* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3+ (Spring Web, Spring Data JPA, Validation)
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Dependências:** Maven[cite: 1]
* **Utilitários:** Lombok

## 📁 Estrutura de Pastas
```text
src/main/java/com/nutriexpress/backend/
├── controller/   # Endpoints REST (@RestController)
├── dto/          # Java Records (Request / Response)
├── exception/    # Handler global e exceções personalizadas
├── model/        # Entidades JPA (@Entity)
├── repository/   # Interfaces Spring Data JPA
└── service/      # Regras de negócio e conversões (toEntity / toDTO)