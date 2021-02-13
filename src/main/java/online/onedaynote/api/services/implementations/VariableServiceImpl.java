package online.onedaynote.api.services.implementations;

import static online.onedaynote.api.config.SecureConstants.IV_PARAMETER_NAME;
import static online.onedaynote.api.config.SecureConstants.RAW_PARAMETER_NAME;


import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import javax.crypto.spec.IvParameterSpec;
import lombok.extern.slf4j.Slf4j;
import online.onedaynote.api.config.SecureConstants;
import online.onedaynote.api.dao.entity.Variable;
import online.onedaynote.api.dao.entity.VariableHistory;
import online.onedaynote.api.dao.repositories.repo.VariableHistoryRepository;
import online.onedaynote.api.dao.repositories.repo.VariableRepository;
import online.onedaynote.api.exceptions.ConflictException;
import online.onedaynote.api.services.interfaces.VariableService;
import online.onedaynote.api.utils.TimeUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VariableServiceImpl implements VariableService {

    private final VariableRepository variableRepository;
    private final VariableHistoryRepository variableHistoryRepository;

    public VariableServiceImpl(
            VariableRepository variableRepository,
            VariableHistoryRepository variableHistoryRepository) {
        this.variableRepository = variableRepository;
        this.variableHistoryRepository = variableHistoryRepository;
    }

    public byte[] getEncryptRaw(){
        if(Objects.isNull(SecureConstants.getEncryptRaw())
                || SecureConstants.getEncryptRaw().length == 0){
            SecureConstants.setEncryptRaw(getOrGenerate(RAW_PARAMETER_NAME));
        }
        return SecureConstants.getEncryptRaw();
    }

    public IvParameterSpec getIv(){
        if(Objects.isNull(SecureConstants.getIvParamByte())
                || SecureConstants.getIvParamByte().length == 0){
            SecureConstants.setIvParam(getOrGenerate(IV_PARAMETER_NAME));
        }
        return SecureConstants.getIvParam();
    }

    private byte[] getOrGenerate(String key){

        log.info("*** Get new security param " + key);
        byte[] result;
        String today = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(TimeUtils.now());
        Variable vars = variableRepository.findByKeyAndSimpleDate(key, today);
        if(Objects.nonNull(vars)){
            log.info("*** Security param " + key + " was found in database");
            result = Base64.getDecoder().decode(vars.getValue());
        }else{
            log.info("*** Security param " + key + " wasn't found in database. Generate new value");
            result = generateAndSaveParam(key);
        }
        return result;
    }

    @Override
    public void deleteAnotherDaysParams(){

        String today = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(TimeUtils.now());
        List<Variable> beforeVariables = variableRepository.findAllBySimpleDateBefore(today);
        if(!beforeVariables.isEmpty()){
            variableRepository.deleteAll(beforeVariables);
        }
    }

    private  byte[] generateAndSaveParam(String key){
        byte[] result;
        if(key.equals(RAW_PARAMETER_NAME)){
            result = SecureConstants.generateEncryptRaw();
        } else if (key.equals(IV_PARAMETER_NAME)){
            result = SecureConstants.generateIvParam();
        } else {
            throw new ConflictException("Unknown variable name");
        }
        Variable variable = variableRepository.save(new Variable(key,
                Base64.getEncoder().encodeToString(result)));
        variableHistoryRepository.save(new VariableHistory(variable));
        return result;
    }
}
