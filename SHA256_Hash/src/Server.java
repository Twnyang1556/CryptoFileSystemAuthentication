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
        String myHash = HashGen.generateHashOfFolder(folderName);

        /* send hash */
        io.write(myHash);

        /* receive hash */
        String readHash = io.read();

        /* compare */
        if (myHash.equals(readHash)) {
            System.out.println("File Systems are equal!");
        } else {
            System.out.println("File Systems are not equal!");
        }

        /* close socket */
        s.close();
        ss.close();
    }
}