package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum Action {

    UNDEFINED(0),
    RUN(1),
    FLY(1),
    SWIM(3),
    STOP(4),
    EAT(5);

    private int action;

    Action(int action){
        this.action = action;
    }
    public int getAction() {
        return this.action;
    }
    public static Action getAction(int action) {
        return Arrays.stream(Action.values())
                .filter(c -> action == c.getAction())
                .findFirst().orElse(UNDEFINED);
    }
}
