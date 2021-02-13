package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum Action {

    UNDEFINED(0, "Undefined"),
    RUN(1, "Run"),
    FLY(1, "Fly"),
    SWIM(3, "Swim"),
    STOP(4, "Stop"),
    EAT(5, "Eat");

    private final int action;
    private final String actionName;

    Action(int action, String actionName){
        this.action = action;
        this.actionName = actionName;
    }
    public int getAction() {
        return this.action;
    }
    public static Action getAction(int action) {
        return Arrays.stream(Action.values())
                .filter(c -> action == c.getAction())
                .findFirst().orElse(UNDEFINED);
    }

    public String getName(){
        return this.actionName;
    }

    public static String[] getValues(){
        return Arrays.stream(Action.values())
                .map(Action::getName)
                .toArray(String[]::new);
    }
}
