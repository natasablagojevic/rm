import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeClient {
    public static void main(String []args) {
        String hostname = "time.nist.gov";

        TimeZone gmt = TimeZone.getTimeZone("GMT");
        Calendar epochi1990 = Calendar.getInstance(gmt);
        epochi1990.set(1990, 0, 0, 0,0);
        Calendar epochi1970 = Calendar.getInstance(gmt);
        epochi1970.set(1970, 0, 0, 0, 0);

        long milisFrom1990 = epochi1990.getTimeInMillis();
        long milisFrom1970 = epochi1970.getTimeInMillis();

        long epochDiff = (milisFrom1970 - milisFrom1990)/1000;

        try (Socket socket = new Socket(hostname, 37);
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream())){

            long fromServer = 0;
            for (int i = 0; i < 4; i++) {
                fromServer = (fromServer << 8) | in.read();
            }

            Date answer = new Date((fromServer - epochDiff)*1000);

            System.out.println(answer);

        } catch (UnknownHostException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
    }
}
