package online.onedaynote.api.rest;

import static online.onedaynote.api.rest.Paths.NOTES;
import static online.onedaynote.api.rest.Paths.ROOT;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.services.interfaces.NoteService;
import online.onedaynote.api.utils.ParamUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(NOTES)
@Slf4j
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(ROOT)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> getNote(final @RequestParam(value = "key") String key,
                                           final @RequestParam(value = "definition") int definition,
                                           final @RequestParam(value = "color") int color,
                                           final @RequestParam(value = "animal") int animal,
                                           final @RequestParam(value = "action") int action){

        NoteDto result = noteService.get(key.concat(
                ParamUtils.paramString(definition, color, animal, action)));
        return ResponseEntity.ok(result);
    }

    @PostMapping(ROOT)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> addNote(final @NonNull NoteCreate model){

        ParamUtils.checkNoteModel(model);
        NoteDto result = noteService.add(model);
        return ResponseEntity.ok(result);
    }

}
