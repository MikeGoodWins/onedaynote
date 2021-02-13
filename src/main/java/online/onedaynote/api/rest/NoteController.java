package online.onedaynote.api.rest;

import static online.onedaynote.api.rest.Paths.NOTES;
import static online.onedaynote.api.rest.Paths.ROOT;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.dto.note.ParamsDto;
import online.onedaynote.api.services.interfaces.NoteService;
import online.onedaynote.api.utils.ParamUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableScheduling
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
    public ResponseEntity<NoteDto> getNote(
            final @RequestParam(value = "key") @NotBlank String key,
            final @RequestParam(value = "definition", defaultValue = "0") int definition,
            final @RequestParam(value = "color", defaultValue = "0") int color,
            final @RequestParam(value = "animal", defaultValue = "0") int animal,
            final @RequestParam(value = "action", defaultValue = "0") int action){

        log.info("/// Get note");
        ParamUtils.checkKey(key);
        NoteDto result = noteService.get(key.concat(
                ParamUtils.paramString(key, definition, color, animal, action)));
        return ResponseEntity.ok(result);
    }

    @GetMapping(Paths.PARAMS)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParamsDto> getParams(){

        log.info("/// Get params");
        ParamsDto result = noteService.getParams();
        return ResponseEntity.ok(result);
    }

    @PostMapping(ROOT)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> addNote(@Valid NoteCreate model){

        log.info("/// Add note");
        ParamUtils.checkType(model);
        ParamUtils.checkKey(model);
        ParamUtils.checkPayload(model);
        NoteDto result = noteService.add(model);
        return ResponseEntity.ok(result);
    }

    @Scheduled(cron = "0 2 0 * * *")
    @DeleteMapping(ROOT)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public void delete(){

        log.info("/// Delete old notes");
        noteService.delete();
    }

}
