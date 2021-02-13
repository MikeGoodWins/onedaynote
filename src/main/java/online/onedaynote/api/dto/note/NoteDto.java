package online.onedaynote.api.dto.note;

import java.time.LocalDateTime;
import online.onedaynote.api.dao.entity.Note;

public class NoteDto {

    public long id;

    public String key;

    public int type;

    public String payload;

    public LocalDateTime created;

    public NoteDto(long id, String key, int type, LocalDateTime created) {
        this.id = id;
        this.key = key;
        this.type = type;
        this.created = created;
    }

    public NoteDto(Note note, String decryptedPayload) {
        this.id = note.getId();
        this.type = note.getNoteType();
        this.payload = decryptedPayload;
        this.created = note.created;
    }
}
