* Sinhronizacija pomocu katanaca sa zakljucavanjem

* imamo dodatnu mogucnost sa zakljucavanjima je ta da: 

* await = mora se imati zakljucan katanac pre poziva metoda await 
* NAKON *LOCK* IMA NEKI *TRY* BLOK I U *FINALLY* DELU OTKLJUCAJ TO STO SI ZAKLJUCAO
* SIGNALALL = POZIV ZA AWAIT -> PROBUDICE SE JEDNA OD NITI, ALI CE NASTAVITI DALJE ONA NIT CIJI JE USLOV ISPUNJEN A SVE
  OSALE CE 

* KATANCI SU NAJPRIMITIVNIJI, ALI SE NE MORAJU RADITI. POSTOJE DRUGI NACINI NA KOJE MOZEMO SINHRONIZOVATI
  PODATKE