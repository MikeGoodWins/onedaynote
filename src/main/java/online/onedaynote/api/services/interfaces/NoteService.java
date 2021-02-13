package online.onedaynote.api.services.interfaces;

import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.dto.note.ParamsDto;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {

    NoteDto get(String key);

    NoteDto add(NoteCreate model);

    void delete();

    ParamsDto getParams();
}
