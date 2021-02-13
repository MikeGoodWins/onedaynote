package online.onedaynote.api.dto.note;

import java.time.LocalDateTime;
import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.dto.enums.NoteType;

public class NoteDto {

    public long id;

    public String key;

    public NoteType type;

    public String payload;

    public LocalDateTime created;

    public NoteDto(long id, String key, int type, LocalDateTime created) {
        this.id = id;
        this.key = key;
        this.type = NoteType.getType(type);
        this.created = created;
    }

    public NoteDto(Note note, String decryptedPayload) {
        this.id = note.getId();
        this.type = NoteType.getType(note.getNoteType());
        this.payload = decryptedPayload;
        this.created = note.created;
    }
}
