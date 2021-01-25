package online.onedaynote.api.services.interfaces;

import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;

public interface NoteService {

    NoteDto get(String key);

    NoteDto add(NoteCreate model);
}
