import java.net.*;
import java.io.*;

class Client {
    public static void main(String args[]) throws Exception {
        long startTime = System.currentTimeMillis();

        Socket s = new Socket("localhost", 3915);
        String folderName = "./Bob";
        IO io = new IO(s);

        /* encrypt client folder */
        String sha256Hash = HashGen.generateHashOfFolder(folderName, "SHA-256");

        /* send hash */
        io.write(sha256Hash);

        /* receive hash */
        String readHash = io.read();

        /* calculate sha1 to compare */
        String sha1Hash = HashGen.generateHashOfFolder(folderName, "SHA-1");

        /* compare */
        if (sha1Hash.equals(readHash)) {
            System.out.println("File Systems are equal!");
        } else {
            System.out.println("File Systems are not equal!");
        }

        /* close socket */
        s.close();

        System.out.println("Runtime in ms: " + (System.currentTimeMillis() - startTime));

    }

}