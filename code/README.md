# Code Standards

- Generelle Code-Standards, siehe [Cheatsheet von Jan Eric Schulze](https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/4-jschulze/JavaFX-BestPractices_1.pdf)
- Standards einer Bibliothek, siehe [Cheatsheet von Elias Anton](https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/6-eanton/Libraries-Cheatsheet_EliasAnton.pdf)
- Zu jeder Klasse eine Test-Klasse außer bei POJOs (siehe [Mockito Einführung](https://gitlab.imn.htwk-leipzig.de/weicker/grapholution/-/blob/master/architecture/5-bkillisch/src/test/java/workflowEval/AppTest.java))
- Code Standards abgeleitet aus den Object Calisthenics
    - **Einrückungsebenen gering halten**, lieber Methoden auslagern
    - **Zeilenumfang** von Klassen sowie Methoden eher **klein halten** und dafür mehr Klassen/Methoden erzeugen
    - Namensgebung sollte **kurz und prägnant** sein, allerdings ohne Abkürzungen
- Generell möchten wir möglichst viel von dem [Maintainable Code Cheat Sheet](https://liviuoprisan.com/maintainable-code-cheat-sheet/) übernehmen, aber das ist relativ viel. Daher: Versucht euer Bestes, wenn ihr mal ein paar Richtlinien verletzt ist das nicht so schlimm. Wirklich problematische Dinge sollten dann im Merge Request auffallen. 

## Anmerkungen basierend auf Code Reviews

### Generell
- Membervariablen von Klassen sollten wann immer möglich "**private final**" sein ([Warum?](https://softwareengineering.stackexchange.com/a/98703))
- Als Variablen-Typ sollten wenn möglich immer die **generischen Typen** verwendet werden. Bspw. List statt ArrayList ([Warum?](https://stackoverflow.com/a/2279059))
- Methoden die einen Boolean zurückgeben sollten mit "**has**" oder "**is**" beginnen und beschreiben was sie prüfen. Bspw. isItTeaTime(Time time)
- Auf Branches auf denen mehrere Entwickler arbeiten sollte nur Code gepusht werden, der **lauffähig** ist und bei dem die **Tests grün** sind

### Testspezifisch
- Test-Namen sollten **ausdrücken was sie tun** nach dem Schema "UnitOfWork_StateUnderTest_ExpectedBehavior" ([Warum?](https://stackoverflow.com/a/1594049))
- Tests sollten nach Muster "**Given -> When -> Then**" aufgebaut sein (siehe [Modern Best Practices for Java Testing](https://phauer.com/2019/modern-best-practices-testing-java/))

# (Technische) Definition of Done
- Checkstyle muss durchlaufen
- Testabdeckung mindestens 75%
- Tests laufen durch
- Ein zweiter Entwickler (oder Architekt) hat den Code gereviewed
- Code ist dokumentiert (Javadoc) -> nicht jedes Detail aber schwer verständliche Dinge, sowie Interfaces. In der Library nach den o.g. Richtlinien

# Happy Hacking!
![Random GIF](https://media.giphy.com/media/KmHueA88mFABT9GkkR/giphy.gif)
