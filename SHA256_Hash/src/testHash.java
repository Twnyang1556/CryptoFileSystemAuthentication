import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class testHash {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        File folder = new File("./");
        File[] fileList = folder.listFiles();
        MessageDigest md_Alice;
        String hex_Alice;
        assert fileList != null;
        for (File file : fileList) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                md_Alice = MessageDigest.getInstance("SHA-256");
                System.out.println(file.toString());
                hex_Alice = checksum(file.toString(), md_Alice);
                System.out.println(hex_Alice);
            }
        }
    }

    private static String checksum(String filepath, MessageDigest md) throws IOException {

        // file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

    }
}

