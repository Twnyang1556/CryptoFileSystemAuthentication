import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Repeatedly sends hashes
 */

public class SendHash implements Runnable {
    private Socket s;
    private DataOutputStream dout;


    /* constructor */
    public SendHash(Socket s) {
        this.s = s;
        try {
            this.dout = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter folder name:");

        String folderName = myObj.nextLine();  // Read user input

        String hash = null;
        try {
            hash = getNextHash(folderName);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            assert hash != null;
            dout.writeUTF(hash);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO
    public synchronized String getNextHash(String folderName) throws IOException, NoSuchAlgorithmException {
        return testHash.generateHashOfFolder(folderName);
    }
}
