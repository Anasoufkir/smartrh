# Politique de securite

## Signaler une vulnerabilite

Si vous decouvrez une vulnerabilite de securite dans SmartRH, merci de ne pas ouvrir une issue publique.

Envoyez un email a **anasoufkir94@gmail.com** avec :

1. Une description detaillee de la vulnerabilite
2. Les etapes pour la reproduire
3. L'impact potentiel
4. Une suggestion de correction si vous en avez une

Vous recevrez une reponse dans les 72 heures. Si la vulnerabilite est confirmee, un correctif sera publie dans les 14 jours.

## Perimetre

Ce qui entre dans le perimetre de securite :
- Contournement de l'authentification JWT
- Injection dans les requetes MongoDB
- Exposition de donnees sensibles via l'API
- Vulnerabilites XSS ou CSRF dans le frontend

## Bonnes pratiques en production

- Changer `JWT_SECRET` par une valeur aleatoire forte (min. 32 caracteres)
- Restreindre `CORS origins` aux domaines autorises
- Activer HTTPS (TLS) devant l'API
- Ne jamais exposer MongoDB directement sur Internet
- Faire des sauvegardes regulieres de la base de donnees
