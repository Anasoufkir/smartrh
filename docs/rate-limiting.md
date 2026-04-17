# Rate Limiting

## Configuration

Les endpoints d'authentification sont protégés contre les attaques brute-force.

| Endpoint | Limite | Fenêtre |
|----------|--------|---------|
| `POST /api/auth/login` | 5 requêtes | 1 minute |
| `POST /api/auth/signup` | 3 requêtes | 1 minute |

## Variables d'environnement

```env
RATE_LIMIT_LOGIN=5
RATE_LIMIT_SIGNUP=3
RATE_LIMIT_WINDOW_SECONDS=60
```

## Réponse en cas de dépassement

```json
HTTP 429 Too Many Requests
{
  "error": "Too many requests. Please try again later.",
  "retryAfter": 45
}
```
