# Java Academic Project Management Application

## 📚 Aperçu

Cette application de bureau est conçue pour aider les professeurs à gérer efficacement les projets académiques. Elle offre une interface utilisateur intuitive et un ensemble de fonctionnalités robustes pour faciliter la gestion des projets étudiants et des tâches associées.

---

## 🛠️ Fonctionnalités

### Gestion des Projets
- **Création de projets** : Ajoutez de nouveaux projets en fournissant des informations telles que le titre, la description, la date de début et la date d'échéance.
- **Mise à jour des projets** : Modifiez les détails des projets existants.
- **Suppression de projets** : Supprimez les projets terminés ou obsolètes.
- **Attachement de documents** :
  - Ajoutez des documents spécifiques (PDF, Word, Excel, etc.) à chaque projet.
  - Affichez et gérez facilement tous les documents attachés.

### Suivi des Tâches
- **Assignation de tâches** : Créez et attribuez des tâches spécifiques à chaque projet.
- **Statut des tâches** : Suivez le statut des tâches (en cours, terminé, en retard).
- **Priorité des tâches** : Classez les tâches par priorité pour une gestion efficace.

### Intégration avec les API Google
- **Google Calendar** : Synchronisez automatiquement les échéances des projets avec Google Calendar.

### Recherche et Filtrage
- Recherchez des projets par titre, étudiant ou date.
- Filtrez les projets par statut (actif, terminé) ou priorité.

### Interface Utilisateur Intuitive
- Utilisation de **Java Swing** pour créer une interface claire et facile à naviguer.
- Prise en charge de la gestion des événements via une architecture **MVC**.

---

## 🏗️ Architecture

L'application suit une architecture **Model-View-Controller (MVC)** pour garantir :
- **Modularité** : Les composants de l'application (modèles, vues, contrôleurs) sont séparés.
- **Extensibilité** : Ajout de nouvelles fonctionnalités sans modification majeure du code existant.

---

## 💻 Technologies Utilisées

- **Java** : Langage principal utilisé pour le développement.
- **Swing** : Framework pour construire l'interface utilisateur.
- **Google APIs** : 
  - Google Calendar : Pour la gestion des échéances.
  - Google Drive : Pour l'attachement et la synchronisation des documents.
- **Fichiers locaux** : Gestion des documents stockés localement pour chaque projet.

---

## 🚀 Installation et Configuration

### Étapes d'Installation

1. **Clonez le dépôt** :
   ```bash
   git clone https://github.com/Redakadiri00/Java_gestionProjects.git
   cd Java_gestionProjects
