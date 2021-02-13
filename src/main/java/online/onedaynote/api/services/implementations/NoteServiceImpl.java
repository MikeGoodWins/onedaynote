package online.onedaynote.api.services.implementations;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

        log.info("*** Get note");
        String encryptedKey = cryptService.encrypt(key);
        Optional<Note> noteOptional = noteRepository.findByKey(encryptedKey);
        if(noteOptional.isEmpty()){
            log.info("*** Note not found by key");
            throw new NotFoundException("Note not found");
        }
        log.info("*** Note found");
        Note note = noteOptional.get();
        noteHandleNotify(note);
        noteHandleRemove(note);
        String decryptedPayload = cryptService.decrypt(note.getPayload());
        return new NoteDto(note, decryptedPayload);
    }

    @Override
    public NoteDto add(NoteCreate model) {

        log.info("*** Add note");
        NoteDto result;
        String fullKey = model.getKey().concat(ParamUtils.paramString(model));
        String key = cryptService.encrypt(fullKey);
        Optional<Note> existsNote = noteRepository.findByKey(key);
        Note note;
        if(existsNote.isEmpty()){
            log.info("*** Note with key not found. Adding new note");
            String payloadString = cryptService.encrypt(model.getPayload());
            note = new Note(key, payloadString, model);
        } else {
            log.info("*** Note with key found. Editing note payload");
            note = existsNote.get();
            note.setPayload(cryptService.encrypt(model.getPayload()));
            note.setNoteType(model.getType().getType());
            note.setNeedNotify(model.isNeedNotify());
            note.setNotifyEmail(model.getNotifyEmail());
            note.setRemovable(model.isRemovable());
            note.setCreated(TimeUtils.now());
        }
        noteRepository.save(note);
        noteHistoryRepository.save(new NoteHistory(note));
        log.info("*** Note successfully saved");
        result = new NoteDto(note.getId(), model.getKey(), note.getNoteType(), note.getCreated());
        return result;
    }

    @Override
    public void delete() {

        log.info("*** Delete old notes");
        String today = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(TimeUtils.now());
        List<Note> yesterdayNotes = noteRepository.findAllBySimpleDateBefore(today);
        if(!yesterdayNotes.isEmpty()){
            log.info("*** Old note list is not empty");
            noteRepository.deleteAll(yesterdayNotes);
            log.info("*** Old notes delete successfully");
        }
        log.info("*** Old note list is empty");
    }

    @Override
    public ParamsDto getParams() {

        log.info("*** Get request params list");
        String[] definitionsArray = Definition.getValues();
        String[] colorsArray = Color.getValues();
        String[] animalsArray = Animal.getValues();
        String[] actionsArray = Action.getValues();
        return new ParamsDto(definitionsArray, colorsArray, animalsArray, actionsArray);
    }

    private void noteHandleRemove(Note note){
        log.info("*** Handle remove note");
        if(note.removable){
            log.info("*** Removable note was deleted");
            noteRepository.delete(note);
        }
    }

    private void noteHandleNotify(Note note){
        log.info("*** Handle notify note");
        if(note.needNotify){
            log.info("*** Need notify");
             notificationService.send(note);
        }
    }
}
