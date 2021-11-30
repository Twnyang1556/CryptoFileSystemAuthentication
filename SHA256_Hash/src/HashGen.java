import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashGen {
    public static void main(String[] args) {
        System.out.println(generateHashOfFile("../untitled/E_coli.txt", "SHA-1"));
    }
    public static String generateHashOfFile(String fileName, String hashFunction) {

        // Define variables to store digest, password text, and result from SHA-256 hash
        MessageDigest digest = null;
        String originalString;
        byte[] encodedHash = {};

        // Hash the file containing passwords
        try {
            digest = MessageDigest.getInstance(hashFunction);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            digest = MessageDigest.getInstance(hashFunction);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        originalString = readFileAsString(fileName) + bytesToHex(encodedHash);
        encodedHash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));

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
