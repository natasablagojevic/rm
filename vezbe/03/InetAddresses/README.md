* Protokol = dogovor u komunikaciji (u internetu komuniciraju: kako odredjeni slojevi vide komunikaciju)
  * kako komunicira sa slojem sa druge strane
* IP protokol - svakoj mrezi dodelimo adresu (sa kim komuniciramo)
  * adresu modelujemo sa identifikatorom, ideja je da bude jedinstven
  * raspon: 32bita = 4B 
  * Cvor - Host(racunari) 
    * IPv4: 4B(32b)     | [0-255].[0-255].[0-255].[0-255]   128.123.3.4; 192.168.0.1
    * IPv6: 16B(128b)   | 23bf:2345:abcdef:2f3d:...:2234 

* **InetAddress**
* Singlton = to je design pattern; to je kada zelimo da imamo jednu instancu kalse aktivnu
* Factory method = u natklasi napravi staticki metod koji ce da primi string ili neke parametre i 
  on ce da odluci koju ce instancu da instancira na osnovu svojstava parametara

* DNS = da mu hostname i on mi da adresu, i posle znam sta cu da radim sa njim

* U Javi nemamo neoznacene tipove => imamo negativne (oni brojevi koji su presli 127)