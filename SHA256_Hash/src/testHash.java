import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class testHash {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String aliceFolderHash = generateHashOfFolder("./Alice");
        String bobFolderHash = generateHashOfFolder("./Bob");
        System.out.println(aliceFolderHash);
        System.out.println(bobFolderHash);
    }

    private static String generateHashOfFolder(String folderName) throws NoSuchAlgorithmException, IOException {
        // Define folder name containing the passwords and store them into an array
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();
        assert(listOfFiles != null);
        Arrays.sort(listOfFiles);

        // Define variables to store digest, password text, and result from SHA-256 hash
        MessageDigest digest;
        String originalString;
        byte[] encodedHash = {};

        // Hash each text file containing passwords
        for (File file : listOfFiles) {
            // Only read files ending with ".txt"
            if (file.isFile() && file.getName().endsWith(".txt")) {
                digest = MessageDigest.getInstance("SHA-256");
                originalString = readFileAsString(file.toString()) + bytesToHex(encodedHash);
                encodedHash = digest.digest(
                        originalString.getBytes(StandardCharsets.UTF_8));
//                System.out.println(file.toString() + ": " + bytesToHex(encodedHash));
            }
        }
        return bytesToHex(encodedHash);
    }

    // Function that reads a whole text file and returns a String
    private static String readFileAsString(String fileName) throws IOException
    {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
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

