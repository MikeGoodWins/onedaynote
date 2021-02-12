package online.onedaynote.api.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface CryptService {

    String encrypt(String payload);

    String decrypt(String payload);
}
