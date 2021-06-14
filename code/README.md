# Übersicht technologischer Entscheidungen

## Architekturentscheidungen

- Verwendung des Build Tools Gradle
    - Zum bauen des Projektes
    - Verwalten der Abhängigkeiten
    - Ausführen der Tests
    - Kontrolle der Code Standards
- Trennung des Projektes in
    - JavaFX Anwendung
    - Bibliothek (gängiger: engl. Library) für evolutionäre Algorithmen
    - Die Bibliothek wird als Abhängigkeit (gängiger: engl. Dependency) in die JavaFX Anwendung eingebunden
- Verwendung von JavaFX für die GUI
    - Verwendung des Entwurfsmusters MVVM
    - Verzicht auf Verwendung von JavaFX Bibliotheken zu Beginn des Projektes
- Verwendung von Clean Architecture innerhalb der Bibliothek

## Code Standards

- Generelle Code-Standards, siehe [Cheatsheet von Jan Eric Schulze](https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/4-jschulze/JavaFX-BestPractices_1.pdf)
- Standards einer Bibliothek, siehe [Cheatsheet von Elias Anton](https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/6-eanton/Libraries-Cheatsheet_EliasAnton.pdf)
- Zu jeder Klasse eine Test-Klasse außer bei POJOs (siehe [Mockito Einführung](https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/5-bkillisch/src/test/java/workflowEval/AppTest.java))
- Code Standards abgeleitet aus den Object Calisthenics
    - Einrückungsebenen gering halten, lieber Methoden auslagern
    - Klassen sowie Methoden eher klein halten und dafür mehr Klassen/Methoden erzeugen
    - Namensgebung sollte kurz und prägnant sein, allerdings ohne Abkürzungen
- Generell möchten wir möglichst viel von dem [Maintainable Code Cheat Sheet](https://liviuoprisan.com/maintainable-code-cheat-sheet/) übernehmen, aber das ist relativ viel. Daher: Versucht euer Bestes, wenn ihr mal ein paar Richtlinien verletzt ist das nicht so schlimm. Wirklich problematische Dinge sollten dann im Merge Request auffallen. 

## (Technische) Definition of Done
- Checkstyle muss durchlaufen
- Testabdeckung mindestens 75%
- Tests laufen durch
- Ein zweiter Entwickler (oder Architekt) hat den Code gereviewed
- Code ist dokumentiert (Javadoc) -> nicht jedes Detail aber schwer verständliche Dinge, sowie Interfaces. In der Library nach den o.g. Richtlinien

## Happy Hacking!
![Random GIF](https://media.giphy.com/media/KmHueA88mFABT9GkkR/giphy.gif)
