import java.net.*;
import java.io.*;

class Client {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 3915);

        /* start receiving and validating hashes */
        VerifyHash verifyHash = new VerifyHash(s);
        Thread receiver = new Thread(verifyHash);
        receiver.start();

        /* start sending hashes */
        SendHash sendHash = new SendHash(s, verifyHash);
        Thread sender = new Thread(sendHash);
        sender.start();

        /* wait for threads to finish */
        sender.join();
        receiver.join();

        /* close socket */
        s.close();

    }
}