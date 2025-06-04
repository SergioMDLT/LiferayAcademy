# Liferay Academy

Liferay Academy is a modular task and training module assignment system built with a clean Onion architecture backend (Java/Spring Boot) and Angular frontend. It provides user authentication via Auth0 and supports flexible task assignment, training module management, and priority handling.

## Features

- **User Management:** CRUD operations and integration with Auth0 authentication.
- **Task Management:** Create, update, delete, and paginate tasks. Tasks belong to training modules.
- **Training Modules:** Define and manage training modules, which group related tasks.
- **Assignment System:** 
  - Assign tasks to users with absolute and module-relative priorities.
  - Assign entire training modules to users, automatically assigning all associated tasks with priority calculation.
  - Unassign modules and automatically unassign all related tasks with priority rebalancing.
- **Frontend:** Angular SPA consuming backend APIs, featuring:
  - Task assignment forms
  - Pagination and infinite scroll for tasks and assignments
  - Drag & drop task priority reordering
  - Real-time updates and validation

## Architecture

- **Backend:** Onion Architecture with strict separation of layers:
  - **Domain:** Core business models and interfaces.
  - **Application:** Use cases and DTOs.
  - **Infrastructure:** Adapters, repositories, REST controllers, and external service integrations.
- **Authentication:** Uses Auth0, integrated only in the infrastructure layer.
- **Frontend:** Angular application with reactive forms and service-based API communication.

## Technologies

- **Backend:**
  - Java 17+
  - Spring Boot
  - Spring Data JPA with Hibernate
  - PostgreSQL (or any SQL database)
  - Auth0 for authentication
- **Frontend:**
  - Angular 15+
  - RxJS
  - Angular Material (for UI components)
  - SCSS for styling

## Key Implementation Details

- **Pagination & Infinite Scroll:** Backend supports pageable queries. Frontend loads data in pages, implementing infinite scroll and manual pagination.
- **Priority Management:** When assigning tasks, priorities (absolute and relative to module) are automatically calculated and adjusted upon insertions and deletions.
- **Task & Module Assignment:** Assigning a module assigns all its tasks automatically with proper priorities.
- **Secure APIs:** Auth0 tokens validated in infrastructure, no leakage of Auth0 references outside infrastructure layer.
- **Drag & Drop:** Tasks priorities can be reordered via drag & drop on the frontend, persisting changes to backend.
- **Error Handling:** Uses toast notifications to alert the user about errors or success.

## Project Structure

- `/domain` — Core business entities and interfaces  
- `/application` — Use cases, DTOs, and mappers  
- `/infrastructure` — REST controllers, repositories, JPA entities, and adapters  
- `/frontend` — Angular SPA  

## Contributing

Contributions are welcome! Please open issues or PRs following the project's coding standards and architecture guidelines.
