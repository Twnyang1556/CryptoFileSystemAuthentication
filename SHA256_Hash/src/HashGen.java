import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashGen {

    public static String generateHashOfFolder(String folderName) {
        // Define folder name containing the passwords and store them into an array
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();
        assert(listOfFiles != null);
        Arrays.sort(listOfFiles);

        // Define variables to store digest, password text, and result from SHA-256 hash
        MessageDigest digest = null;
        String originalString;
        byte[] encodedHash = {};

        // Hash each text file containing passwords
        for (File file : listOfFiles) {
            // Only read files ending with ".txt"
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                originalString = readFileAsString(file.toString()) + bytesToHex(encodedHash);
                encodedHash = digest.digest(
                        originalString.getBytes(StandardCharsets.UTF_8));
//                System.out.println(file.toString() + ": " + bytesToHex(encodedHash));
            }
        }
        return bytesToHex(encodedHash);
    }

    // Function that reads a whole text file and returns a String or null if cannot be read
    private static String readFileAsString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            return null;
        }
    }

    // Converts the hashed result from byte to hex
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
