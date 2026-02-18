# Contribuer a SmartRH

## Prerequis

- Java 17, Maven 3.9
- Node 20, npm 10
- MongoDB 7 (ou Docker)
- Git

## Workflow

1. Forker le depot
2. Creer une branche depuis `master` : `git checkout -b ma-feature`
3. Developper et tester
4. Pousser et ouvrir une Pull Request

## Format des commits

Les messages de commit suivent ce format :

```
[TAG] description courte en minuscules (max 72 caracteres)
```

Tags autorises :

| Tag | Usage |
|-----|-------|
| `[INIT]` | Commit initial ou creation de module |
| `[ADD]` | Ajout d'une nouvelle fonctionnalite |
| `[IMP]` | Amelioration ou refactoring |
| `[FIX]` | Correction de bug |
| `[CI]` | Pipeline, scripts de build |
| `[CONF]` | Fichiers de configuration |

Exemples corrects :

```
[ADD] endpoint DELETE pour les candidatures
[FIX] correction du filtre JWT sur les routes protegees
[IMP] extraction de la logique de validation dans AuthService
[CI] ajout du job de securite npm audit
```

Exemples incorrects :

```
fix: auth bug          # format Conventional Commits non utilise
Fixed the bug          # pas de tag
[ADD] Added new feature for users that allows them to...  # trop long
```

## Process de Pull Request

- Une PR = une fonctionnalite ou un fix
- Les tests doivent passer (`mvn test` + `npm test`)
- Remplir le template de PR completement
- Au moins un reviewer avant merge

## Tests

```bash
# Backend
cd Back && mvn test

# Frontend
cd front && npm test -- --watchAll=false
```

Toute nouvelle fonctionnalite doit etre accompagnee de tests unitaires.
