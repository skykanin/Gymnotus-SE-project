I denne mappen finner du for det meste kode som omhandler backend-delen av applikasjonen. Dette er koden som holder styr på databasen og passer på at kode blir lagret riktig og sendt riktig fra serveren.

## Avhengigheter

Serveren er kjører på en [Jetty-servlet](https://www.eclipse.org/jetty/) og bruker [Jersey](https://jersey.github.io/) til å tilby opp et REST-api. 
Dette er to rammeverk som bevisst ble valgt med tanke på hvor enkelt det er å sette opp, syntaks, robusthet og forståelighet.

Det skal ikke være nødvendig å manuelt installere disse rammeverkene da Maven laster ned og installerer rammeverkene for deg når man bygger systemet. 

## Bygg og kjør din egen server

Dersom du ønsker å starte opp serveren gjennom en terminal kan du gjøre som følgende:

Kom deg inn i stien kalt `32/tdt4140-gr1832`:

1. Kjør `$ mvn clean install -pl web.server` for å bygge programmet samt. rydde vekk utdaterte avhengigheter.
2. Kjør så `$ mvn exec:java -pl web.server -Dexec.mainClass=ServerThread` for å spawne en server som kjører på port 8080.

Dersom du ønsker å kjøre serveren gjennom et ønsket utviklingsmiljø, må du på tilsvarende måte passe på at du går gjennom `clean instalL` som en "maven lifecycle".
Etter det er det bare å kjøre main-funksjonen i `ServerThread`-klassen. Med Eclipse er det bare å høyreklikke filen og velge "Run as > Java application" på ` ServerThread.java`.

## Struktur og design av backend

Siden all strukturen mellom entitene/tabellene ligger i databasen trenger det ikke nødvendigvis å være akkurat de samme strukturene mellom de implementerte klassene. Dette har simplifisert kodestrukturen og har gjort sånn at vi kan strukurere klassene til å uavhengige datagiver klasser for klientene. Dette ble gjort lettere med JAX sine annotasjoner på GET- og POST-metoder. Klassene bruker så og si ingen minne, da det(foreløpig) dataen ikke buffres på serveren av hensyn til begrenset plass på Digital Ocean-serveren vi benytter for å holde serveren oppe.

Backend er delt opp i tre deler; server, data access object(DAO) og data. Server inneholder all nødvendig kode for databasetilgang og server-funksjonaliteter. DAO er "tjenestene" som blir gitt til omverdenen, dvs. i DAO-klassene finner man metoder som sender data til/fra serveren og databasen ved forespørsler. DAO-klassene representerer entitene/tabellene ved å lagre dem i såkalte "data/container-klasser" som rett og slett er minimale lagringsplasser for de relevante feltene til tabellene/entitetene. Dermed har vi per DAO-klasse en tilsvarende data/container-klasse som (mellom)lagrer tilhørende data.