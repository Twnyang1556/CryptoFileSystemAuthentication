import java.net.*;
import java.io.*;
import java.util.Scanner;

class Server {


    public static void main(String[] args) throws Exception {

        /* initialize connection */
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(3915);
        } catch (IOException e) {
            System.exit(1);
        }

        Socket s = ss.accept();     //Accept connection to the socket

        Scanner myobj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file name: ");

        String fileName = myobj.nextLine();  // Read user input
        IO io = new IO(s);

        /* Hash server file folder */
        String hashSHA1 = HashGen.generateHashOfFile(fileName, "SHA-1");  //Generate hash for SHA-1
        String hashSHA256 = HashGen.generateHashOfFile(fileName, "SHA-256"); //Generate hash for SHA-256
//        System.out.println(hashSHA256);
//        System.out.println(hashSHA1);
        /* send hash to client*/
        io.write(hashSHA1);

        /* receive hash from client */
        String readHash = io.read();

        /* compare */
        if (hashSHA256.equals(readHash)) {
            System.out.println("File Systems are equal!");
        } else {
            System.out.println("File Systems are not equal!");
        }

        /* close socket */
        s.close();
        ss.close();
    }

}