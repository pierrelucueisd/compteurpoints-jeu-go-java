# Projet de session - INF5153

## Description 
Ce projet est réalisé dans le carde du cours Génie logiciel: conception (INF5153) offert par l'Université du Québec À 
Montréal (UQAM). Le projet consiste en l'implémentation d'un jeu de Go. 

## [Règle du Jeu de GO](https://www.jeudego.org/_php/regleGo.php#:~:text=Le%20go%20se%20joue%20%C3%A0,on%20consid%C3%A8re%20la%20partie%20termin%C3%A9e)
Conventionnelement le jeu de Go, se joue à deux, sur un plateau composé d'intersections appelé Goban, 
le Goban peut avoir différentes tailles (9x9, 13x13, 19x19). On joue avec des pierres habituellement faites en résine,
il y a des pierres noires et des pierres blanches. 

Le joueur ayant les pierres noires ouvre la partie en une pierre sur l'une des intersections du Goban.
Le but du jeu étant de posséder le plus grand territoire sur le plateau, cela se fait en capturant les pierres adverses 
et en formant des barrière empêchant au joueur adverse de jouer dans notre territoire.

#### Groupe de pierres (Chaîne de pierres)
Un groupe de pierre est se forme lorsque des pierres de la même couleur son posées sur des intersections adjacentes.
 

#### Capture
Dans le jeu de Go, chaque pierre (ou groupe de pierres) possède une notion de degré de liberté, il s'agit du nombre 
d'intersections libres autour de la pierre, une fois qu'il n'y a plus d'intersection libre autour d'une pierre cette 
dernière se fait capturer.

#### Atari 
Atari est l'état dans lequel une pierre (groupe de pierres) se retrouve lorsque son degré de liberté est réduit à un 
seul mouvement, le joueur adverse peut alors poser une pierre et faire une capture.

#### KO
KO signifie éternité en japonais et illustre ici une situation non avantageuse pour les deux joueurs. Pour remédier à 
une situation potentiellement sans fin il existe une règle stipulant qu'il est interdit de 
reprendre un kô au coup immédiatement suivant. 

#### Fin de partie 
Une partie de Go finie lorsque les deux joueurs passent consécutivement leur tour. On compte alors les points, 
les points sont calculés en fonction du nombre de pierres sur le Goban et d'intersections dans un territoire fermé.

## Fonctionnement
Le logiciel prend en entrée une série de positions qui vont constituer une partie de Go

Le programme peut être lancé avec où sans fichier en argument :

```

```

#### Fichier d'entrée
Un fichier peut être passé en argument avec une suite d'actions en contenu :

```
G7 G6 F6 G5 H5 H6 J6 H7 H8
```

#### Format de sortie 
Le programme fournira en sortie une représentation d'un Goban 9x9 à la suite de l'execution de toutes les actions 
fournies en entrée.

Chaque '+' représente une intersection, les '*' les pierres noires et les 'o' les pierres blanches.

```
+-+-+-+-+-+-+-+-+
+-+-+-+-+-+-+-*-+
+-+-+-+-+-+-*-o-+
+-+-+-+-+-*-o-o-*
+-+-+-+-+-+-o-*-+
+-+-+-+-+-+-+-+-+
+-+-+-+-+-+-+-+-+
+-+-+-+-+-+-+-+-+
+-+-+-+-+-+-+-+-+
```
## Contenu du projet

#### projet-de-session-equipe_tres_solid/src/main
Dans ce dossier, vous retrouvez toutes les classes contenant le code source du logiciel.

    - /java/ :
    
        Le code sources est divisée entre différentes classes :
        
            -Action :
            -ActionType :
            -Board :
            -BoardLogger :
            -Color :
            -Deserializer :
            -ErrorType :
            -GameConsole :
            -GameCOntroller:
            -Intersection :
            -Main :
            -PassAction :
            -Player :
            -PlayerCarousel :
            -Position :
            -PositionDeserializer :
            -PutStoneAction :
          
    
#### projet-de-session-equipe_tres_solid/src/test
Dans ce dossier vous retrouverez tous les tests unitaires.

    -/java/
    
        Le dossier de test contient la classe de test :
        
            -test.java
## Liste des logiciels et versions utilisés 

- JUnit Jupiter 

```

	<dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter</artifactId>
                <version>5.4.2</version>
                <scope>test</scope>
        </dependency>

```

## Outil de Build utilisé
* [Maven 4.0.0](https://maven.apache.org/) - Dependency Management

## Dépôt central 
* [Github](https://github.com/INF5153-E20/projet-de-session-equipe_tres_solid)

## Auteurs
* Pierre-Luc Maître -
* David Quirion - QUID26099001
* Jennifer-Kim Garand - GARJ07599504 
* Alexandre-Thibault ARCOLE - ARCA83030008

## Références
* [Énoncé du projet](https://github.com/INF5153-E20/Notes/blob/master/projet.md)
* [Jeu de Go](https://www.jeudego.org/_php/regleGo.php#:~:text=Le%20go%20se%20joue%20%C3%A0,on%20consid%C3%A8re%20la%20partie%20termin%C3%A9e.)
* [Wikipedia](https://fr.wikipedia.org/wiki/Go_(jeu))

## License 

Le projet est sous licence MIT X11.

Consulter le fichier [LICENSE.md](LICENSE.md) pour les détails de la licence.

