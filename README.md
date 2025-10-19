# Système de Gestion de Banque de Sang

## Description du Projet

Ce projet est une application web monolithique développée en Java Enterprise Edition (JEE) pour gérer efficacement les opérations d'une banque de sang. Elle vise à automatiser la gestion des donneurs et des receveurs, en intégrant des règles métier strictes pour la compatibilité sanguine et les urgences médicales. L'application résout les défis des processus manuels traditionnels en offrant une interface intuitive pour la création, la visualisation et la mise à jour des profils, tout en assurant la traçabilité des associations entre donneurs et receveurs.

## Objectifs

- **Optimiser la gestion des données** : Centraliser les informations personnelles, médicales et logistiques des donneurs et receveurs pour une prise de décision rapide.
- **Automatiser le matching** : Appliquer une matrice de compatibilité sanguine pour associer automatiquement les donneurs disponibles aux receveurs prioritaires, en tenant compte des niveaux d'urgence (Critique, Urgent, Normal).
- **Assurer la conformité médicale** : Valider l'éligibilité des donneurs via des critères automatisés (âge, poids, contre-indications) et gérer les statuts de disponibilité et de satisfaction des receveurs.
- **Améliorer l'expérience utilisateur** : Fournir des interfaces web responsives avec validations en temps réel et des listes triées par priorité pour une navigation fluide.
- **Promouvoir la scalabilité** : Utiliser une architecture MVC multicouches pour faciliter les extensions futures, comme la pagination ou les analytics.

## Architecture en Couches

L'application suit une architecture MVC (Model-View-Controller) multicouches pour une séparation claire des responsabilités, respectant les principes SOLID et le Repository Pattern. Voici les couches principales :

- **Couche Présentation (View)** : Gérée par des pages JSP avec JSTL pour le rendu dynamique. Les vues affichent les formulaires, listes et alertes d'erreurs, en utilisant Bootstrap pour un design responsive et moderne.
  
- **Couche Contrôleur (Controller)** : Implémentée via des Servlets (ex. : `EditReceveurServlet`, `ListReceveursServlet`) qui interceptent les requêtes HTTP, valident les entrées et orchestrent les appels vers les services. Les mappings sont configurés dans `web.xml` sans annotations.

- **Couche Service (Business Logic)** : Contient la logique métier dans des classes comme `ReceveurService` et `DonneurService`. Elle gère les règles de validation (compatibilité sanguine, éligibilité), les calculs (besoin en poches de sang) et les ajustements automatiques (statuts de disponibilité/satisfaction).

- **Couche Accès aux Données (DAO/Repository)** : Utilise JPA/Hibernate pour l'accès persistant via des interfaces comme `ReceveurDao`. Les méthodes CRUD (Create, Read, Update, Delete) et les requêtes JPQL assurent l'encapsulation de l'accès à la base de données.

- **Couche Modèle (Model)** : Entités JPA annotées (ex. : `@Entity` pour `Receveur`, `Donneur`, `Donation`) modélisant les relations (OneToMany entre Receveur et Donations).

Cette structure permet une maintenance aisée, avec un flux typique : Requête HTTP → Servlet → Service → DAO → JPA → Base de données.

## Technologies Utilisées

