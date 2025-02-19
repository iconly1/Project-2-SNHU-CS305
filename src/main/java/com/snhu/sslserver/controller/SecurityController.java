package com.snhu.sslserver.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

@RestController
public class SecurityController {

    @GetMapping("/hash")
    public String getHash(@RequestParam(value = "data", defaultValue = "Artemis Financial Secure Transfer") String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return "Data: " + data + "<br>SHA-256 Hash: " + hexString.toString();
        } catch (Exception e) {
            return "Error generating hash: " + e.getMessage();
        }
    }
}
