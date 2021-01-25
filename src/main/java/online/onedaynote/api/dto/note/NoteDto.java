package online.onedaynote.api.dto.note;

import online.onedaynote.api.dto.enums.NoteType;

public class NoteDto {

    public NoteType type;

    public String text;

    public String picture;

    public NoteDto(NoteType type, String payload) {
        this.type = type;
        if(type == NoteType.TEXT){
            text = payload;
        } else {
            picture = payload;
        }
    }
}
