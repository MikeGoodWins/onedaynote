package online.onedaynote.api.rest;

import static online.onedaynote.api.rest.Paths.NOTES;
import static online.onedaynote.api.rest.Paths.ROOT;
import static online.onedaynote.api.rest.Paths.VARS;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;
import online.onedaynote.api.dto.note.NoteDto;
import online.onedaynote.api.services.interfaces.VariableService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(NOTES)
@Slf4j
public class VarsController {

    private final VariableService variableService;

    public VarsController(VariableService variableService) {
        this.variableService = variableService;
    }

    @DeleteMapping(VARS)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> delete(){

        variableService.deleteAnotherDaysParams();
        return ResponseEntity.noContent().build();
    }
}
