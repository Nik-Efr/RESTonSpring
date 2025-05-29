-- Заполнение таблицы писателей
INSERT INTO tbl_writer (login, password, firstname, lastname) VALUES
('john_doe', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'John', 'Doe'),
('jane_smith', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Jane', 'Smith'),
('bob_wilson', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Bob', 'Wilson'),
('alice_brown', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Alice', 'Brown'),
('mike_johnson', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Mike', 'Johnson'),
('sarah_davis', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Sarah', 'Davis'),
('tom_miller', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Tom', 'Miller'),
('lisa_garcia', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'Lisa', 'Garcia');

-- Заполнение таблицы меток
INSERT INTO tbl_label (name) VALUES
('Java'),
('Spring'),
('Database'),
('Web Development'),
('REST API'),
('Microservices'),
('Docker'),
('PostgreSQL'),
('Testing'),
('Performance'),
('Security'),
('Frontend'),
('Backend'),
('DevOps'),
('Architecture');

-- Заполнение таблицы топиков
INSERT INTO tbl_topic (writer_id, title, content, created, modified) VALUES
(1, 'Getting Started with Spring Boot', 'Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss.', NOW() - INTERVAL '10 days', NOW() - INTERVAL '10 days'),
(2, 'PostgreSQL Performance Optimization', 'PostgreSQL is a powerful, open source object-relational database system with over 30 years of active development that has earned it a strong reputation for reliability, feature robustness, and performance.', NOW() - INTERVAL '8 days', NOW() - INTERVAL '7 days'),
(3, 'Docker Best Practices', 'Docker is a platform for developers and sysadmins to develop, deploy, and run applications with containers. The use of Linux containers to deploy applications is called containerization.', NOW() - INTERVAL '6 days', NOW() - INTERVAL '6 days'),
(4, 'REST API Design Guidelines', 'REST APIs are one of the most common kinds of web services available today. They allow various clients including browser apps to communicate with a server via the REST API.', NOW() - INTERVAL '5 days', NOW() - INTERVAL '4 days'),
(1, 'Microservices Architecture', 'Microservices - also known as the microservice architecture - is an architectural style that structures an application as a collection of services that are highly maintainable and testable.', NOW() - INTERVAL '4 days', NOW() - INTERVAL '3 days'),
(5, 'Java 17 New Features', 'Java 17 is a long-term support (LTS) release that includes many new features and improvements. This article covers the most important changes and how to use them in your projects.', NOW() - INTERVAL '3 days', NOW() - INTERVAL '2 days'),
(6, 'Testing Strategies for Web Applications', 'Testing is a crucial part of software development. This guide covers different testing strategies including unit testing, integration testing, and end-to-end testing.', NOW() - INTERVAL '2 days', NOW() - INTERVAL '1 day'),
(7, 'Security in Modern Web Applications', 'Web application security is a branch of information security that deals specifically with security of websites, web applications and web services.', NOW() - INTERVAL '1 day', NOW() - INTERVAL '1 day'),
(8, 'Frontend Development with React', 'React is a JavaScript library for building user interfaces. It is maintained by Facebook and a community of individual developers and companies.', NOW(), NOW()),
(2, 'DevOps Culture and Practices', 'DevOps is a set of practices that combines software development and IT operations. It aims to shorten the systems development life cycle and provide continuous delivery.', NOW(), NOW());

-- Заполнение связей топиков с метками
INSERT INTO tbl_topic_label (topic_id, label_id) VALUES
-- Spring Boot topic
(1, 1), (1, 2), (1, 4), (1, 13),
-- PostgreSQL topic
(2, 3), (2, 8), (2, 10),
-- Docker topic
(3, 7), (3, 14),
-- REST API topic
(4, 4), (4, 5), (4, 13),
-- Microservices topic
(5, 1), (5, 2), (5, 6), (5, 15),
-- Java 17 topic
(6, 1),
-- Testing topic
(7, 9), (7, 4),
-- Security topic
(8, 11), (8, 4), (8, 13),
-- React topic
(9, 12), (9, 4),
-- DevOps topic
(10, 14), (10, 7);

-- Заполнение таблицы уведомлений
INSERT INTO tbl_notice (topic_id, content) VALUES
(1, 'This topic has been updated with new Spring Boot 3.0 information'),
(2, 'Performance benchmarks have been added to this article'),
(3, 'Docker Compose examples have been included'),
(4, 'OpenAPI specification examples added'),
(5, 'Updated with latest microservices patterns'),
(6, 'Code examples updated for Java 17 syntax'),
(7, 'New testing frameworks comparison added'),
(8, 'OWASP Top 10 security risks updated'),
(9, 'React 18 features and hooks explained'),
(10, 'CI/CD pipeline examples included'),
(1, 'Community feedback incorporated into the guide'),
(2, 'Query optimization techniques expanded'),
(4, 'Error handling best practices added'),
(5, 'Service mesh integration examples provided'),
(7, 'Mock testing strategies detailed');
