package online.onedaynote.api.dto.note;

import java.time.LocalDateTime;
import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.dto.enums.NoteType;

public class NoteDto {

    public long id;

    public NoteType type;

    public String payload;

    public LocalDateTime created;

    public NoteDto(long id, LocalDateTime created) {
        this.id = id;
        this.created = created;
    }

    public NoteDto(Note note, String decryptedPayload) {
        this.id = note.getId();
        this.type = note.getNoteType();
        this.payload = decryptedPayload;
        this.created = note.created;
    }
}
