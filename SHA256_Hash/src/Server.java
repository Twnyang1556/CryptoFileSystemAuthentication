import java.net.*;
import java.io.*;

class Server {


    public static void main(String args[]) throws Exception {

        /* initialize connection */
        ServerSocket ss = new ServerSocket(3915);
        Socket s = ss.accept();

        String folderName = "./Bob";
        IO io = new IO(s);

        /* encrypt client folder */
        String sha1Hash = HashGen.generateHashOfFolder(folderName, "SHA-1");

        /* send hash */
        io.write(sha1Hash);

        /* receive hash */
        String readHash = io.read();

        /* calculate SHA-256 */
        String sha256Hash = HashGen.generateHashOfFolder(folderName, "SHA-256");

        /* compare */
        if (sha256Hash.equals(readHash)) {
            System.out.println("File Systems are equal!");
        } else {
            System.out.println("File Systems are not equal!");
        }

        /* close socket */
        s.close();
        ss.close();
    }
}