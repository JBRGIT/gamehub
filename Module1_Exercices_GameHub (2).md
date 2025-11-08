# MODULE 1 - BASES ET BONNES PRATIQUES
## Exercices Pratiques - Projet GameHub

**Durée estimée** : 8-10 heures  
**Objectif** : Construire l'architecture de base du projet GameHub en appliquant les bonnes pratiques Spring Boot

---

## 📋 CONTEXTE DU MODULE

Vous allez créer la base du projet GameHub, une plateforme de gestion de jeux vidéo. Ce premier module pose les fondations du projet avec une architecture professionnelle.

**Livrables attendus** :
- Un projet Spring Boot fonctionnel
- Un CRUD complet pour les jeux vidéo
- Une architecture en couches respectée
- Une gestion d'erreurs centralisée

---

## 🎯 EXERCICE 1 : MISE EN PLACE DU PROJET
**Durée** : 45 minutes  
**Difficulté** : ⭐

### Objectif
Initialiser le projet avec Spring Initializr et mettre en place la structure de packages selon les bonnes pratiques vues en cours.

### Consignes

1. **Créer le projet sur Spring Initializr**
   - Group : `com.gamehub`
   - Artifact : `gamehub-api`
   - Java 17 ou 21
   - Dépendances : Spring Web, Spring Data JPA, H2 Database, Lombok, Validation

2. **Créer la structure de packages**
   - Rappelez-vous : 8 packages principaux vus en cours
   - Dans `dto`, créez 2 sous-packages : `request` et `response`

3. **Configurer application.properties**
   - Base de données Postgresql en mémoire nommée `gamehub`
   - Afficher les requêtes SQL
   - DDL : create-drop pour le développement

### Questions pour réfléchir
- Pourquoi sépare-t-on les DTOs en `request` et `response` ?
- Quel est l'intérêt d'avoir un package `config` séparé ?
- Pourquoi utiliser `create-drop` en développement ?

### ✅ Auto-validation
- [ ] Le projet compile sans erreur
- [ ] Vous avez bien 8 packages à la racine (+ 2 sous-packages dans dto)
- [ ] `mvn spring-boot:run` lance l'application
- [ ] La console H2 est accessible : http://localhost:8080/h2-console

---

## 🎯 EXERCICE 2 : ENTITÉ ET REPOSITORY
**Durée** : 1h  
**Difficulté** : ⭐⭐

### Objectif
Créer l'entité `Game` et son repository en appliquant les annotations JPA correctes.

### Consignes

#### 2.1 Créer l'énumération GameCategory
Dans le package `entity`, créez une enum avec au moins 10 catégories de jeux :
- ACTION, ADVENTURE, RPG, STRATEGY, etc.

#### 2.2 Créer l'entité Game

L'entité doit contenir les attributs suivants :

| Attribut | Type | Contraintes |
|----------|------|-------------|
| id | Long | Clé primaire, auto-incrémenté |
| title | String | Obligatoire, max 200 caractères |
| description | String | Texte long (TEXT) |
| releaseDate | LocalDate | Date de sortie |
| price | BigDecimal | Prix du jeu, obligatoire |
| developer | String | Nom du développeur, max 100 |
| publisher | String | Nom de l'éditeur, max 100 |
| category | GameCategory | Catégorie (enum), obligatoire |
| coverImageUrl | String | URL de l'image de couverture |
| available | Boolean | Disponibilité, obligatoire, défaut=true |

**Rappel des annotations vues en cours** :
- `@Entity` et `@Table` pour la table
- `@Id` et `@GeneratedValue` pour la clé primaire
- `@Column` pour les contraintes
- `@Enumerated` pour les enums
- Lombok : `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`

#### 2.3 Créer le Repository

Interface `GameRepository` dans le package `repository` :
- Étendre `JpaRepository<Game, Long>`
- Ajouter 3 méthodes de requête personnalisées :
  1. Trouver un jeu par son titre (retourne Optional)
  2. Trouver tous les jeux d'une catégorie
  3. Trouver tous les jeux disponibles (available = true)

**Rappel** : Spring Data JPA génère automatiquement l'implémentation si vous nommez bien vos méthodes !

### Questions pour réfléchir
- Pourquoi utiliser `BigDecimal` pour le prix plutôt que `double` ?
- Quelle est la différence entre `@Column(nullable = false)` et la validation Bean ?
- Pourquoi retourner `Optional` dans les méthodes de recherche ?

