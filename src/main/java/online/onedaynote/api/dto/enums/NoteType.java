package online.onedaynote.api.dto.enums;

public enum NoteType {

    TEXT(0),
    PICTURE(1);

    private int type;

    NoteType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
