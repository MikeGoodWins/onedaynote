package online.onedaynote.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteException extends RuntimeException {

    private final int code;

    public NoteException(final int code, final String msg) {
        super(msg);
        this.code = code;
    }

    public NoteException(final int code) {
        super();
        this.code = code;
    }

    public NoteException(final String msg) {
        super(msg);
        this.code = 0;
    }
}