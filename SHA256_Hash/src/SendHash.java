import java.io.*;
import java.net.Socket;

/**
 * Repeatedly sends hashes
 */

public class SendHash implements Runnable {
    private Socket s;
    private DataOutputStream dout;
    private VerifyHash verifier;
    private Reader reader;


    /* constructor */
    public SendHash(Socket s, VerifyHash verifier) throws Exception{
        this.s = s;
        this.verifier = verifier;
        this.reader = new Reader();
        this.dout = new DataOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        byte[] hash;
        do {
            hash = reader.getNextBytes();

            try {
                dout.write(hash);
                dout.flush();

                /* when sending a generated hash, give it to the verify class so it can compare to received hashes */
                verifier.addHash(hash);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (hash != null);
    }

}
