package online.onedaynote.api.services.implementations;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import online.onedaynote.api.dao.entity.Note;
import online.onedaynote.api.dao.entity.NoteHistory;
import online.onedaynote.api.dao.repositories.repo.NoteHistoryRepository;
import online.onedaynote.api.dao.repositories.repo.NoteRepository;
import online.onedaynote.api.dto.enums.Action;
import online.onedaynote.api.dto.enums.Animal;
import online.onedaynote.api.dto.enums.Color;
import online.onedaynote.api.dto.enums.Definition;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.dto.note.ParamsDto;
import online.onedaynote.api.exceptions.NotFoundException;
import online.onedaynote.api.services.interfaces.CryptService;
import online.onedaynote.api.services.interfaces.NoteService;
import online.onedaynote.api.services.interfaces.NotificationService;
import online.onedaynote.api.utils.ParamUtils;
import online.onedaynote.api.utils.TimeUtils;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteHistoryRepository noteHistoryRepository;
    private final NotificationService notificationService;
    private final CryptService cryptService;

    public NoteServiceImpl(
            NoteRepository noteRepository,
            NoteHistoryRepository noteHistoryRepository,
            NotificationService notificationService,
            CryptService cryptService) {
        this.noteRepository = noteRepository;
        this.noteHistoryRepository = noteHistoryRepository;
        this.notificationService = notificationService;
        this.cryptService = cryptService;
    }

    @Override
    public NoteDto get(String key){

        String encryptedKey = cryptService.encrypt(key);
        Optional<Note> note = noteRepository.findByKey(encryptedKey);
        if(note.isEmpty()) throw new NotFoundException("Note not found");
        noteHandleNotify(note.get());
        noteHandleRemove(note.get());
        Note result = note.get();
        String decryptedPayload = cryptService.decrypt(result.getPayload());
        return new NoteDto(result, decryptedPayload);
    }

    @Override
    public NoteDto add(NoteCreate model) {
        NoteDto result;
        String fullKey = model.getKey().concat(ParamUtils.paramString(model));
        String key = cryptService.encrypt(fullKey);
        Optional<Note> existsNote = noteRepository.findByKey(key);
        Note note;
        if(existsNote.isEmpty()){
            String payloadString = cryptService.encrypt(model.getPayload());
            note = new Note(key, payloadString, model);
        } else {
            note = existsNote.get();
            note.setPayload(cryptService.encrypt(model.getPayload()));
            note.setNoteType(model.getType());
            note.setNeedNotify(model.isNeedNotify());
            note.setNotifyEmail(model.getNotifyEmail());
            note.setRemovable(model.isRemovable());
            note.setCreated(TimeUtils.now());
        }
        noteRepository.save(note);
        noteHistoryRepository.save(new NoteHistory(note));
        result = new NoteDto(note.getId(), note.getCreated());
        return result;
    }

    @Override
    public void delete() {

        String today = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(TimeUtils.now());
        List<Note> yesterdayNotes = noteRepository.findAllBySimpleDateBefore(today);
        if(!yesterdayNotes.isEmpty()){
            noteRepository.deleteAll(yesterdayNotes);
        }
    }

    @Override
    public ParamsDto getParams() {

        String[] definitionsArray = Definition.getValues();
        String[] colorsArray = Color.getValues();
        String[] animalsArray = Animal.getValues();
        String[] actionsArray = Action.getValues();
        return new ParamsDto(definitionsArray, colorsArray, animalsArray, actionsArray);
    }

    private void noteHandleRemove(Note note){
        if(note.removable){
            noteRepository.delete(note);
        }
    }

    private void noteHandleNotify(Note note){
        if(note.needNotify){
             notificationService.send(note);
        }
    }
}
