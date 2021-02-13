package online.onedaynote.api.dto.enums;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Definition {

    UNDEFINED(0, "Undefined"),
    HAPPY(1, "Happy"),
    UGLY(2, "Ugly"),
    DEAD(3, "Dead"),
    ZOMBIE(4, "Zombie"),
    SHINY(5, "Shiny");

    private final int definition;
    private final String definitionName;

    Definition(int definition, String definitionName){

        this.definition = definition;
        this.definitionName = definitionName;
    }
    public int getDefinition() {
        return this.definition;
    }
    public static Definition getDefinition(int definition) {
        return Arrays.stream(Definition.values())
                .filter(c -> definition == c.getDefinition())
                .findFirst().orElse(UNDEFINED);
    }

    public String getName(){
        return this.definitionName;
    }

    public static String[] getValues(){
        log.info("Get definition values array");
        return Arrays.stream(Definition.values())
                .map(Definition::getName)
                .toArray(String[]::new);
    }
}
