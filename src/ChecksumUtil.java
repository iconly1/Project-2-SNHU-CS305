import java.security.MessageDigest;

public class ChecksumUtil {
    public static String generateChecksum(String data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) throws Exception {
        String data = "Artemis Financial Secure Transfer";
        String checksum = generateChecksum(data);
        System.out.println("Data: " + data);
        System.out.println("SHA-256 Checksum: " + checksum);
    }
}
