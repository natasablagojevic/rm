import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public class SSLSocketsIntro {
  public static void main(String[] args) {
    SSLSocket socket = null;

    // opisuje stringove
    // Get supported cipher suites used for the encryption, of the following:
    // SSL_RSA_WITHRC4_128_MDS


  }

  class HandshakeInterfaceExample implements HandshakeCompletedListener {

    @Override
    public void handshakeCompleted(HandshakeCompletedEvent e) {
      SSLSession session = e.getSession();
      session.getProtocol();
      session.getPeerHost();

      e.getSession();
      e.getSocket();
      e.getCipherSuite();
    }
  }
}
