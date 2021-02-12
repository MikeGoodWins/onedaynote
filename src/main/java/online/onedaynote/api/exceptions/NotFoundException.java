package online.onedaynote.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends NoteException {
    public NotFoundException(String msg) {
        super(msg);
    }
}