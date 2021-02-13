package online.onedaynote.api.dto.enums;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Color {

    UNDEFINED(0, "Undefined"),
    RED(1, "Red"),
    GREEN(1, "Green"),
    BLUE(3, "Blue"),
    BLACK(4, "Black"),
    WHITE(5, "White");

    private final int color;
    private final String colorName;

    Color(int color, String colorName){
        this.color = color;
        this.colorName = colorName;
    }
    public int getColor() {
        return this.color;
    }
    public static Color getColor(int color) {
        return Arrays.stream(Color.values())
                .filter(c -> color == c.getColor())
                .findFirst().orElse(UNDEFINED);
    }

    public String getName(){
        return this.colorName;
    }

    public static String[] getValues(){
        log.info("Get color values array");
        return Arrays.stream(Color.values())
                .map(Color::getName)
                .toArray(String[]::new);
    }
}
