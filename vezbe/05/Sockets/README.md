- **soket** je apstrakcija veza izmedju dva hosta;<br>
 zelimo da iz naseg programa vrsimo komunikaciju preko mreze, na isti <br>
 nacin kao da pisemo u fajl
  - ideja je da moze da izvrsi neke operacije
  - koristeci sitemske pozive ili neke druge funkije
  - operacije nad soketom:
    1. connect
    2. send
    3. receive
    4. close (client)
    5. bind (server) (radimo eksplicitno)
    6. listen (server) (blokirajuci poziv)
    7. accept (server)
  - komunikacija se vrsi u oba smera
  