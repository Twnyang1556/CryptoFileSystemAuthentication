import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class VerifyHash implements Runnable {
    private Socket s;
    private DataInputStream din;


    /* Constructor */
    public VerifyHash(Socket s) {
        this.s = s;
        try {
            din = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


        String hash = "";
        try {
            hash = din.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("received " + hash);

        /* close connection */

        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
