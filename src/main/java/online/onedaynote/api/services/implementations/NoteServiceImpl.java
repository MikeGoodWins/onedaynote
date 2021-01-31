package online.onedaynote.api.services.implementations;

import java.util.Optional;
import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.dao.repositories.interfaces.NoteRedisRepository;
import online.onedaynote.api.dao.repositories.repo.NoteRepositoryImpl;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.exceptions.NotFoundException;
import online.onedaynote.api.services.interfaces.NoteService;
import online.onedaynote.api.services.interfaces.NotificationService;
import online.onedaynote.api.utils.ParamUtils;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    //private final NoteRedisRepository noteRedisRepository;
    private final NoteRepositoryImpl noteRepository;
    private final NotificationService notificationService;

    public NoteServiceImpl(
            NoteRedisRepository noteRedisRepository,
            NoteRepositoryImpl noteRepository,
            NotificationService notificationService) {
        //this.noteRedisRepository = noteRedisRepository;
        this.noteRepository = noteRepository;
        this.notificationService = notificationService;
    }

    @Override
    public NoteDto get(String key){

        Optional<Note> note = noteRepository.findByKey(key);
        if(note.isEmpty()) throw new NotFoundException("Note not found");
        noteHandleNotify(note.get());
        noteHandleRemove(note.get());
        return note.get().toDto();
    }

    @Override
    public NoteDto add(NoteCreate model) {
        Note note = new Note(model, model.getKey().concat(ParamUtils.paramString(model)));
        noteRepository.save(note);
        //noteRedisRepository.save(note);
        return note.toDto();
    }

    private void noteHandleRemove(Note note){
        if(note.removable){
            noteRepository.delete(note);
            //noteRedisRepository.delete(note.key);
        }
    }

    private void noteHandleNotify(Note note){
        if(note.needNotify){
             notificationService.send(note);
        }
    }
}
