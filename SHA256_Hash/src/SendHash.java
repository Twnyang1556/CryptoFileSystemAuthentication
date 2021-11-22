import java.io.*;
import java.net.Socket;

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

        String hash = getNextHash();

        try {
            dout.writeUTF(hash);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO
    public synchronized String getNextHash() {
        return "test";
    }
}
