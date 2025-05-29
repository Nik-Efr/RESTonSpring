1. Проект: REST API на Spring Boot
   Веб-приложение с REST API, построенное на Spring Boot. Демонстрирует создание RESTful сервисов,
   работу с базой данных через Spring Data JPA и современные подходы к разработке backend-приложений.

2. Собрать и запустить
   docker run --name postgres-RESTonSpring -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=distcomp -p 5432:5432 -d postgres:15
   mvn clean package
   java -jar target/rest-on-spring-0.0.1-SNAPSHOT.jar  
   API будет доступно по адресу: http://localhost:24110/api/v1.0

3. Технические особенности
   Использован Spring Boot для разработки REST API 
   Интеграция с PostgreSQL через Spring Data JPA 
   Настроена миграция схемы БД с помощью Liquibase 
   Тестовые данные загружаются автоматически при первом запуске
   Реализованы CRUD операции через RESTful endpoints
