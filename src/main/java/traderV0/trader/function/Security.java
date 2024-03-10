package traderV0.trader.function;

import org.springframework.stereotype.Component;
import traderV0.trader.constant.GameConst;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Security {
    public String loginHashing(String before){
        String sha256 = "";
        try{
            MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
            mdSHA256.update(before.getBytes("UTF-8"));
            byte[] sha256Hash = mdSHA256.digest();

            StringBuffer hexSHA256hash = new StringBuffer();
            for(byte b : sha256Hash){
                String hexString = String.format("%02x",b);
                hexSHA256hash.append(hexString);
            }
            sha256 = hexSHA256hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return sha256;
    }
    public boolean isSessionValidDate(LocalDateTime sessionDate){
        if (Duration.between(LocalDateTime.now(), sessionDate).toMinutes() <= GameConst.maximumSessionMinute){
            return true;
        }else {
            return false;
        }
    }
}
