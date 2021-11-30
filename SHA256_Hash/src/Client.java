import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class Client {
    public static void main(String[] args) throws Exception {
        Socket s = null;
        try {
            s = new Socket("localhost", 3915);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner myobj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file name: ");

        String fileName = myobj.nextLine();  // Read user input

        assert s != null;
        IO io = new IO(s);

        /* encrypt client folder */
        String hashSHA256 = HashGen.generateHashOfFile(fileName, "SHA-256");
        String hashSHA1 = HashGen.generateHashOfFile(fileName, "SHA-1");
//        System.out.println(hashSHA1);
//        System.out.println(hashSHA256);
        /* send hash */
        io.write(hashSHA256);

        /* receive hash */
        String readHash = io.read();

        /* compare */
        if (hashSHA1.equals(readHash)) {
            System.out.println("File Systems are equal!");
        } else {
            System.out.println("File Systems are not equal!");
        }

        /* close socket */
        s.close();

    }

}