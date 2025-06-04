CREATE DATABASE IF NOT EXISTS taskManager CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE taskManager;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `auth0id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8tdjwoo7it10tld0e4joj88lj` (`auth0id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `training_modules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `training_module_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq607q57pvxrvgsd7vlkty2ubf` (`training_module_id`),
  CONSTRAINT `FKq607q57pvxrvgsd7vlkty2ubf` FOREIGN KEY (`training_module_id`) REFERENCES `training_modules` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `assigned_tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `absolute_priority` int DEFAULT NULL,
  `completed` bit(1) NOT NULL,
  `module_priority` int DEFAULT NULL,
  `task_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKloq44ygkobiox3rh6sxjhb9pq` (`task_id`),
  KEY `FKhvhp9r6kp39s99cwxdkn5jyf2` (`user_id`),
  CONSTRAINT `FKloq44ygkobiox3rh6sxjhb9pq` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `FKhvhp9r6kp39s99cwxdkn5jyf2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `assigned_training_modules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `completed` bit(1) NOT NULL,
  `training_module_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhp3u2b6oe6vk2tdcnqn5e9cge` (`training_module_id`),
  KEY `FKcay34nb1s97hs1a2e8sw5jk7r` (`user_id`),
  CONSTRAINT `FKhp3u2b6oe6vk2tdcnqn5e9cge` FOREIGN KEY (`training_module_id`) REFERENCES `training_modules` (`id`),
  CONSTRAINT `FKcay34nb1s97hs1a2e8sw5jk7r` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Resetear AUTO_INCREMENT a 1 para todas las tablas
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE training_modules AUTO_INCREMENT = 1;
ALTER TABLE tasks AUTO_INCREMENT = 1;
ALTER TABLE assigned_tasks AUTO_INCREMENT = 1;
ALTER TABLE assigned_training_modules AUTO_INCREMENT = 1;

-- Insertar usuarios
INSERT INTO users (`auth0id`, `email`, `role`) VALUES
('auth0|684038aed58aaa532a3aaaf2', 'smonterodelatorre@hotmail.com', 'ADMIN'),
('auth0|6840443be10d750354227739', 'smonterodelatorre@gmail.com', 'USER');

-- Insertar 10 training modules
INSERT INTO training_modules (`description`, `name`) VALUES
('Introduccion a Java para Liferay', 'Java Basico'),
('Construccion y gestion de proyectos con Gradle', 'Gradle'),
('Gestion de proyectos con Maven', 'Maven'),
('Desarrollo con Blade CLI para Liferay', 'Blade CLI'),
('Conceptos fundamentales de Liferay', 'Liferay Core'),
('Uso de Eclipse para desarrollo Liferay', 'Eclipse'),
('Creacion de temas y layouts', 'Temas y Layouts'),
('Desarrollo de portlets con MVC', 'Portlets MVC'),
('Servicios en Liferay y APIs REST', 'Servicios y APIs'),
('Optimizacion y despliegue en Liferay', 'Optimizacion y Despliegue');

-- Módulo 1: 5 originales + 5 nuevas tareas = 10 tareas (Java Básico)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Aprender sintaxis basica de Java', 'Sintaxis Java', 1),
('Comprender tipos de datos y variables', 'Tipos y Variables', 1),
('Manejo de estructuras de control', 'Control de flujo', 1),
('Introduccion a la programacion orientada a objetos', 'POO Basica', 1),
('Ejercicios practicos en Java', 'Practicas Java', 1),
('Manejo de excepciones en Java', 'Excepciones', 1),
('Colecciones y genericos', 'Colecciones', 1),
('Programacion funcional en Java', 'Programacion funcional', 1),
('Trabajo con archivos y streams', 'Archivos y Streams', 1),
('Introduccion a concurrencia y hilos', 'Concurrencia', 1);

-- Módulo 2: 13 originales + 5 nuevas tareas = 18 tareas (Gradle)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Introduccion a Gradle', 'Conceptos basicos de Gradle', 2),
('Instalacion y configuracion de Gradle', 'Configurar Gradle', 2),
('Construccion de proyectos con Gradle', 'Construccion basica', 2),
('Gestion de dependencias', 'Dependencias', 2),
('Uso de plugins en Gradle', 'Plugins Gradle', 2),
('Tareas personalizadas', 'Crear tareas propias', 2),
('Integracion con Liferay', 'Gradle y Liferay', 2),
('Ejecutar tests con Gradle', 'Testing en Gradle', 2),
('Optimizacion de scripts', 'Optimizacion', 2),
('Manejo de perfiles y configuraciones', 'Perfiles de construccion', 2),
('Integracion continua con Gradle', 'CI Gradle', 2),
('Solucion de problemas comunes', 'Debugging Gradle', 2),
('Publicacion de artefactos', 'Publicar builds', 2),
('Configuracion avanzada de tareas', 'Tareas avanzadas', 2),
('Uso de wrappers de Gradle', 'Gradle Wrapper', 2),
('Automatizacion con scripts Groovy', 'Scripts Groovy', 2),
('Integracion con Jenkins', 'Jenkins y Gradle', 2),
('Optimización del build cache', 'Build Cache', 2);

-- Módulo 3: 8 originales + 5 nuevas tareas = 13 tareas (Maven)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Introduccion a Maven', 'Conceptos basicos de Maven', 3),
('Configuracion del POM', 'POM.xml', 3),
('Gestion de dependencias', 'Dependencias en Maven', 3),
('Construccion y empaquetado', 'Build y Package', 3),
('Plugins esenciales', 'Plugins Maven', 3),
('Repositorios y artefactos', 'Repositorios Maven', 3),
('Integracion con Liferay', 'Maven y Liferay', 3),
('Solucion de errores comunes', 'Debugging Maven', 3),
('Perfiles y configuraciones avanzadas', 'Perfiles Maven', 3),
('Creacion de plugins personalizados', 'Plugins personalizados', 3),
('Automatizacion de tareas con Maven', 'Automatizacion', 3),
('Integracion con sistemas CI/CD', 'CI/CD Maven', 3),
('Gestion de versiones y releases', 'Versiones y Releases', 3);

-- Módulo 4: 6 originales + 5 nuevas tareas = 11 tareas (Blade CLI)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Introduccion a Blade CLI', 'Conceptos basicos Blade', 4),
('Instalacion y configuracion', 'Setup Blade', 4),
('Crear un modulo con Blade', 'Crear modulo', 4),
('Desplegar modulos', 'Desplegar Blade', 4),
('Integracion con Liferay Workspace', 'Workspace Blade', 4),
('Solucion de problemas comunes', 'Debug Blade', 4),
('Automatizacion con Blade', 'Automatizacion Blade', 4),
('Uso de plantillas en Blade', 'Plantillas Blade', 4),
('Debugging avanzado', 'Debug avanzado', 4),
('Integracion con otros SDKs', 'Integracion SDK', 4),
('Optimización de build con Blade', 'Optimización Build', 4);

-- Módulo 5: 7 originales + 5 nuevas tareas = 12 tareas (Liferay Core)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Arquitectura de Liferay', 'Arquitectura', 5),
('Configuracion basica', 'Configurar Liferay', 5),
('Usuarios y permisos', 'Gestion usuarios', 5),
('Panel de control y administracion', 'Admin Panel', 5),
('Customizacion basica', 'Personalizar', 5),
('Integracion con otros sistemas', 'Integracion', 5),
('Seguridad en Liferay', 'Seguridad', 5),
('Internacionalizacion y localizacion', 'I18N y L10N', 5),
('Configuracion avanzada de portales', 'Portales avanzados', 5),
('Configuracion de roles y grupos', 'Roles y Grupos', 5),
('Monitoreo y logging', 'Monitoreo', 5),
('Backup y restauracion', 'Backup y Restore', 5);

-- Módulo 6: 4 originales + 5 nuevas tareas = 9 tareas (Eclipse)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Instalacion y configuracion de Eclipse', 'Setup Eclipse', 6),
('Configuracion para desarrollo Liferay', 'Configurar para Liferay', 6),
('Debugging en Eclipse', 'Debug Eclipse', 6),
('Uso de plugins para Liferay', 'Plugins Eclipse', 6),
('Configuracion de entornos de desarrollo', 'Entornos de desarrollo', 6),
('Uso de shortcuts y herramientas', 'Shortcuts Eclipse', 6),
('Integracion con sistemas de control de versiones', 'Control versiones', 6),
('Pruebas y debugging avanzado', 'Testing avanzado', 6),
('Optimización del IDE', 'Optimización IDE', 6);

-- Módulo 7: 5 originales + 5 nuevas tareas = 10 tareas (Temas y Layouts)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Introduccion a temas en Liferay', 'Conceptos Temas', 7),
('Creacion de un tema basico', 'Crear tema', 7),
('Customizacion de layouts', 'Layouts', 7),
('Uso de CSS y JS en temas', 'Estilos y scripts', 7),
('Despliegue de temas', 'Deploy temas', 7),
('Uso avanzado de SASS', 'SASS avanzado', 7),
('Optimización de temas para rendimiento', 'Optimizacion temas', 7),
('Integracion con portlets', 'Integracion portlets', 7),
('Desarrollo responsivo', 'Responsive design', 7),
('Debugging de temas', 'Debug temas', 7);

-- Módulo 8: 9 originales + 5 nuevas tareas = 14 tareas (Portlets MVC)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Introduccion a portlets MVC', 'Conceptos MVC', 8),
('Creacion de portlets basicos', 'Crear portlet', 8),
('Manejo de vistas y controladores', 'Vistas y controladores', 8),
('Comunicacion entre portlets', 'Comunicacion', 8),
('Uso de JSP y servlets', 'JSP y Servlets', 8),
('Servicios backend para portlets', 'Servicios backend', 8),
('Pruebas de portlets', 'Testing portlets', 8),
('Debugging de portlets', 'Debug portlets', 8),
('Despliegue y configuracion', 'Deploy portlets', 8),
('Personalizacion de portlets', 'Personalizacion', 8),
('Manejo de eventos y listeners', 'Eventos y listeners', 8),
('Integracion con bases de datos', 'Integracion BD', 8),
('Seguridad en portlets', 'Seguridad portlets', 8),
('Optimización de portlets', 'Optimizacion portlets', 8);

-- Módulo 9: 3 originales + 5 nuevas tareas = 8 tareas (Servicios y APIs)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Introduccion a servicios en Liferay', 'Conceptos servicios', 9),
('Creacion de APIs REST', 'APIs REST', 9),
('Seguridad en servicios', 'Seguridad servicios', 9),
('Documentacion de APIs', 'Documentacion APIs', 9),
('Versionado de APIs', 'Versionado APIs', 9),
('Manejo de errores y excepciones', 'Errores y excepciones', 9),
('Integracion con OAuth', 'OAuth', 9),
('Testing de servicios', 'Testing servicios', 9);

-- Módulo 10: 4 originales + 5 nuevas tareas = 9 tareas (Optimizacion y Despliegue)
INSERT INTO tasks (`description`, `title`, `training_module_id`) VALUES
('Optimizacion del rendimiento', 'Optimizacion rendimiento', 10),
('Monitorizacion de Liferay', 'Monitorizacion', 10),
('Despliegue en produccion', 'Deploy produccion', 10),
('Automatizacion del despliegue', 'Automatizacion', 10),
('Uso de caches', 'Caches', 10),
('Backup y restauracion', 'Backup y restauracion', 10),
('Seguridad en despliegue', 'Seguridad despliegue', 10),
('Balanceo de carga', 'Balanceo carga', 10),
('Mejores practicas en despliegue', 'Buenas practicas', 10);