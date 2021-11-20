import java.net.*;
import java.io.*;

class Server {


    public static void main(String args[]) throws Exception {

        /* initialize connection */
        ServerSocket ss = new ServerSocket(3915);
        Socket s = ss.accept();


        /* start sending hashes */
        SendHash sendHash = new SendHash(s);
        Thread sender = new Thread(sendHash);
        sender.start();

        /* start receiving and validating hashes */
        VerifyHash verifyHash = new VerifyHash(s);
        Thread receiver = new Thread(verifyHash);
        receiver.start();

        /* wait for threads to finish */
        sender.join();
        receiver.join();

        /* close sockets */
        s.close();
        ss.close();
    }
}