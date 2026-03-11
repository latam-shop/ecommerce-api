# Latam Shop E-commerce API (DDD & TDD) 🛒

Una implementación robusta de un backend para E-commerce utilizando las últimas capacidades de **Java 21** y **Spring Boot 4.0.3**. Este proyecto está diseñado bajo los principios de **Domain-Driven Design (DDD)** para manejar la complejidad del negocio y **Test-Driven Development (TDD)** para garantizar la integridad del código.

## 🚀 Tecnologías

* **Lenguaje:** Java 21 (aprovechando Virtual Threads y Pattern Matching).
* **Framework:** Spring Boot 4.0.3.
* **Persistencia:** Spring Data JPA / PostgreSQL.
* **Validación:** Jakarta Bean Validation.
* **Testing:** JUnit 5, Mockito, AssertJ.

## 🏗️ Arquitectura (DDD)

El proyecto sigue una estructura de **Arquitectura Hexagonal (Puertos y Adaptadores)** para desacoplar la lógica de negocio de la infraestructura:

* **Domain:** Contiene las entidades, objetos de valor (Value Objects) y reglas de negocio puras. Sin dependencias externas.
* **Application:** Casos de uso y servicios de aplicación que orquestan el flujo de datos.
* **Infrastructure:** Implementaciones técnicas (repositorios de base de datos, adaptadores de API externa, configuración de Spring).
* **Interfaces/Web:** Controladores REST y DTOs para la comunicación con el cliente.

## 🧪 Enfoque en Calidad (TDD)

Este repositorio sigue un flujo estrictamente orientado a pruebas:
1.  **Red:** Escritura de tests unitarios/integración antes de la lógica.
2.  **Green:** Implementación mínima para pasar los tests.
3.  **Refactor:** Mejora del código manteniendo la cobertura.

Para ejecutar los tests:
```bash
./mvnw test