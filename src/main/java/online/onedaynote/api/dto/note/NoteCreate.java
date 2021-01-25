package online.onedaynote.api.dto.note;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import online.onedaynote.api.dto.enums.Action;
import online.onedaynote.api.dto.enums.Animal;
import online.onedaynote.api.dto.enums.Color;
import online.onedaynote.api.dto.enums.Definition;
import online.onedaynote.api.dto.enums.NoteType;

@Getter
public class NoteCreate {

    @NonNull
    private NoteType type;

    @NotBlank
    private String key;

    private String text;

    private String picture;

    private Definition definition;

    private Color color;

    private Animal animal;

    private Action action;

    private boolean removable;

    private boolean needNotify;

    private String notifyEmail;
}
