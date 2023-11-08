- soketi = transportni sloj
- TCP - tri vej hensn
- UDP - aktivna veza dva ucesnika
    
    klijent --> syn --> server <br>
    klijent <-- ack <-- server <br>
    klijent --> ack --> server <br>

- da gadjamo i odredjeni port na kome se izvrsava aplikacija
- **listen** i **accept** su spojene = izrsavaju se blokirajuce
- uzimamo velike brojeve za portove
- odredjeni protokoli ce se izvrsavati na odredjenim portovima
- **netcat** radi sa TCP  = kako bi se simulirao rad jedne pa druge strane
  - **-l** port = gde pravimo

- **\r** stavi na pocetak

- visenitni program pravimo kada : treba nesto da nam se isplati
  - pravimo nit za vracanje odgovora