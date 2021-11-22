import java.net.*;
import java.io.*;

class Client {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 3915);
        String folderName = "./Alice";

        /* encrypt client folder */


        /* close socket */
        s.close();

    }
}