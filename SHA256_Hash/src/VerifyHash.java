import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class VerifyHash implements Runnable {
    private Socket s;
    private DataInputStream din;
    public final int HASH_SIZE = 32; // because SHA-256 is 256 bits or 32 bytes

    private Deque<byte[]> queue;

    /* Constructor */
    public VerifyHash(Socket s) {
        this.s = s;
        queue = new ArrayDeque<byte[]>();
        try {
            din = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            /* receive hash */
            byte[] hash = new byte[HASH_SIZE];
            try {
                din.read(hash);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (hash == null) {
                System.out.println("The file systems are equal");
                break;
            }

            /* wait for queue to have an entry */
            while(queue.isEmpty());

            if (queue.getFirst() != hash) {
                /* hashes are different so the files systems are not equal */

                System.out.println("The file systems are not equal: ");
                break;

            }
        }


        /* close connection */
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void addHash(byte[] b) {
        queue.add(b);
    }


}
