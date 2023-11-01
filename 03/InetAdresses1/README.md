# IP ADRESE 

- IPv4: 192.168.0.1  (4B)
  - IPv6: abcd:1abc:...:1111 (32B)
    - abcd:0000:...:1111
    - abcd:0:...:1111
    - abcd:0:0:...:1111
    - abcd:0:ac25:0:...:111 // mozemo da izostavimo samo jednu nulu, ne mozemo uzastopne
- U javi ne postoje unsigned, od 128 se tumace kao negativni brojevi...
- LocalHost - imamo mogucnost da pristupamo racunaru kao da je on udaljen od nas preko interneta
- koristi se kada pravimo sajt, da gledamo spolja
- Opsezi adresa: 
  - 192.168.*.* - sve adrese koje pocinju sa 192.168 ; kada zelimo da imamo ceo opseg adresa; da postavimo pod mrezu
    - podmreze, koriste se u zgradi, da nadjemo lokaciju, geolokacija
  - CIDR standard A.B.C.D predvidjene da se koriste za to i to

- Java gleda lokalno sta se desava, ne ide direktno na internet, vec lokalno na racunaru
- hostname -I => dobija se lokalna adresa 
  - logovanje na ssh
  - staticku delimo u zgradi ili kuci sa vise racunaran = nismo jedinstveno identifikovani na internetu

- od sledece nedelje se razlikuju vezbe od proslogodisnjih ...
- 