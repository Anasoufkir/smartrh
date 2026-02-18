# Changelog

Format base sur [Keep a Changelog](https://keepachangelog.com/fr/1.0.0/).

## [1.1.0] — 2026-03-05

### Ajoute
- Authentification JWT reelle avec JJWT 0.12 (HS256)
- Filtre `JwtAuthFilter` injecte dans la chaine de securite Spring
- Gestionnaire global d'exceptions (`GlobalExceptionHandler`)
- Tests unitaires pour `AuthService`, `JobOfferService`, `ApplicationService`, `JwtUtil`
- Tests de composants React pour `LoginForm` et `JobOffersList`
- Pipeline CI GitHub Actions (lint + test + audit + build)
- Docker Compose avec services mongodb, backend, frontend
- Templates d'issues et de Pull Request GitHub
- Documentation : README, ARCHITECTURE, CONTRIBUTING, SETUP, SECURITY

### Corrige
- Token JWT factice remplace par une generation signee HMAC-SHA256
- Credentials hardcodes supprimes de `application.properties`
- Titre incorrect "Sign-up Smart RH" dans `LoginForm`
- Appel multipart/form-data remplace par JSON dans `submitApplication`
- Methodes `update()` et `delete()` lèvent `ResourceNotFoundException` si la ressource est absente

### Ameliore
- Annotations de validation `@Valid`, `@NotBlank`, `@Email` sur tous les modeles et controllers
- `api.js` utilise `REACT_APP_API_URL` et injecte automatiquement le token Bearer
- `application.properties` utilise des variables d'environnement avec valeurs par defaut

## [1.0.0] — 2025-11-05

### Ajoute
- Structure initiale du projet Spring Boot 3.2 + React 18
- Modeles `User`, `JobOffer`, `Application` avec repositories MongoDB
- Services CRUD pour offres d'emploi et candidatures
- Controllers REST `/api/auth`, `/api/job-offers`, `/api/applications`
- Composants React : `LoginForm`, `SignupForm`, `JobOffersList`, `ApplicationsList`, `CreateJobOfferForm`, `SubmitApplicationForm`
- Navigation et routes proteges avec `ProtectedRoute`
- Encodage des mots de passe avec BCrypt
