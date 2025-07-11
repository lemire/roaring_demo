# Démonstration RoaringBitmap

Ce projet compare les performances et la mémoire de RoaringBitmap et HashSet en Java.


## Installation de Java et Maven

### Sous macOS

1. Installez [Homebrew](https://brew.sh/) si ce n'est pas déjà fait :
   ```sh
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```
2. Installez Java 21 ou mieux :
   ```sh
   brew install openjdk@21
   ```
   Ajoutez Java à votre PATH (si nécessaire) :
   ```sh
   echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```
3. Installez Maven :
   ```sh
   brew install maven
   ```

### Sous Windows

1. Téléchargez Java 21 (ou mieux) depuis [Adoptium.net](https://adoptium.net/fr/temurin/releases/).
   - Choisissez l'installateur MSI pour Windows, puis suivez les instructions pour installer Java.
   - Lors de l'installation, cochez l'option pour ajouter Java au PATH si disponible.
2. Installez Maven :
   - Téléchargez la dernière version binaire de Maven depuis [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).
   - Décompressez l'archive ZIP dans un dossier (ex : `C:\maven`).
   - Ajoutez le chemin du dossier `bin` de Maven à la variable d'environnement `PATH` :
     1. Ouvrez le menu Démarrer, recherchez « variables d'environnement » et ouvrez « Modifier les variables d'environnement système ».
     2. Cliquez sur « Variables d'environnement… ».
     3. Dans la section « Variables système », sélectionnez la variable `Path` puis cliquez sur « Modifier… ».
     4. Cliquez sur « Nouveau » et ajoutez le chemin complet du dossier `bin` de Maven (ex : `C:\maven\bin`).
     5. Cliquez sur OK pour valider et fermez toutes les fenêtres.
3. Redémarrez le terminal pour que les variables d'environnement soient prises en compte.

### Vérification

Vérifiez l'installation avec :
```sh
java -version
mvn -version
```
Les deux commandes doivent afficher la version installée.

## Compilation

Dans le dossier du projet, exécutez :

```bash
mvn clean package
```

## Exécution

Après compilation, lancez :

```bash
java -jar target/roaring_demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Résultat possible

```

Remplissage des ensembles...
Temps de remplissage: 326 ms

Taille en mémoire:
RoaringBitmap 1: 1252766 bytes
RoaringBitmap 2: 1252804 bytes
HashSet 1 (estimé): 30457888 bytes
HashSet 2 (estimé): 30438208 bytes

Opération d'union...
Temps union RoaringBitmap: 3 ms
Temps union HashSet: 85 ms

Opération d'intersection...
Temps intersection RoaringBitmap: 3 ms
Temps intersection HashSet: 61 ms

Vérification d'appartenance (100000 tests)...
Temps contains RoaringBitmap: 5 ms
Temps contains HashSet: 5 ms

Cardinalité:
Bitmap 1: 951809
Bitmap 2: 951194
Union: 1812035
Intersection: 90968
```

## Explication

Ce programme compare deux structures de données pour gérer de grands ensembles d'entiers :

- **RoaringBitmap** : structure compacte et rapide pour les ensembles d'entiers, optimisée pour la mémoire et les opérations ensemblistes (union, intersection, appartenance).
- **HashSet** : structure standard Java pour les ensembles, moins efficace en mémoire pour de grands ensembles dispersés.

Le code mesure :
- Le temps de remplissage des ensembles avec des valeurs aléatoires.
- La mémoire utilisée par chaque structure.
- La rapidité des opérations d'union et d'intersection.
- La vitesse de vérification d'appartenance (contains).
- La cardinalité (nombre d'éléments) après chaque opération.

RoaringBitmap est généralement plus rapide et consomme beaucoup moins de mémoire que HashSet pour des ensembles d'entiers volumineux et dispersés.

## Fichiers principaux
- `src/main/java/RoaringBitmapDemo.java` : le code de démonstration
- `pom.xml` : configuration Maven

## Dépendance principale
- [RoaringBitmap](https://github.com/RoaringBitmap/RoaringBitmap)

## Résultat
Le programme affiche les temps d'exécution et la mémoire utilisée pour différentes opérations sur les ensembles.
