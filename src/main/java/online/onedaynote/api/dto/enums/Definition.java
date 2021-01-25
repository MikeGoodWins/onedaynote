package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum Definition {

    UNDEFINED(0),
    HAPPY(1),
    UGLY(1),
    DEAD(3),
    ZOMBIE(4),
    SHINY(5);

    private int definition;

    Definition(int definition){
        this.definition = definition;
    }
    public int getDefinition() {
        return this.definition;
    }
    public static Definition getDefinition(int definition) {
        return Arrays.stream(Definition.values())
                .filter(c -> definition == c.getDefinition())
                .findFirst().orElse(UNDEFINED);
    }
}
