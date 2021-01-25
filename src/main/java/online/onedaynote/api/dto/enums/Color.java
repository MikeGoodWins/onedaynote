package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum Color {

    UNDEFINED(0),
    RED(1),
    GREEN(1),
    BLUE(3),
    BLACK(4),
    WHITE(5);

    private int color;

    Color(int color){
        this.color = color;
    }
    public int getColor() {
        return this.color;
    }
    public static Color getColor(int color) {
        return Arrays.stream(Color.values())
                .filter(c -> color == c.getColor())
                .findFirst().orElse(UNDEFINED);
    }
}
