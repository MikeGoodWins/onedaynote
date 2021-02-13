package online.onedaynote.api.dto.note;

import lombok.Getter;
import lombok.Setter;
import online.onedaynote.api.dto.enums.Action;
import online.onedaynote.api.dto.enums.Animal;
import online.onedaynote.api.dto.enums.Color;
import online.onedaynote.api.dto.enums.Definition;
import online.onedaynote.api.dto.enums.NoteType;

@Getter
@Setter
public class NoteCreate {

    private NoteType type;

    private String key;

    private String payload;

    private Definition definition = Definition.UNDEFINED;

    private Color color = Color.UNDEFINED;

    private Animal animal = Animal.UNDEFINED;

    private Action action = Action.UNDEFINED;

    private boolean removable = false;

    private boolean needNotify = false;

    private String notifyEmail;
}
