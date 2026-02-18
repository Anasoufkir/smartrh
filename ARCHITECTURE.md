# Architecture

## Vue d'ensemble

SmartRH est une application full-stack organisee en deux projets independants communicant via une API REST.

```
┌─────────────────────────────────────────────────────────┐
│  Client (React 18)                                       │
│  Browser — port 3000                                     │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────────┐  │
│  │  Components │  │  React Router│  │  Axios Client  │  │
│  │  LoginForm  │  │  ProtectedRt │  │  /services/api │  │
│  │  JobOffers  │  │  Routes      │  │  + JWT header  │  │
│  │  Applications│  └──────────────┘  └────────────────┘  │
│  └─────────────┘                                         │
└────────────────────┬────────────────────────────────────┘
                     │ HTTP/JSON — port 8080
┌────────────────────▼────────────────────────────────────┐
│  API Spring Boot 3.2                                     │
│  ┌──────────────────────────────────────────────────┐   │
│  │  SecurityFilterChain                              │   │
│  │  JwtAuthFilter (OncePerRequestFilter)             │   │
│  └──────────────────────────────────────────────────┘   │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐  │
│  │ AuthCtrl    │  │ JobOfferCtrl│  │ ApplicationCtrl  │  │
│  └──────┬──────┘  └──────┬──────┘  └────────┬─────────┘  │
│         │               │                   │            │
│  ┌──────▼──────┐  ┌──────▼──────┐  ┌────────▼─────────┐  │
│  │ AuthService │  │ JobOfferSvc │  │ ApplicationSvc   │  │
│  └──────┬──────┘  └──────┬──────┘  └────────┬─────────┘  │
│         │               │                   │            │
│  ┌──────▼──────────────────────────────────▼─────────┐  │
│  │  Spring Data MongoDB Repositories                  │  │
│  └──────────────────────────┬──────────────────────┘   │
└───────────────────────────────┼────────────────────────┘
                                │ MongoDB Wire Protocol
                     ┌──────────▼──────────┐
                     │  MongoDB 7           │
                     │  Collections:        │
                     │  - users             │
                     │  - job_offers        │
                     │  - applications      │
                     └─────────────────────┘
```

## Couches applicatives

### Backend

| Couche | Package | Responsabilite |
|--------|---------|----------------|
| Controller | `controller/` | Validation des entrees, routing HTTP, formatage des reponses |
| Service | `services/` | Logique metier, regles de validation, orchestration |
| Repository | `repository/` | Acces base de donnees via Spring Data MongoDB |
| Model | `model/` | Entites de domaine avec annotations de validation |
| Security | `security/` | Filtre JWT, generation et validation des tokens |
| Exception | `exception/` | Gestionnaire global d'erreurs HTTP |

### Frontend

| Dossier | Responsabilite |
|---------|----------------|
| `components/` | Composants React presentationnels et conteneurs |
| `services/api.js` | Couche d'abstraction Axios avec injection automatique du token |

## Choix techniques

### JWT stateless
Les tokens JWT sont signes avec HMAC-SHA256. Aucune session n'est stockee cote serveur. Chaque requete est auto-portante, ce qui simplifie la scalabilite horizontale.

### MongoDB
Document model naturellement adapte aux offres d'emploi (description variable, champs optionnels). Spring Data MongoDB elimine le boilerplate des requetes CRUD.

### React SPA
L'application React gere le routage cote client avec React Router v6. Les routes protegees verifient la presence du token avant le rendu.

### Validation en deux couches
- Backend : annotations `@Valid`, `@NotBlank`, `@Email` sur les modeles
- Frontend : attributs HTML5 `required` et gestion des erreurs API

## Securite

- Mots de passe hasches avec BCrypt (facteur 10)
- Tokens JWT expires apres 24h (configurable)
- CORS ouvert en developpement — a restreindre en production
- CSRF desactive (API stateless, pas de cookies de session)
- Variables sensibles injectees via variables d'environnement

## Extension du projet

Pour ajouter une nouvelle fonctionnalite (ex: gestion des entretiens) :
1. Creer le modele dans `model/`
2. Creer le repository dans `repository/`
3. Creer le service dans `services/`
4. Creer le controller dans `controller/`
5. Ajouter les tests unitaires dans `src/test/`
6. Ajouter le composant React dans `front/src/components/`