- **Langage** : Java 8+ avec Collections API et Stream API pour les traitements de listes.
- **Serveur Web** : Apache Tomcat pour le déploiement.
- **Vue** : JSP + JSTL pour les templates dynamiques ; Bootstrap 5 pour le CSS et les icônes (Bootstrap Icons).
- **Build et Dépendances** : Maven pour la gestion des artefacts (pom.xml inclut Hibernate, JPA, JUnit).
- **Base de Données** : MySQL avec JPA/Hibernate pour l'ORM (fichier `persistence.xml` configuré pour l'unité de persistance).
- **Tests** : JUnit pour les tests unitaires (au moins 2 : un pour la validation d'éligibilité des donneurs, un pour la compatibilité sanguine).
- **Autres** : Logging via SLF4J ; Gestion d'erreurs avec exceptions et alertes Bootstrap.

## Structure des Classes

Le projet est organisé en paquets Maven standards. Voici une vue détaillée :

### Paquet `banquesang.model` (Entités JPA)
- `Donneur` : Attributs (nom, prénom, téléphone, cin, dateNaissance, poids, genre, groupeSang, medical, statusDisponibilite). Relations : `@OneToOne` avec `Donation`.
- `Receveur` : Attributs (nom, prénom, téléphone, cin, dateNaissance, genre, groupeSang, urgence, receveurStatus, disponible). Relations : `@OneToMany` avec `Donation`.
- `Donation` : Entité de liaison (id, pocheCount=1). Relations : `@ManyToOne` avec `Donneur` et `Receveur`.
- `Medical` : Attributs booléens (hepatiteB, hepatiteC, vih, diabete, grossesse, allaitement). `@OneToOne` avec `Donneur`.

### Paquet `banquesang.enums`
- `GroupeSang` : Enum pour A+, A-, B+, B-, AB+, AB-, O+, O-.
- `Genre` : Enum (Homme, Femme).
- `StatusDisponibilite` : Enum (Disponible, NonDisponible, NonEligible).
- `Urgence` : Enum (Critique=4 poches, Urgent=3, Normal=1).
- `ReceveurStatus` : Enum (EnAttente, Satisfait).

### Paquet `banquesang.Dao`
- `ReceveurDao` / `DonneurDao` : Interfaces pour CRUD.
- `ReceveurDaoImp` / `DonneurDaoImp` : Implémentations avec EntityManager, JPQL pour requêtes (ex. : findAll avec JOIN FETCH).

### Paquet `banquesang.service`
- `ReceveurService` / `DonneurService` : Logique métier (create, updateWithAdjustments, verifierSatisfaction, isCompatible).

### Paquet `banquesang.servlet`
- `ListReceveursServlet`, `EditReceveurServlet`, etc. : Gestion des requêtes GET/POST.

### Ressources
- `src/main/resources/META-INF/persistence.xml` : Configuration JPA (provider Hibernate, dialect MySQL).
- `web.xml` : Mappings de servlets et filtres.

Diagramme de classes (UML) : [Voir diagramme_uml.png](diagramme_uml.png)

## Fonctionnalités Principales

- **Création de Profils** : Formulaires JSP pour donneurs (avec validations médicales) et receveurs (avec sélection d'urgence). Vérification automatique de l'éligibilité.
- **Listes Dynamiques** :
  - Donneurs : Tableau avec infos personnelles, statut, infos médicales ; tri par disponibilité ; actions (éditer/supprimer).
  - Receveurs : Tableau trié par urgence décroissante ; affichage des donneurs associés ; bouton "Voir Compatibles" pour matching.
- **Matching Automatisé** : Affichage des donneurs compatibles (basé sur matrice sanguine) et disponibles pour un receveur ; association unidirectionnelle (1 don=1 poche).
- **Gestion des Statuts** : Calcul automatique (éligibilité donneur, satisfaction receveur). Ajustements lors de modifications (suppression de dons excédentaires ou incompatibles).
- **Validations et Erreurs** : Regex pour téléphone/CIN ; alertes pour contre-indications ; messages d'erreur en JSP.
- **Bonus Implémentés** : Filtrage par groupe sanguin ; Pagination sur listes (via paramètres de requête).

## Captures d'Écran

![Page de Création Donneur](screenshots/create_donneur.png)  
*Formulaire de création de donneur avec validations médicales.*

![Liste des Donneurs](screenshots/list_donneurs.png)  
*Tableau des donneurs avec statuts et actions.*

![Page de Création Receveur](screenshots/create_receveur.png)  
*Formulaire de création de receveur avec sélection d'urgence.*

![Liste des Receveurs](screenshots/list_receveurs.png)  
*Tableau trié par urgence avec donneurs associés.*

![Matching Compatibles](screenshots/matching_compatibles.png)  
*Vue des donneurs compatibles pour un receveur critique.*

![Diagramme UML](screenshots/diagramme_classes.png)  
*Diagramme de classes des entités principales.*
