package online.onedaynote.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SomethingWentWrongException extends NoteException {
    public SomethingWentWrongException(String msg) {
        super(msg);
    }
}