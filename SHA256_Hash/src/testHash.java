import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;

public class testHash {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Path path = Paths.get("/Users/jeremy/Desktop/CryptoFileSystemAuthentication/KingJamesBible.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        String line = Files.readAllLines(path).get(0);
        String m1 = line;
        String m2 = line;
        System.out.println(m1);
        byte[] c1 = digest.digest(
                m1.getBytes(StandardCharsets.UTF_8));
        byte[] c2 = digest.digest(
                m2.getBytes(StandardCharsets.UTF_8));
        System.out.println(bytesToHex(c1));
        System.out.println(bytesToHex(c2));
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

