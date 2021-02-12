package online.onedaynote.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends NoteException {
    public BadRequestException(String msg) {
        super(msg);
    }
}