package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum NoteType {

    TEXT(0),
    PICTURE(1);

    private final int type;

    NoteType(int type) {
        this.type = type;
    }

    public static boolean contains(int type){
        return Arrays.stream(NoteType.values()).anyMatch(nt -> type == nt.getType());
    }

    public static boolean notContains(int type){
        return !contains(type);
    }

    public int getType() {
        return this.type;
    }

    public static NoteType getType(int type) {
        return Arrays.stream(NoteType.values())
                .filter(nt -> type == nt.getType())
                .findFirst()
                .orElse(null);
    }
}
