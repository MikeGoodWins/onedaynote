package online.onedaynote.api.config;

import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.spec.IvParameterSpec;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecureConstants {

    public static final String RAW_PARAMETER_NAME = "raw";
    public static final String IV_PARAMETER_NAME = "iv";

    private static byte[] encryptRaw;

    private static byte[] ivParam;

    private SecureConstants() {
    }

    public static byte[] generateEncryptRaw(){
        byte[] b = new byte[16];
        new Random().nextBytes(b);
        encryptRaw = b;
        return encryptRaw;
    }

    public static byte[] generateIvParam(){
        SecureRandom rnd = new SecureRandom();
        ivParam = new IvParameterSpec(rnd.generateSeed(16)).getIV();
        return ivParam;
    }

    public static byte[] getEncryptRaw(){
        return encryptRaw;
    }

    public static void setEncryptRaw(byte[] data){ encryptRaw = data; }

    public static byte[] getIvParamByte(){
        return ivParam;
    }

    public static IvParameterSpec getIvParam(){
        return new IvParameterSpec(ivParam);
    }

    public static void setIvParam(byte[] data){
        ivParam = data;
    }
}
