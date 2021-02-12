package online.onedaynote.api.services.interfaces;

import javax.crypto.spec.IvParameterSpec;
import online.onedaynote.api.dao.entity.Variable;
import org.springframework.stereotype.Service;

@Service
public interface VariableService {

    byte[] getEncryptRaw();

    IvParameterSpec getIv();

    void deleteAnotherDaysParams();
}
