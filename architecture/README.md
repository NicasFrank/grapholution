# Übersicht technologischer Entscheidungen

## Aufträge für den Technologie Workshop

- siehe PDF
- Motivation: Aufträge sind dafür da, um Grundverständnis für Libraries aufzubauen und Umsetzungsmöglichkeiten zu finden
- Link zu den Aufträgen im Discord
- Was schon feststeht
    - Multimodulprojekt mit Gradle
    - EvoAlgo mit Pipes and Filters
    - GUI mit JavaFX

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

## Architektur Workshop (23.11.21, Praktikum II)

siehe https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/workshop/Architektur-Workshop.md

## Code Standards und Definition of Done

siehe https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/code/README.md