### ✅ Auto-validation
- [ ] Le projet compile toujours
- [ ] Au démarrage, la table `games` est créée (vérifiez dans H2 Console)
- [ ] La table a bien 10 colonnes
- [ ] L'énumération s'affiche comme STRING dans la base

---

## 🎯 EXERCICE 3 : DTOs ET VALIDATION
**Durée** : 1h30  
**Difficulté** : ⭐⭐⭐

### Objectif
Créer les DTOs pour séparer la couche présentation de la couche données, avec validation automatique.

### Consignes

#### 3.1 GameCreateRequest

**Ce que le client envoie pour CRÉER un jeu** (package `dto.request`)

Attributs identiques à Game SAUF :
- Pas d'`id` (il sera généré)
- Pas d'`available` (sera mis à true par défaut)

**Validations à ajouter** (utilisez les annotations Jakarta Validation) :
- `title` : obligatoire, max 200 caractères
- `description` : max 5000 caractères
- `releaseDate` : ne peut pas être dans le futur
- `price` : obligatoire, entre 0 et 999.99
- `developer` : max 100 caractères
- `publisher` : max 100 caractères
- `category` : obligatoire
- `coverImageUrl` : doit être une URL valide (regex : `^(http|https)://.*$`)

**Annotations de validation à utiliser** : `@NotBlank`, `@NotNull`, `@Size`, `@DecimalMin`, `@DecimalMax`, `@PastOrPresent`, `@Pattern`

**Avec Lombok** : `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`

#### 3.2 GameUpdateRequest

**Ce que le client envoie pour MODIFIER un jeu** (package `dto.request`)

Mêmes attributs que GameCreateRequest MAIS :
- Tous les champs sont optionnels (nullable)
- Seuls les champs envoyés seront mis à jour
- Ajouter l'attribut `available` (Boolean, optionnel)

**Validations** : Les mêmes que GameCreateRequest, MAIS sans `@NotNull` / `@NotBlank`

#### 3.3 GameSummaryResponse

**Ce que l'API renvoie pour une LISTE de jeux** (package `dto.response`)

Attributs minimalistes pour une liste :
- id
- title
- price
- category
- coverImageUrl
- available

