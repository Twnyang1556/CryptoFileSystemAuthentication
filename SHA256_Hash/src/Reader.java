import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;

/**
 * Reads from the file
 * The public function getNextBytes() will return the next 32 encrypted bytes of the directory
 */


public class Reader {
    private MessageDigest md;
    private File[] fileList;
    private int list_i;

    public Reader() throws Exception {
        this.md = MessageDigest.getInstance("SHA-256");
        this.fileList = new File("./").listFiles();
        this.list_i = 0;
    }

    public byte[] getNextBytes() {
        // TODO what happens if this is null?
        return md.digest(readNextFile());
    }

    /* reads the next file in the directory and returns its binary representation */
    // TODO This could be improved because it would need worst case 4gb memory
    private byte[] readNextFile() {
        if (list_i < fileList.length) {
            try {
                return Files.readAllBytes(Paths.get(fileList[list_i++].getPath()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* no files left */
        return null;
    }
}
