

# 🩸 Système de Gestion de Banque de Sang

## 🔹 Description

Ce projet est une application web **monolithique** développée en **Java Enterprise Edition (JEE)** pour gérer efficacement les opérations d'une banque de sang.
Elle automatise la gestion des **donneurs** et **receveurs**, en intégrant des règles de compatibilité sanguine et de suivi médical.

L'application permet de :

* Créer, visualiser et mettre à jour les profils des donneurs et receveurs.
* Assurer la compatibilité et l’éligibilité des dons.
* Prioriser les receveurs en fonction du niveau d’urgence (Critique, Urgent, Normal).

---

## 🎯 Objectifs

* **Optimisation des données** : Centralisation des informations personnelles et médicales.
* **Matching automatique** : Association des donneurs disponibles aux receveurs prioritaires.
* **Conformité médicale** : Vérification de l’éligibilité des donneurs (âge, poids, contre-indications).
* **Expérience utilisateur améliorée** : Interface web responsive avec validations et listes triées.
* **Scalabilité** : Architecture MVC multicouches pour faciliter les extensions futures.

---

## 🏛 Architecture

L’application suit le modèle **MVC (Model-View-Controller)** :

### 1. Couche Présentation (View)

* **JSP + JSTL** pour l’affichage dynamique.
* Design responsive avec **Bootstrap**.

### 2. Couche Contrôleur (Controller)

* **Servlets** pour gérer les requêtes HTTP (`ListReceveursServlet`, `EditReceveurServlet`).
* Validation et orchestration des appels aux services.

### 3. Couche Service (Business Logic)

* Logique métier dans `DonneurService` et `ReceveurService`.
* Gestion de l’éligibilité, compatibilité sanguine et ajustements des statuts.

### 4. Couche Accès aux Données (DAO/Repository)

* **JPA/Hibernate** pour la persistance.
* CRUD et requêtes JPQL via `DonneurDaoImp` et `ReceveurDaoImp`.

### 5. Couche Modèle (Model)

* Entités **JPA** : `Donneur`, `Receveur`, `Donation`, `Medical`.
* Relations OneToOne et OneToMany.

---

## 🛠 Technologies Utilisées

| Technologie     | Usage                            |
| --------------- | -------------------------------- |
| Java 17         | Langage principal                |
| Apache Tomcat   | Serveur web                      |
| JSP / JSTL      | Templates dynamiques             |
| Maven           | Gestion des dépendances et build |
| JPA / Hibernate | ORM pour la base de données      |
| JUnit           | Tests unitaires                  |

---

## 📂 Structure du Projet

* **banquesang.model** : Entités JPA (`Donneur`, `Receveur`, `Donation`, `Medical`)
* **banquesang.enums** : Enumérations (`GroupeSang`, `Genre`, `StatusDisponibilite`, `Urgence`, `ReceveurStatus`)
* **banquesang.dao** : Interfaces et implémentations pour CRUD
* **banquesang.service** : Logique métier
* **banquesang.servlet** : Servlets pour les requêtes HTTP

---

## ⚙️ Installation et Exécution

```bash
# Cloner le repository
git clone https://github.com/amhine/sang.git
cd sang

# Build avec Maven
mvn clean package

# Déployer sur Tomcat
mvn tomcat7:deploy

# Accéder à l'application
# http://localhost:8080/sang

# Lancer les tests unitaires
mvn test
```

---

## 📝 Fonctionnalités

* **Gestion des profils** : Créer, modifier, supprimer donneurs et receveurs.
* **Liste dynamique** : Affichage des donneurs et receveurs avec tri par urgence et statut.
* **Matching automatique** : Donneurs compatibles affichés pour chaque receveur selon matrice sanguine et disponibilité.
* **Validation médicale** : Vérification automatique des contre-indications et statut d’éligibilité.

---

## 📷 Captures d’Écran

| Page                 | Capture                                                        |
| -------------------- | -------------------------------------------------------------- |
| Création Donneur     | ![create\_donneur](screenshots/create_donneur.png)             |
| Liste des Donneurs   | ![list\_donneurs](screenshots/list_donneurs.png)               |
| Création Receveur    | ![create\_receveur](screenshots/create_receveur.png)           |
| Liste des Receveurs  | ![list\_receveurs](screenshots/list_receveurs.png)             |
| Matching Compatibles | ![matching\_compatibles](screenshots/matching_compatibles.png) |
| Diagramme UML        | ![uml](screenshots/diagramme_classes.png)                      |

---

## 📈 Diagramme UML

[Voir diagramme_uml.png](screenshots/diagramme_classes.png)

---
