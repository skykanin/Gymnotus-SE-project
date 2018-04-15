I denne mappen finner du for det meste kode som omhandler core-delen av applikasjonen.
Core-delen tilbyr hovedsaklig logisk funksjonalitet til Applikasjonen. 

## Avhengigheter

I app.core bruker vi Gson til å konvertere fra Json, som er formatet serveren bruker når data sendes.

## Struktur og design av app.core

App.core er delt opp i tre mapper:
1.    **src/main/java**
    
    Dette er selve koden for funksjonalitet på kliensiden av serveren. Den består igen av tre pakker;

        * tdt4140.gr1832.app.core: Dette er pakken som består av alle ulike App klasser som er knyttet til applikasjonens vinduer. 
                                    Disse står for http inhenting(getRequest) og sending(PostRequest) av informasjon. 
                                    Disse klassene er altså bindeleddet mellom Kontrollerene og selve dataen. 
                                    De manipulerer dataen til ønsket format før den sendes videre til kontroller. 
                                    Her er det viktig å påpeke at disse ikke har noe ansvar for databasehandling. 
                                    Dette gjøres på serversiden, altså i Web pakken. 
                                    
        * containers:              Denne pakken består av containerene som tar til seg informasjonen fra json objektene når App klassene får svar av http requestene. 
                                    Disse klassen gjør convertering enkelt ved at de er på samme format som containerene på server siden, 
                                    og derfor skjer converteringen fra json til håndterbar informasjon automatisk.
       
        * comparators:              Denne pakken innholder bare to klasser som implementerer comparators grensesnittet. 
                                    Disse er hjelpe klasser til App klasser hvor sortering av data er ndvendig. 
        
2.  **src/test/java**

    Denne mappen består av en tdt4140.gr1832.app.core pakke. Denne pakken inneholder testklasser som tester java koden i Core. Her er det en test klasse for tilhørende App klasse. Siden App klassene er avhengig av sine tilhørende containere testes disse i samme test for App klassen. 
3.    **src/test/reources**

    Denne mappen skal brukes for å teste hjelpekode. Vi har ikke brukt noe slik kode i core, siden core bare er logikk med javaklasser er ikke slike klasser nødvendig.