package online.onedaynote.api.services.implementations;

import java.util.Objects;
import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.dao.repositories.interfaces.NoteRepository;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.exceptions.NotFoundException;
import online.onedaynote.api.services.interfaces.NoteService;
import online.onedaynote.api.services.interfaces.NotificationService;
import online.onedaynote.api.utils.ParamUtils;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NotificationService notificationService;

    public NoteServiceImpl(
            NoteRepository noteRepository,
            NotificationService notificationService) {
        this.noteRepository = noteRepository;
        this.notificationService = notificationService;
    }

    @Override
    public NoteDto get(String key){

        Note note = noteRepository.findByKey(key);
        if(Objects.isNull(note)) throw new NotFoundException("Note not found");
        noteHandleRemove(note);
        noteHandleNotify(note);
        return note.toDto();
    }

    @Override
    public NoteDto add(NoteCreate model) {
        Note note = new Note(model, model.getKey().concat(ParamUtils.paramString(model)));
        noteRepository.save(note);
        return note.toDto();
    }

    private void noteHandleRemove(Note note){
        if(note.removable){
            noteRepository.delete(note.key);
        }
    }

    private void noteHandleNotify(Note note){
        if(note.needNotify){
             notificationService.send(note);
        }
    }
}
