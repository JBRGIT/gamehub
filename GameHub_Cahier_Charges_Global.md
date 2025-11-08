# 🎮 GAMEHUB - CAHIER DES CHARGES GLOBAL
## Plateforme de Gestion de Bibliothèque de Jeux Vidéo

---

**Version** : 1.0  
**Date** : Octobre 2025  
**Durée** : 24 semaines (6 modules de 4 semaines)  
**Format** : Alternance (1 semaine cours + 3 semaines entreprise)  
**Public** : Étudiants 18-25 ans en formation Spring Boot

---

## 📋 TABLE DES MATIÈRES

1. [Vue d'Ensemble du Projet](#vue-densemble)
2. [Objectifs Pédagogiques](#objectifs-pédagogiques)
3. [Description Fonctionnelle](#description-fonctionnelle)
4. [Modèle de Données Global](#modèle-de-données)
5. [Architecture Technique](#architecture-technique)
6. [Progression par Modules](#progression-modules)
7. [Stack Technique](#stack-technique)
8. [Livrables Attendus](#livrables)
9. [Critères d'Évaluation](#evaluation)

---

## 1. VUE D'ENSEMBLE DU PROJET {#vue-densemble}

### 1.1 Contexte

**GameHub** est une plateforme communautaire de gestion de bibliothèque de jeux vidéo, inspirée de **Steam**, **IGN** et **Backloggd**. Elle permet aux joueurs passionnés de centraliser et gérer leur collection de jeux vidéo tout en interagissant avec une communauté active.

### 1.2 Problématique

Les gamers utilisent aujourd'hui de nombreuses plateformes différentes (Steam, Epic Games, PlayStation, Xbox, Nintendo Switch, etc.) et perdent la vue d'ensemble de leur collection. GameHub répond à ce besoin en proposant :

- Une **bibliothèque unifiée** de tous leurs jeux
- Un **suivi des succès** (achievements) débloqués
- Des **critiques et notes** pour aider la communauté
- Des **tournois** pour la dimension compétitive
- Des **statistiques** sur leur activité gaming

### 1.3 Valeur Ajoutée

✅ **Pour les gamers** :
- Vue centralisée de leur collection
- Suivi de progression (achievements, temps de jeu)
- Découverte de nouveaux jeux via reviews
- Participation à des tournois

✅ **Pour la communauté** :
- Partage d'avis et critiques
- Organisation de tournois
- Classements et statistiques

✅ **Pour l'apprentissage** :
- Projet complet et moderne
- Toutes les facettes de Spring Boot
- Architecture évolutive (monolithe → microservices)
- Cas d'usage réels et concrets

---

## 2. OBJECTIFS PÉDAGOGIQUES {#objectifs-pédagogiques}

### 2.1 Objectifs Globaux

Au terme de ce projet de 24 semaines, les étudiants seront capables de :

✅ **Concevoir et développer** une application Spring Boot professionnelle complète
✅ **Modéliser** des relations de base de données complexes avec JPA
✅ **Implémenter** une architecture en couches propre et maintenable
✅ **Tester** rigoureusement leur code (unitaire, intégration, end-to-end)
✅ **Sécuriser** une API REST avec JWT et Spring Security
✅ **Versionner** une base de données avec Liquibase
✅ **Découper** une application en microservices communicants
✅ **Déployer** une architecture distribuée avec Docker

### 2.2 Compétences Techniques Visées

#### Backend
- ✅ Spring Boot (Core, Web, Data JPA, Security)
- ✅ Architecture REST API
- ✅ Gestion des erreurs et validation
- ✅ Mapping Entity ↔ DTO (MapStruct)
- ✅ Tests unitaires et intégration (JUnit, Mockito)

#### Base de Données
- ✅ Modélisation relationnelle complexe
- ✅ JPA / Hibernate (relations, queries JPQL)
- ✅ Optimisation des requêtes (N+1, projections)
- ✅ Migrations avec Liquibase
- ✅ PostgreSQL

#### Sécurité
- ✅ Authentification JWT
- ✅ Autorizations basées sur rôles
- ✅ Hashage des mots de passe (BCrypt)
- ✅ Protection des endpoints

#### Architecture
- ✅ Architecture en couches (Controller → Service → Repository)
- ✅ Principes SOLID
- ✅ Design patterns (DTO, Mapper, Repository)
- ✅ Microservices (Service Discovery, API Gateway, Communication inter-services)

#### Outillage
- ✅ Maven
- ✅ Git / GitHub
- ✅ Swagger / OpenAPI
- ✅ Postman / Insomnia
- ✅ Docker / Docker Compose
- ✅ IntelliJ IDEA + JPA Buddy

---

## 3. DESCRIPTION FONCTIONNELLE {#description-fonctionnelle}

### 3.1 Acteurs

#### 🎮 GAMER (Utilisateur Standard)
**Rôle** : Joueur utilisant la plateforme pour gérer sa collection

**Permissions** :
- Consulter le catalogue de jeux
- Gérer sa bibliothèque personnelle
- Débloquer et suivre ses achievements
- Publier des reviews (notes et critiques)
- S'inscrire à des tournois
- Modifier son profil

#### 🛡️ MODERATOR (Modérateur)
**Rôle** : Modérateur de la communauté

**Permissions** :
- Toutes les permissions GAMER
- Créer et gérer des tournois
- Modérer les reviews (supprimer si inappropriées)
- Bannir temporairement des utilisateurs

#### 👑 ADMIN (Administrateur)
**Rôle** : Administrateur de la plateforme

**Permissions** :
- Toutes les permissions MODERATOR
- Gérer le catalogue (jeux, développeurs, plateformes, genres)
- Promouvoir/rétrograder des utilisateurs
- Accès aux statistiques globales

### 3.2 Fonctionnalités Principales

#### 📚 Gestion du Catalogue

**F1 - Développeurs (Developers)**
- Créer, consulter, modifier, supprimer des développeurs
- Associer des jeux à des développeurs
- Rechercher des développeurs

**F2 - Jeux (Games)**
- Créer, consulter, modifier, supprimer des jeux
- Associer jeux ↔ développeurs, plateformes, genres
- Rechercher des jeux (titre, plateforme, genre, prix)
- Filtrer et trier

**F3 - Plateformes (Platforms)**
- Gérer les plateformes (PC, PlayStation, Xbox, Nintendo, Mobile)
- Associer jeux ↔ plateformes

**F4 - Genres (Genres)**
- Gérer les genres (Action, RPG, Strategy, Adventure, etc.)
- Associer jeux ↔ genres

#### 👤 Gestion des Utilisateurs

**F5 - Authentification**
- Inscription avec email et mot de passe
- Connexion avec JWT
- Refresh token
- Modification de profil
- Changement de mot de passe

**F6 - Profil Utilisateur**
- Avatar, bio, gamer tag
- Niveau et XP
- Statistiques (jeux possédés, achievements débloqués, temps de jeu total)

#### 📖 Bibliothèque Personnelle (Library)

**F7 - Gestion de la Bibliothèque**
- Ajouter un jeu à sa bibliothèque
- Statuts : Wishlist, Owned, Playing, Completed, Abandoned
- Suivre le temps de jeu (playtime)
- Noter personnellement un jeu
- Date d'achat et de complétion
- Filtrer sa bibliothèque par statut

**F8 - Statistiques Bibliothèque**
- Nombre de jeux par statut
- Temps de jeu total
- Jeux complétés vs possédés (taux de complétion)
- Répartition par genre/plateforme

#### 🏆 Achievements (Succès)

**F9 - Gestion des Achievements**
- Liste des achievements par jeu
- Débloquer un achievement
- Suivre sa progression (X/Y achievements débloqués)
- Achievements rares (< 10% des joueurs)
- Points d'achievement

**F10 - Statistiques Achievements**
- Nombre total d'achievements débloqués
- Pourcentage de complétion par jeu
- Achievements les plus rares possédés

#### ⭐ Reviews (Critiques)

**F11 - Publication de Reviews**
- Noter un jeu (0-10)
- Rédiger une critique (titre + contenu)
- Like/Dislike des reviews
- Modifier/Supprimer sa review

**F12 - Consultation des Reviews**
- Reviews par jeu (triées par note, date, likes)
- Reviews d'un utilisateur
- Note moyenne d'un jeu (agrégation)
- Filtrer par note

#### 🎯 Tournaments (Tournois)

**F13 - Gestion des Tournois**
- Créer un tournoi (nom, jeu, dates, prize pool)
- S'inscrire / Se désinscrire d'un tournoi
- Nombre max de participants
- Statuts : Upcoming, Ongoing, Completed, Cancelled
- Désigner le vainqueur

**F14 - Consultation des Tournois**
- Liste des tournois (filtrer par statut, jeu)
- Détail d'un tournoi (participants, dates, prix)
- Tournois auxquels un utilisateur participe
- Historique des victoires

---

## 4. MODÈLE DE DONNÉES GLOBAL {#modèle-de-données}

### 4.1 Schéma Relationnel Complet

```
┌──────────────┐         ┌─────────────┐         ┌────────────┐
│  Developer   │────1:N──│    Game     │────N:N──│  Platform  │
└──────────────┘         └─────────────┘         └────────────┘
                               │                        
                              N:N                       
                               │                        
                          ┌────────┐                    
                          │  Genre │                    
                          └────────┘                    
                                                        
┌───────────┐         ┌──────────────┐         ┌─────────────┐
│   User    │────1:N──│   Library    │────N:1──│    Game     │
└───────────┘         └──────────────┘         └─────────────┘
     │                                               │
     │                                               │
    1:N                                             1:N
     │                                               │
┌───────────┐                                 ┌──────────────┐
│  Review   │─────────────N:1─────────────────│     Game     │
└───────────┘                                 └──────────────┘
                                                     │
┌────────────────┐                                  1:N
│  Achievement   │──────────────────────────────────┘
└────────────────┘                                   
     │                                               
    N:N (user_achievements)                         
     │                                               
┌───────────┐                                        
│   User    │                                        
└───────────┘                                        
     │
    N:N (tournament_participants)
     │
┌────────────┐         ┌─────────────┐
│ Tournament │────N:1──│    Game     │
└────────────┘         └─────────────┘
     │
     │ (winner_id)
     └────N:1──→ User
```

### 4.2 Entités Principales

#### Developer (Développeur)
```
- id (PK)
- name (UNIQUE, NOT NULL)
- country
- founded_year
- logo_url
- website
- description
- created_at, updated_at
```

#### Game (Jeu)
```
- id (PK)
- title (NOT NULL)
- description
- release_date
- publisher
- cover_url
- price
- average_rating (calculé depuis reviews)
- developer_id (FK)
- created_at, updated_at
```

#### Platform (Plateforme)
```
- id (PK)
- name (UNIQUE, NOT NULL)
- type (ENUM: PC, PLAYSTATION, XBOX, NINTENDO, MOBILE)
- manufacturer
- release_date
- logo_url
```

#### Genre (Genre)
```
- id (PK)
- name (UNIQUE, NOT NULL)
- description
```

#### User (Utilisateur)
```
- id (PK)
- username (UNIQUE, NOT NULL)
- email (UNIQUE, NOT NULL)
- password (BCrypt hash)
- role (ENUM: GAMER, MODERATOR, ADMIN)
- avatar
- gamer_tag
- level
- xp
- bio
- enabled, account_non_expired, account_non_locked, credentials_non_expired
- member_since
```

#### Library (Bibliothèque)
```
- id (PK)
- user_id (FK)
- game_id (FK)
- status (ENUM: WISHLIST, OWNED, PLAYING, COMPLETED, ABANDONED)
- purchase_date
- playtime (en heures)
- completed_date
- personal_rating
- added_at, updated_at
- UNIQUE(user_id, game_id)
```

#### Achievement (Succès)
```
- id (PK)
- title (NOT NULL)
- description
- points
- game_id (FK)
- icon_url
- completion_rate (% joueurs ayant débloqué)
```

#### Review (Critique)
```
- id (PK)
- user_id (FK)
- game_id (FK)
- rating (0-10, NOT NULL)
- title
- content
- likes, dislikes
- created_at, updated_at
- UNIQUE(user_id, game_id)
```

#### Tournament (Tournoi)
```
- id (PK)
- name (NOT NULL)
- description
- game_id (FK)
- start_date, end_date
- prize_pool
- status (ENUM: UPCOMING, ONGOING, COMPLETED, CANCELLED)
- max_participants
- winner_id (FK → User)
- created_at
```

### 4.3 Tables de Jointure

#### game_platforms
```
- game_id (FK)
- platform_id (FK)
- PRIMARY KEY (game_id, platform_id)
```

#### game_genres
```
- game_id (FK)
- genre_id (FK)
- PRIMARY KEY (game_id, genre_id)
```

#### user_achievements
```
- user_id (FK)
- achievement_id (FK)
- unlocked_at
- PRIMARY KEY (user_id, achievement_id)
```

#### tournament_participants
```
- tournament_id (FK)
- user_id (FK)
- joined_at
- PRIMARY KEY (tournament_id, user_id)
```

---

## 5. ARCHITECTURE TECHNIQUE {#architecture-technique}

### 5.1 Architecture en Couches (Modules 1-5)

```
┌─────────────────────────────────────────────────┐
│           PRESENTATION LAYER                    │
│         REST Controllers (@RestController)      │
│         - GameController                        │
│         - UserController                        │
│         - LibraryController                     │
│         - etc.                                  │
└─────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────┐
│              DTO LAYER                          │
│         Request DTOs / Response DTOs            │
│         - GameCreateDTO, GameDTO                │
│         - LoginRequestDTO, AuthResponseDTO      │
└─────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────┐
│            SERVICE LAYER                        │
│         Business Logic (@Service)               │
│         - GameService                           │
│         - UserService                           │
│         - LibraryService                        │
└─────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────┐
│          PERSISTENCE LAYER                      │
│         Repositories (@Repository)              │
│         - GameRepository (JpaRepository)        │
│         - UserRepository                        │
└─────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────┐
│            DATABASE LAYER                       │
│            PostgreSQL                           │
└─────────────────────────────────────────────────┘
```

### 5.2 Architecture Microservices (Module 6)

```
                    ┌───────────────────┐
                    │   Client (Web)    │
                    └─────────┬─────────┘
                              │
                    ┌─────────▼─────────┐
                    │   API Gateway     │
                    │   (Port 8080)     │
                    └─────────┬─────────┘
                              │
                    ┌─────────▼─────────┐
                    │  Eureka Server    │
                    │   (Port 8761)     │
                    └─────────┬─────────┘
                              │
          ┌───────────────────┼───────────────────┐
          │                   │                   │
    ┌─────▼──────┐      ┌─────▼──────┐     ┌─────▼──────┐
    │   Game     │      │    User    │     │ Community  │
    │  Service   │      │  Service   │     │  Service   │
    │ (Port 8081)│      │ (Port 8082)│     │ (Port 8083)│
    └─────┬──────┘      └─────┬──────┘     └─────┬──────┘
          │                   │                   │
    ┌─────▼──────┐      ┌─────▼──────┐     ┌─────▼──────┐
    │  game_db   │      │  user_db   │     │community_db│
    │ PostgreSQL │      │ PostgreSQL │     │ PostgreSQL │
    └────────────┘      └────────────┘     └────────────┘
```

**Game Service** : Games, Developers, Platforms, Genres  
**User Service** : Auth, Users, Library, Achievements  
**Community Service** : Reviews, Tournaments

### 5.3 Structure des Packages

```
com.gamehub/
├── config/                     # Configurations
│   ├── SecurityConfig.java
│   ├── OpenApiConfig.java
│   └── MapperConfig.java
│
├── controller/                 # REST Controllers
│   ├── GameController.java
│   ├── DeveloperController.java
│   ├── UserController.java
│   ├── LibraryController.java
│   ├── AchievementController.java
│   ├── ReviewController.java
│   └── TournamentController.java
│
├── dto/                        # Data Transfer Objects
│   ├── request/
│   │   ├── GameCreateDTO.java
│   │   ├── RegisterRequestDTO.java
│   │   └── ...
│   └── response/
│       ├── GameDTO.java
│       ├── AuthResponseDTO.java
│       └── ...
│
├── entity/                     # JPA Entities
│   ├── Developer.java
│   ├── Game.java
│   ├── Platform.java
│   ├── Genre.java
│   ├── User.java
│   ├── Library.java
│   ├── Achievement.java
│   ├── Review.java
│   └── Tournament.java
│
├── mapper/                     # MapStruct Mappers
│   ├── GameMapper.java
│   ├── UserMapper.java
│   └── ...
│
├── repository/                 # Spring Data Repositories
│   ├── GameRepository.java
│   ├── UserRepository.java
│   └── ...
│
├── service/                    # Business Logic
│   ├── GameService.java
│   ├── UserService.java
│   ├── AuthService.java
│   └── ...
│
├── security/                   # Security Components
│   ├── JwtUtil.java
│   ├── JwtAuthenticationFilter.java
│   └── UserDetailsServiceImpl.java
│
├── exception/                  # Exception Handling
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── DuplicateResourceException.java
│   └── BusinessException.java
│
└── GameHubApplication.java     # Main Application
```

---

## 6. PROGRESSION PAR MODULES {#progression-modules}

### MODULE 1 - Base Spring Boot & Bonnes Pratiques (Semaines 1-4)

**Objectif** : Poser les fondations du projet

**Fonctionnalités** :
- ✅ Développeurs (Developers) - CRUD complet
- ✅ Jeux (Games) - CRUD complet
- ✅ Relation Developer → Games (OneToMany)

**Compétences** :
- Architecture en couches
- DTOs et Mappers (MapStruct)
- Validation des données
- Gestion d'erreurs centralisée
- Configuration multi-environnements
- Documentation Swagger

**Livrables** :
- API REST fonctionnelle
- Base de données PostgreSQL
- Documentation Swagger
- Collection Postman

---

### MODULE 2 - Tests Unitaires & Intégration (Semaines 5-8)

**Objectif** : Sécuriser le code avec des tests

**Nouvelles Fonctionnalités** :
- ✅ Plateformes (Platforms) - CRUD
- ✅ Relation Games ↔ Platforms (ManyToMany)

**Compétences** :
- Tests unitaires (JUnit 5 + Mockito)
- Tests d'intégration (MockMvc)
- Tests repository (@DataJpaTest)
- Coverage de code (JaCoCo)

**Livrables** :
- Suite de tests complète (coverage > 80%)
- Rapport JaCoCo
- Tests automatisés

---

### MODULE 3 - JPA Buddy & Relations Complexes (Semaines 9-12)

**Objectif** : Modéliser des relations JPA avancées

**Nouvelles Fonctionnalités** :
- ✅ Utilisateurs (Users)
- ✅ Bibliothèque (Library)
- ✅ Achievements
- ✅ Relations complexes

**Compétences** :
- JPA Buddy (plugin IntelliJ)
- Relations bidirectionnelles
- Queries JPQL avancées
- Projections
- Optimisation (N+1, Lazy/Eager)

**Livrables** :
- Modèle de données complet
- Diagramme ER
- Queries optimisées

---

### MODULE 4 - Liquibase & Migrations (Semaines 13-16)

**Objectif** : Versionner la base de données

**Nouvelles Fonctionnalités** :
- ✅ Genres
- ✅ Reviews (Critiques)
- ✅ Tournaments (Tournois)

**Compétences** :
- Liquibase (changelogs XML)
- Migrations de BDD
- Rollbacks
- Seed data
- Contraintes et indexes

**Livrables** :
- Changelogs Liquibase
- Scripts de migration
- Documentation des migrations

---

### MODULE 5 - Spring Security & JWT (Semaines 17-20)

**Objectif** : Sécuriser l'application

**Nouvelles Fonctionnalités** :
- ✅ Authentification JWT
- ✅ Système de rôles (GAMER, MODERATOR, ADMIN)
- ✅ Permissions

**Compétences** :
- Spring Security
- JWT (génération, validation)
- BCrypt (hashage mots de passe)
- Autorizations (@PreAuthorize)
- Gestion des tokens

**Livrables** :
- Authentification complète
- Endpoints sécurisés
- Tests de sécurité

---

### MODULE 6 - Architecture Microservices (Semaines 21-24)

**Objectif** : Découper en microservices

**Architecture** :
- ✅ 3 microservices (Game, User, Community)
- ✅ Eureka Server (Service Discovery)
- ✅ API Gateway
- ✅ Communication Feign
- ✅ Circuit Breaker (Resilience4j)

**Compétences** :
- Spring Cloud
- Service Discovery
- API Gateway
- Communication inter-services
- Résilience
- Docker Compose

**Livrables** :
- Architecture microservices fonctionnelle
- Docker Compose
- Documentation architecture

---

## 7. STACK TECHNIQUE {#stack-technique}

### 7.1 Backend

```yaml
Framework: Spring Boot 3.2+
Language: Java 17 ou 21
Build Tool: Maven 3.9+
```

### 7.2 Base de Données

```yaml
Primary Database: PostgreSQL 15+
Test Database: H2 (in-memory)
ORM: Spring Data JPA + Hibernate 6+
Migrations: Liquibase 4.24+
```

### 7.3 Sécurité

```yaml
Security Framework: Spring Security 6+
Authentication: JWT (jjwt 0.12+)
Password Encoding: BCrypt
```

### 7.4 Tests

```yaml
Unit Testing: JUnit 5
Mocking: Mockito
Assertions: AssertJ
Integration: MockMvc, @DataJpaTest
Coverage: JaCoCo
```

### 7.5 Documentation

```yaml
API Documentation: SpringDoc OpenAPI 3
Interactive UI: Swagger UI
API Testing: Postman / Insomnia
```

### 7.6 Outils

```yaml
IDE: IntelliJ IDEA + JPA Buddy
DTO Mapping: MapStruct
Boilerplate Reduction: Lombok
Version Control: Git / GitHub
```

### 7.7 Microservices (Module 6)

```yaml
Service Discovery: Spring Cloud Netflix Eureka
API Gateway: Spring Cloud Gateway
HTTP Client: OpenFeign
Resilience: Resilience4j (Circuit Breaker)
Containerization: Docker + Docker Compose
```

### 7.8 Optionnel (Bonus)

```yaml
Monitoring: Spring Boot Actuator
Metrics: Prometheus + Grafana
Distributed Tracing: Sleuth + Zipkin
Config Management: Spring Cloud Config
```

---

## 8. LIVRABLES ATTENDUS {#livrables}

### 8.1 Par Module

#### Code Source
- ✅ Repository Git avec historique de commits
- ✅ Code propre et commenté
- ✅ Respect des conventions de nommage
- ✅ Structure de packages organisée

#### Documentation
- ✅ README.md complet
  - Description du projet
  - Instructions d'installation
  - Configuration de la BDD
  - Lancement de l'application
  - Accès Swagger
- ✅ Documentation technique (architecture, choix techniques)
- ✅ Diagrammes (ER, architecture)

#### Base de Données
- ✅ Scripts SQL de création
- ✅ Données de test (seed data)
- ✅ Migrations Liquibase (à partir du Module 4)

#### Tests
- ✅ Tests unitaires
- ✅ Tests d'intégration
- ✅ Rapport de coverage
- ✅ Tests passants (build Maven réussi)

#### API
- ✅ Swagger accessible
- ✅ Collection Postman/Insomnia
- ✅ Exemples de requêtes
- ✅ Gestion d'erreurs documentée

### 8.2 Livrable Final (Fin Module 6)

#### Application Complète
- ✅ Monolithe fonctionnel (Modules 1-5)
- ✅ Architecture microservices (Module 6)
- ✅ Docker Compose pour déploiement
- ✅ Tests end-to-end

#### Documentation Finale
- ✅ Rapport de projet complet
- ✅ Guide d'installation et déploiement
- ✅ Documentation API complète
- ✅ Présentation PowerPoint/PDF

#### Présentation
- ✅ Démonstration fonctionnelle (15-20 min)
- ✅ Explication de l'architecture
- ✅ Retour d'expérience
- ✅ Q&A

---

## 9. CRITÈRES D'ÉVALUATION {#evaluation}

### 9.1 Grille d'Évaluation Globale

#### Fonctionnalités (30%)
- ✅ Toutes les fonctionnalités requises implémentées
- ✅ Endpoints REST fonctionnels
- ✅ Validation des données
- ✅ Gestion des cas d'erreur

#### Qualité du Code (25%)
- ✅ Architecture en couches respectée
- ✅ Code propre et lisible
- ✅ Respect des conventions
- ✅ Absence de duplication
- ✅ Principes SOLID
- ✅ Design patterns appropriés

#### Tests (20%)
- ✅ Coverage > 80%
- ✅ Tests unitaires pertinents
- ✅ Tests d'intégration fonctionnels
- ✅ Tests passants

#### Base de Données (10%)
- ✅ Modèle relationnel cohérent
- ✅ Relations JPA correctes
- ✅ Migrations Liquibase propres
- ✅ Optimisation des requêtes

#### Sécurité (10%)
- ✅ Authentification fonctionnelle
- ✅ Autorizations correctes
- ✅ Mots de passe hashés
- ✅ Protection des endpoints

#### Documentation (5%)
- ✅ README complet
- ✅ Swagger à jour
- ✅ Code commenté
- ✅ Diagrammes clairs

### 9.2 Évaluation par Module

Chaque module sera évalué selon :
- ✅ Respect du cahier des charges
- ✅ Fonctionnalités demandées
- ✅ Qualité du code
- ✅ Tests
- ✅ Livrables

**Note finale** = Moyenne des 6 modules

---

## 10. PLANNING ET ORGANISATION

### 10.1 Rythme Alternance

**Format** : 1 semaine cours + 3 semaines entreprise

**Semaine de Cours** :
- Lundi : Présentation du module + live coding
- Mardi : Exercices pratiques guidés
- Mercredi : Développement libre + support
- Jeudi : Code review + questions
- Vendredi : Point d'avancement + lancement 3 semaines

**3 Semaines Entreprise** :
- Développement des fonctionnalités
- Suivi hebdomadaire (30 min en visio)
- Support asynchrone (Slack/Discord)
- Commits réguliers sur Git

### 10.2 Jalons

| Semaines | Module | Jalon |
|----------|--------|-------|
| 4 | Module 1 | API de base fonctionnelle |
| 8 | Module 2 | Tests complets + Platforms |
| 12 | Module 3 | Users + Library + Achievements |
| 16 | Module 4 | BDD versionnée + Reviews + Tournaments |
| 20 | Module 5 | Application sécurisée |
| 24 | Module 6 | Architecture microservices |

### 10.3 Bonnes Pratiques

#### Git
- ✅ Commits réguliers (minimum 1/jour de travail)
- ✅ Messages de commit clairs
- ✅ Branches par fonctionnalité
- ✅ Pull requests pour code review

#### Code
- ✅ Respecter les conventions Java
- ✅ Commenter les parties complexes
- ✅ Refactoring régulier
- ✅ Éviter le code dupliqué

#### Tests
- ✅ Écrire les tests en même temps que le code
- ✅ Tests unitaires pour la logique métier
- ✅ Tests d'intégration pour les endpoints
- ✅ Viser 80%+ de coverage

---

## 11. RESSOURCES

### 11.1 Documentation Officielle
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Security](https://docs.spring.io/spring-security/reference/)
- [Spring Cloud](https://docs.spring.io/spring-cloud/docs/current/reference/html/)
- [Liquibase](https://docs.liquibase.com/)
- [MapStruct](https://mapstruct.org/documentation/)

### 11.2 Tutoriels Recommandés
- [Baeldung Spring Boot](https://www.baeldung.com/spring-boot)
- [Spring Guides](https://spring.io/guides)

### 11.3 Chaînes YouTube
- Amigoscode (Spring Boot tutorials)
- Java Brains (Microservices)
- Dan Vega (Spring Boot)

### 11.4 Outils
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [JPA Buddy](https://www.jpa-buddy.com/)
- [Postman](https://www.postman.com/)
- [DBeaver](https://dbeaver.io/)

---

## 12. CONCLUSION

**GameHub** est un projet complet et moderne qui permet de maîtriser l'ensemble de l'écosystème Spring Boot, de la création d'une API REST simple jusqu'à une architecture microservices distribuée.

### Points Forts du Projet

✅ **Progressif** : Complexité croissante sur 24 semaines  
✅ **Concret** : Cas d'usage réels et actuels  
✅ **Complet** : Couvre toutes les facettes de Spring Boot  
✅ **Motivant** : Thématique gaming attractive pour les 18-25 ans  
✅ **Professionnalisant** : Architecture et pratiques du marché  
✅ **Portfolio** : Excellent projet à présenter en entretien

### Prochaines Étapes

1. **Consulter** l'énoncé détaillé du Module 1
2. **Configurer** l'environnement de développement
3. **Créer** le repository Git
4. **Démarrer** le développement

---
**Date de dernière mise à jour** : Octobre 2025  
**Version** : 1.0
