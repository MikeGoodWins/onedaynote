package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum NoteType {

    TEXT(0),
    PICTURE(1);

    private int type;

    NoteType(int type) {
        this.type = type;
    }

    public static boolean contains(NoteType type){
        return Arrays.asList(NoteType.values()).contains(type);
    }

    public static boolean notContains(NoteType type){
        return !contains(type);
    }

    public int getType() {
        return this.type;
    }
}
