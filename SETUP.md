# Guide d'installation

## Table des matieres

- [Developpement local](#developpement-local)
- [Variables d'environnement](#variables-denvironnement)
- [Docker Compose](#docker-compose)
- [Production](#production)

---

## Developpement local

### Prerequis

| Outil | Version minimale |
|-------|-----------------|
| Java | 17 |
| Maven | 3.9 |
| Node | 20 |
| MongoDB | 7 |

### 1. Cloner le depot

```bash
git clone https://github.com/Anasoufkir/smartrh.git
cd smartrh
```

### 2. Configurer les variables d'environnement

```bash
cp .env.example .env
# Editer .env avec vos valeurs
```

### 3. Demarrer MongoDB

```bash
# Avec Docker
docker run -d -p 27017:27017 --name mongo mongo:7

# Ou utiliser votre installation locale
mongod --dbpath /data/db
```

### 4. Demarrer le backend

```bash
cd Back
mvn spring-boot:run
```

L'API est disponible sur http://localhost:8080

### 5. Demarrer le frontend

Dans un second terminal :

```bash
cd front
npm install
npm start
```

L'interface est disponible sur http://localhost:3000

---

## Variables d'environnement

| Variable | Description | Defaut |
|----------|-------------|--------|
| `MONGO_URI` | URI de connexion MongoDB | `mongodb://localhost:27017/smartrhv1` |
| `JWT_SECRET` | Cle de signature JWT (min. 32 caracteres) | Valeur de dev — **a changer** |
| `JWT_EXPIRATION` | Duree de validite du token en ms | `86400000` (24h) |
| `REACT_APP_API_URL` | URL de base de l'API | `http://localhost:8080/api` |

---

## Docker Compose

Demarre la stack complete (MongoDB + Backend + Frontend) :

```bash
cp .env.example .env
docker compose up --build
```

Pour arreter :

```bash
docker compose down
```

Pour arreter et supprimer les volumes :

```bash
docker compose down -v
```

---

## Production

### Configuration recommandee

1. Generer un secret JWT fort :
   ```bash
   openssl rand -base64 48
   ```

2. Mettre a jour `.env` avec la vraie valeur de `JWT_SECRET`

3. Configurer CORS dans `SecurityConfig.java` pour autoriser uniquement votre domaine

4. Placer un reverse proxy (Nginx, Traefik) devant les services pour gerer le TLS

### Build de production

```bash
# Backend
cd Back && mvn package -DskipTests
# Le JAR est dans target/smartrhV1-0.0.1-SNAPSHOT.jar

# Frontend
cd front && npm run build
# Les fichiers sont dans build/
```

### Demarrage avec PM2

```bash
pm2 start "java -jar Back/target/smartrhV1-0.0.1-SNAPSHOT.jar" --name smartrh-api
pm2 serve front/build 3000 --name smartrh-ui --spa
pm2 save
```
