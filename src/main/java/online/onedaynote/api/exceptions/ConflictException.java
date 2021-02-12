package online.onedaynote.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends NoteException {
    public ConflictException(String msg) {
        super(msg);
    }
}