Pas de validations (c'est une réponse, pas une requête).

#### 3.4 GameDetailResponse

**Ce que l'API renvoie pour UN jeu en détail** (package `dto.response`)

Tous les attributs de Game.

### Questions pour réfléchir
- Pourquoi séparer `CreateRequest` et `UpdateRequest` ?
- Pourquoi avoir deux DTOs de réponse différents ?
- Quelle est la différence entre `@NotNull`, `@NotBlank` et `@NotEmpty` ?

### ✅ Auto-validation
- [ ] Vous avez 4 classes DTO
- [ ] Les validations sont présentes sur les 2 Request
- [ ] Le projet compile

---

## 🎯 EXERCICE 4 : MAPPER AVEC MAPSTRUCT
**Durée** : 1h  
**Difficulté** : ⭐⭐⭐

### Objectif
Créer le mapper pour convertir automatiquement entre Entity et DTOs.

### Consignes

#### 4.1 Ajouter MapStruct au pom.xml

Ajoutez la dépendance MapStruct (version 1.5.5.Final) et configurez le plugin Maven compiler avec les annotation processors (MapStruct + Lombok).

**Indice** : Cherchez dans vos notes de cours la configuration complète du pom.xml pour MapStruct.

#### 4.2 Créer l'interface GameMapper

Dans le package `mapper`, créez l'interface `GameMapper` avec :

**Annotation de la classe** :
```java
@Mapper(componentModel = "spring")
```

**4 méthodes à définir** :

1. **Entity → DetailResponse**
   - Paramètre : `Game`
   - Retour : `GameDetailResponse`

2. **Entity → SummaryResponse**
   - Paramètre : `Game`
   - Retour : `GameSummaryResponse`

3. **CreateRequest → Entity**
   - Paramètre : `GameCreateRequest`
   - Retour : `Game`

4. **UpdateRequest → Entity (mise à jour partielle)**
   - Paramètres : `GameUpdateRequest` + `@MappingTarget Game`
   - Retour : `void`
   - Annotation spéciale : `@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)`

#### 4.3 Compiler le projet

Lancez `mvn clean compile` pour générer l'implémentation du mapper.

### Questions pour réfléchir
- Où se trouve l'implémentation générée ? (Indice : target/generated-sources)
- Pourquoi utilise-t-on `@MappingTarget` ?
- Que fait `NullValuePropertyMappingStrategy.IGNORE` ?

### ✅ Auto-validation
- [ ] L'interface `GameMapper` contient 4 méthodes
- [ ] Après compilation, `GameMapperImpl` existe dans target/generated-sources
- [ ] Vous pouvez injecter `GameMapper` comme un bean Spring

---

## 🎯 EXERCICE 5 : GESTION DES EXCEPTIONS
**Durée** : 1h  
**Difficulté** : ⭐⭐

### Objectif
Mettre en place une gestion centralisée et professionnelle des erreurs avec `@ControllerAdvice`.

### Consignes

#### 5.1 Créer les exceptions personnalisées

Dans le package `exception`, créez :

**GameNotFoundException**
- Étend `RuntimeException`
- 2 constructeurs :
  - Un avec `Long id` → message : "Jeu non trouvé avec l'ID : {id}"
  - Un avec `String title` → message : "Jeu non trouvé avec le titre : {title}"

**GameAlreadyExistsException**
- Étend `RuntimeException`
- 1 constructeur avec `String title` → message : "Un jeu existe déjà avec le titre : {title}"

#### 5.2 Créer ErrorResponse

Dans le package `exception`, créez un record ou une classe `ErrorResponse` avec :
- `LocalDateTime timestamp`
- `int status`
- `String error`
- `String message`
- `String path`
- `Map<String, String> validationErrors` (pour les erreurs de validation)

**Avec Lombok** : `@Data`, `@Builder`, constructeurs

#### 5.3 Créer GlobalExceptionHandler

Dans le package `exception`, créez une classe annotée `@RestControllerAdvice`.

**4 méthodes à implémenter** :

1. **handleGameNotFoundException**
   - `@ExceptionHandler(GameNotFoundException.class)`
   - Retourne `ResponseEntity<ErrorResponse>`
   - Status HTTP : 404 NOT_FOUND

2. **handleGameAlreadyExistsException**
   - `@ExceptionHandler(GameAlreadyExistsException.class)`
   - Retourne `ResponseEntity<ErrorResponse>`
   - Status HTTP : 409 CONFLICT

3. **handleValidationException**
   - `@ExceptionHandler(MethodArgumentNotValidException.class)`
   - Retourne `ResponseEntity<ErrorResponse>`
   - Status HTTP : 400 BAD_REQUEST
   - Extraire les erreurs de validation : `ex.getBindingResult().getAllErrors()`
   - Les mettre dans le champ `validationErrors` de ErrorResponse

4. **handleGlobalException**
   - `@ExceptionHandler(Exception.class)`
   - Retourne `ResponseEntity<ErrorResponse>`
   - Status HTTP : 500 INTERNAL_SERVER_ERROR
   - Message générique

**Rappel du cours** : Utilisez `WebRequest request` en paramètre pour récupérer le path.

### Questions pour réfléchir
- Pourquoi étendre `RuntimeException` et pas `Exception` ?
- Quelle est la différence entre `@RestControllerAdvice` et `@ControllerAdvice` ?
- Pourquoi capturer `Exception.class` en dernier ?

### ✅ Auto-validation
- [ ] Vous avez 2 exceptions personnalisées
- [ ] `GlobalExceptionHandler` a 4 méthodes
- [ ] Chaque méthode retourne un `ErrorResponse` bien structuré

---

## 🎯 EXERCICE 6 : COUCHE SERVICE
**Durée** : 2h  
**Difficulté** : ⭐⭐⭐⭐

### Objectif
Implémenter la logique métier dans la couche service avec gestion des erreurs.

### Consignes

#### 6.1 Créer l'interface GameService

Dans le package `service`, créez l'interface avec ces signatures de méthodes :

```java
GameDetailResponse createGame(GameCreateRequest request);
GameDetailResponse getGameById(Long id);
List<GameSummaryResponse> getAllGames();
List<GameSummaryResponse> getGamesByCategory(GameCategory category);
List<GameSummaryResponse> getAvailableGames();
GameDetailResponse updateGame(Long id, GameUpdateRequest request);
void deleteGame(Long id);
void markAsUnavailable(Long id);
```

#### 6.2 Implémenter GameServiceImpl

Dans le package `service`, créez la classe d'implémentation :

**Annotations de classe** :
- `@Service`
- `@RequiredArgsConstructor` (pour l'injection)
- `@Slf4j` (pour les logs)
- `@Transactional` (au niveau classe)

**Injecter** (via le constructeur avec `@RequiredArgsConstructor`) :
- `GameRepository`
- `GameMapper`

**Pour chaque méthode**, implémentez la logique suivante :

**createGame** :
1. Logger l'opération
2. Vérifier si un jeu avec ce titre existe déjà (utiliser `findByTitle`)
3. Si oui → lever `GameAlreadyExistsException`
4. Convertir le DTO en entité (mapper)
5. Mettre `available` à `true`
6. Sauvegarder en base
7. Logger le succès avec l'ID
8. Retourner le DTO de réponse

**getGameById** :
1. Logger la récupération
2. Chercher en base avec `findById`
3. Si absent → lever `GameNotFoundException`
4. Convertir en DTO et retourner
5. Annotation : `@Transactional(readOnly = true)`

**getAllGames** :
1. Logger
2. `findAll()` du repository
3. Convertir chaque Game en SummaryResponse avec le mapper
4. Retourner la liste
5. Annotation : `@Transactional(readOnly = true)`

**getGamesByCategory** :
1. Logger avec la catégorie
2. Utiliser la méthode custom du repository
3. Mapper en liste de SummaryResponse
4. Annotation : `@Transactional(readOnly = true)`

**getAvailableGames** :
1. Logger
2. Utiliser la méthode custom du repository
3. Mapper en liste de SummaryResponse
4. Annotation : `@Transactional(readOnly = true)`

**updateGame** :
1. Logger
2. Récupérer le jeu par ID (lever exception si absent)
3. Si le titre change, vérifier qu'il n'existe pas déjà
4. Utiliser le mapper pour la mise à jour partielle
5. Sauvegarder
6. Logger le succès
7. Retourner le DTO

**deleteGame** :
1. Logger
2. Vérifier que le jeu existe (`existsById`)
3. Si absent → lever exception
4. Supprimer avec `deleteById`
5. Logger le succès

**markAsUnavailable** :
1. Logger
2. Récupérer le jeu (lever exception si absent)
3. Mettre `available` à `false`
4. Sauvegarder
5. Logger

**Indice** : Utilisez `log.info()`, `log.error()` pour tracer les opérations.

### Questions pour réfléchir
- Pourquoi mettre `@Transactional` au niveau de la classe ?
- Quel est l'intérêt de `readOnly = true` ?
- Pourquoi logger les opérations ?

### ✅ Auto-validation
- [ ] Les 8 méthodes sont implémentées
- [ ] Les exceptions sont levées aux bons moments
- [ ] Les logs sont présents
- [ ] Le projet compile

---

## 🎯 EXERCICE 7 : CONTRÔLEUR REST
**Durée** : 1h30  
**Difficulté** : ⭐⭐⭐

### Objectif
Créer le contrôleur REST exposant l'API HTTP.

### Consignes

#### 7.1 Créer GameController

Dans le package `controller`, créez une classe avec :

**Annotations de classe** :
- `@RestController`
- `@RequestMapping("/api/games")`
- `@RequiredArgsConstructor`
- `@Slf4j`

**Injecter** : `GameService`

#### 7.2 Implémenter les endpoints

**POST /api/games - Créer un jeu**
- Méthode : `createGame`
- Paramètre : `@Valid @RequestBody GameCreateRequest`
- Retour : `ResponseEntity<GameDetailResponse>`
- Status : 201 CREATED
- Logger la requête

**GET /api/games/{id} - Récupérer un jeu**
- Méthode : `getGameById`
- Paramètre : `@PathVariable Long id`
- Retour : `ResponseEntity<GameDetailResponse>`
- Status : 200 OK

**GET /api/games - Récupérer les jeux (avec filtres)**
- Méthode : `getAllGames`
- Paramètres optionnels :
  - `@RequestParam(required = false) GameCategory category`
  - `@RequestParam(required = false, defaultValue = "false") Boolean availableOnly`
- Retour : `ResponseEntity<List<GameSummaryResponse>>`
- Logique :
  - Si `category` fournie → appeler `getGamesByCategory`
  - Sinon si `availableOnly = true` → appeler `getAvailableGames`
  - Sinon → appeler `getAllGames`

**PUT /api/games/{id} - Mettre à jour un jeu**
- Méthode : `updateGame`
- Paramètres : `@PathVariable Long id`, `@Valid @RequestBody GameUpdateRequest`
- Retour : `ResponseEntity<GameDetailResponse>`
- Status : 200 OK

**DELETE /api/games/{id} - Supprimer un jeu**
- Méthode : `deleteGame`
- Paramètre : `@PathVariable Long id`
- Retour : `ResponseEntity<Void>`
- Status : 204 NO_CONTENT

**PATCH /api/games/{id}/unavailable - Marquer indisponible**
- Méthode : `markAsUnavailable`
- Paramètre : `@PathVariable Long id`
- Retour : `ResponseEntity<Void>`
- Status : 204 NO_CONTENT

### Questions pour réfléchir
- Pourquoi utiliser `ResponseEntity` plutôt que retourner directement le DTO ?
- Quelle est la différence entre PUT et PATCH ?
- Pourquoi 201 pour POST et 204 pour DELETE ?

### ✅ Auto-validation
- [ ] 6 endpoints créés
- [ ] `@Valid` est présent sur les POST et PUT
- [ ] Les codes HTTP sont corrects
- [ ] L'application démarre sans erreur

---

## 🎯 EXERCICE 8 : TESTS MANUELS
**Durée** : 1h  
**Difficulté** : ⭐⭐

### Objectif
Valider le bon fonctionnement de l'API avec Postman, cURL ou un client REST.

### Consignes

Créez une collection de tests et testez les scénarios suivants :

#### Scénario 1 : CRUD Complet
1. **Créer un jeu**
   ```
   POST /api/games
   {
     "title": "The Witcher 3",
     "description": "Un RPG épique",
     "releaseDate": "2015-05-19",
     "price": 39.99,
     "developer": "CD Projekt Red",
     "publisher": "CD Projekt",
     "category": "RPG",
     "coverImageUrl": "https://example.com/witcher.jpg"
   }
   ```
   → Attendu : 201, retourne le jeu avec un ID

2. **Récupérer le jeu créé**
   ```
   GET /api/games/1
   ```
   → Attendu : 200, retourne le jeu complet

3. **Lister tous les jeux**
   ```
   GET /api/games
   ```
   → Attendu : 200, tableau avec le jeu

4. **Mettre à jour le prix**
   ```
   PUT /api/games/1
   {
     "price": 29.99
   }
   ```
   → Attendu : 200, prix modifié

5. **Marquer comme indisponible**
   ```
   PATCH /api/games/1/unavailable
   ```
   → Attendu : 204

6. **Vérifier l'indisponibilité**
   ```
   GET /api/games/1
   ```
   → Attendu : available = false

7. **Supprimer le jeu**
   ```
   DELETE /api/games/1
   ```
   → Attendu : 204

8. **Vérifier la suppression**
   ```
   GET /api/games/1
   ```
   → Attendu : 404

#### Scénario 2 : Gestion des erreurs

**Test validation : titre vide**
```
POST /api/games
{
  "title": "",
  "price": 39.99,
  "category": "RPG"
}
```
→ Attendu : 400, message de validation

**Test validation : prix négatif**
```
POST /api/games
{
  "title": "Test",
  "price": -10,
  "category": "RPG"
}
```
→ Attendu : 400, message de validation

**Test : jeu introuvable**
```
GET /api/games/999
```
→ Attendu : 404, message d'erreur clair

**Test : doublon**
1. Créer un jeu "Cyberpunk 2077"
2. Essayer de créer un autre jeu "Cyberpunk 2077"
→ Attendu : 409 CONFLICT

#### Scénario 3 : Filtres

**Test : filtrer par catégorie**
1. Créer 3 jeux de catégories différentes
2. `GET /api/games?category=RPG`
→ Attendu : uniquement les jeux RPG

**Test : jeux disponibles uniquement**
1. Créer 2 jeux
2. Marquer l'un comme indisponible
3. `GET /api/games?availableOnly=true`
→ Attendu : uniquement le jeu disponible

### À rendre
- Captures d'écran de 5 tests réussis
- 1 capture d'une erreur de validation
- 1 capture d'une erreur 404

### ✅ Auto-validation
- [ ] Tous les scénarios passent
- [ ] Les codes HTTP sont corrects
- [ ] Les messages d'erreur sont clairs
- [ ] Les filtres fonctionnent

---

## 🎯 EXERCICE 9 : DONNÉES DE TEST (BONUS)
**Durée** : 30 minutes  
**Difficulté** : ⭐

### Objectif
Créer un jeu de données initial avec `CommandLineRunner`.

### Consignes

Dans le package `config`, créez une classe `DataLoader` :

1. Annotez avec `@Configuration`
2. Créez un `@Bean` de type `CommandLineRunner`
3. Injectez `GameRepository`
4. Dans la méthode `run` :
   - Vérifier si la base est vide (`count() == 0`)
   - Si oui, créer 5 jeux avec différentes catégories
   - Logger le nombre de jeux créés

**Astuce** : Utilisez le Builder pattern pour créer vos entités Game.

### ✅ Auto-validation
- [ ] Au démarrage, 5 jeux sont créés
- [ ] GET /api/games retourne les 5 jeux
- [ ] Les jeux sont visibles dans H2 Console

---

## 📊 GRILLE D'AUTO-ÉVALUATION

Pour chaque critère, évaluez-vous de 1 à 5 :

| Critère | Note /5 | Commentaire |
|---------|---------|-------------|
| **Architecture** | | |
| Structure de packages correcte | | |
| Séparation des couches respectée | | |
| **Entités et JPA** | | |
| Annotations JPA appropriées | | |
| Repository avec méthodes customs | | |
| **DTOs et Mapping** | | |
| DTOs bien conçus | | |
| Validations pertinentes | | |
| Mapper fonctionnel | | |
| **Gestion des erreurs** | | |
| Exceptions personnalisées | | |
| @ControllerAdvice bien implémenté | | |
| Messages d'erreur clairs | | |
| **Service** | | |
| Logique métier correcte | | |
| Gestion des cas d'erreur | | |
| Logs présents | | |
| **Contrôleur** | | |
| Endpoints REST corrects | | |
| Codes HTTP appropriés | | |
| Validation activée | | |
| **Tests** | | |
| Tous les scénarios testés | | |
| Gestion des erreurs validée | | |

**Score total** : ___ / 90

### Objectif de validation
- **< 45** : Revoir les concepts de base
- **45-60** : Bon travail, quelques points à améliorer
- **60-75** : Très bon niveau
- **> 75** : Excellence ! Prêt pour le module suivant

---

## 🎓 POINTS CLÉS À RETENIR

### Architecture en couches
✅ Chaque couche a une responsabilité unique  
✅ Controller → Service → Repository → Entity  
✅ Les DTOs isolent la couche présentation

### Bonnes pratiques
✅ Ne jamais exposer les entités directement  
✅ Valider les entrées avec Bean Validation  
✅ Gérer les erreurs de manière centralisée  
✅ Logger les opérations importantes  
✅ Utiliser des codes HTTP appropriés

### À éviter
❌ Code métier dans le contrôleur  
❌ Repository appelé directement depuis le contrôleur  
❌ Exceptions non gérées  
❌ Pas de validation des entrées  
❌ Entités JPA exposées dans l'API

---

## 📝 RENDU ATTENDU

**Format** : ZIP nommé `NomPrenom_Module1_GameHub.zip`

**Contenu** :
1. **Code source complet** (tout le dossier du projet)
2. **README.md** contenant :
   - Commande pour lancer le projet
   - Liste des endpoints avec exemples
   - 3 difficultés rencontrées et comment vous les avez résolues
3. **captures/** dossier avec :
   - Application qui démarre
   - 5 requêtes réussies
   - 2 gestions d'erreurs
4. **tests.json** : Collection Postman exportée OU fichier de commandes cURL

---

## 🚀 POUR ALLER PLUS LOIN

Si vous avez fini en avance, essayez ces défis :

### Défi 1 : Pagination
Ajouter la pagination sur GET /api/games avec :
- `@PageableDefault`
- Retourner un `Page<GameSummaryResponse>`

### Défi 2 : Recherche
Créer un endpoint de recherche par mot-clé dans le titre :
```
GET /api/games/search?query=witcher
```

### Défi 3 : Statistiques
Créer un endpoint qui retourne :
- Nombre de jeux par catégorie
- Prix moyen par catégorie
- Jeu le plus cher, le moins cher

### Défi 4 : Documentation
Ajouter Swagger/OpenAPI pour documenter automatiquement votre API.

---

**Bon courage ! 💪🎮**

*N'hésitez pas à revenir vers votre formateur si vous bloquez sur un exercice !*
