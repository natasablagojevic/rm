import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketsIntro {
    public static void main(String[] args) {
        // vrsi se na dve strane:
        // connect
        // recv
        // send
        // close

        // bind - rezervisemo port
        // listen
        // accept = prihvatanje od srane servera sa klijentom

        /**
         * da otvorimo stream-ove i da njih koristimo
         */

        try {
            Socket s = new Socket("www.matf.bg.ac.rs", 80); // uvek koristimo za klijente
            System.out.println(s);
            System.out.println(s.getInetAddress());
            System.out.println(s.getLocalPort());


            s.close(); // uvek zatvarati sokete!!!
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ServerSocket ss = new ServerSocket(12345)) {
            // cekamo odredjenog klijenta ..
            // blokirajuce - time out
            /**
             * potreban je kiljentski soket ;
             * potreban lokalni port iz klijenta kako bismo sve mogli da radimo ..
             *
             */
            Socket clinet =  ss.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
