# Gymnotus - TDT4140, gruppe 32
Dette er repoet for treningsoppfølgingsprogrammet kalt Gymnotus. Applikasjonen lar personlige trenere følge opp treningen og helsen til kundene til appen gjennom analyse av trening- og helsedata som brukeren sender inn.

Utviklingen av applikasjonen Gymnotus startet i våren 2018 som del av en prosjektoppgavet i faget TDT4140 - Programvareutvikling. Oppgaven omhandler i korte trekk om å lage infrastruktur for "smart liv og helse". Denne infrastrukturen skal samle liv-, aktivitet- og helseinformasjon om deltakende, samtykkende og ganske friske personer eller ting knyttet til personer.

Gruppe 32 sitt forslag på et produkt som skal tillate en bruker å gjøre dette i et "treningsstudio setting" er applikasjonen Gymnotus. 

## Beskrivelse av programmet

I programmet er det mulig å registrere seg som to forskjellige brukere, "trener" og "bruker". Disse brukerne vil i utgangspunktet ha forskjellige funksjonaliteter på sine
grensesnitt, men i applikasjonen er det bare grensesnitt for trenere.

Trenerne skal kunne følge brukerne når de trener på treningsprogrammene som ligger inne
i applikasjonen. Kunden rapporterer inn data fra treningsøkter samt daglig helsedata. Både trener og kunde kan så få frem dataene visualisert og ikke-visualisert for å kunne tolke kundens treningsprogresjon.



## Avhengigheter
Du vil trenge det følgende for å kjøre programmet:
 - [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
 - [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Bygging av programmet

For å bygge programmet gjennom terminal må du først passe på at du er i mappen `32/tdt4140-gr1832`. Deretter kjører du følgende kommando for å bygge programmet:
```sh
$ mvn clean install -pl app.ui -am
```

Om du bare ønsker å bygge programmet, uten å kjøre testene, kan du kjøre følgende kommando:

```sh
$ mvn clean install -Dmaven.test.skip -pl app.ui -am
```
For å bygge programmet i et ønsket utviklingsmiljø må du passe på å få gått gjennom `clean` og `install` som maven-livssykluser.
I Eclipse kan du gjøre dette ved å høyreklikke [pom.xml](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/32/blob/master/tdt4140-gr1832/pom.xml) som ligger i `32/ tdt4140-gr1832` mappen og gjøre følgende:
1. Run as > Maven clean
2. Run as > Maven install

## Kjøre programmet

For å kjøre Gymnotus gjennom terminalen så kan du gjøre det følgende. Pass på at du befinner deg i mappen `32/tdt4140-gr1832`:

```sh
$ mvn exec:java -pl app.ui -Dexec.mainClass=FxApp
```

Dersom du skal kjøre programmet gjennom et ønsket utviklingsmiljø så må du kjøre main-funksjonen i [FxApp.java](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/32/blob/master/tdt4140-gr1832/app.ui/src/main/java/tdt4140/gr1832/app/ui/FxApp.java).
Med Eclipse så kan du gjøre dette ved å høyreklikke filen og velge Run as > Run as java-application.