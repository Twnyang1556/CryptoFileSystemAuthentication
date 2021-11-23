import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class handles sending and receiving hashes
 */

public class IO {
    private Socket s;
    private DataOutputStream dout;
    private DataInputStream din;


    public IO (Socket s) {
        this.s = s;
        try {
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String str) {
        try {
            dout.writeUTF(str);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
             return din.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
