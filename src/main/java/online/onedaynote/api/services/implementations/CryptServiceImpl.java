package online.onedaynote.api.services.implementations;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import online.onedaynote.api.exceptions.SomethingWentWrongException;
import online.onedaynote.api.services.interfaces.CryptService;
import online.onedaynote.api.services.interfaces.VariableService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CryptServiceImpl implements CryptService {

    private final VariableService variableService;

    public CryptServiceImpl(
            VariableService variableService) {
        this.variableService = variableService;
    }

    @Override
    public String encrypt(String payload) {
        log.info("*** Encrypt begin");
        try {
            SecretKeySpec keySpec = new SecretKeySpec(
                    variableService.getEncryptRaw(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, variableService.getIv());
            byte[] original = cipher.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            log.info("*** Encrypt end successfully");
            return Base64.getEncoder().encodeToString(original);
        } catch (Exception ex) {
            log.error("Encrypt error");
            ex.printStackTrace();
            throw new SomethingWentWrongException("Crypt exception" + ex.getLocalizedMessage());
        }
    }

    @Override
    public String decrypt(String payload) {
        log.info("*** Decrypt begin");
        try {
            SecretKeySpec keySpec = new SecretKeySpec(variableService.getEncryptRaw(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, variableService.getIv());
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(payload));
            log.info("*** Decrypt end successfully");
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("Decrypt error");
            ex.printStackTrace();
            throw new SomethingWentWrongException("Crypt exception" + ex.getLocalizedMessage());
        }
    }
}
