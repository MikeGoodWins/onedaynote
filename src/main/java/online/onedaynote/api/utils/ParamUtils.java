package online.onedaynote.api.utils;

import online.onedaynote.api.dto.enums.Action;
import online.onedaynote.api.dto.enums.Animal;
import online.onedaynote.api.dto.enums.Color;
import online.onedaynote.api.dto.enums.Definition;
import online.onedaynote.api.dto.enums.NoteType;
import online.onedaynote.api.dto.note.NoteCreate;
import online.onedaynote.api.exceptions.BadRequestException;

public final class ParamUtils {


    private ParamUtils() {
    }

    public static String paramString(NoteCreate model){
        return paramString(model.getDefinition().getDefinition(),
                model.getColor().getColor(),
                model.getAnimal().getAnimal(),
                model.getAction().getAction());
    }

    public static String paramString(int definition, int color, int animal, int action) {

        return new StringBuilder()
                .append(handleDefinition(definition))
                .append(handleColor(color))
                .append(handleAnimal(animal))
                .append(handleAction(action))
                .toString();
    }

    public static void checkType(NoteCreate model){
        if(NoteType.notContains(model.getType())){
            throw new BadRequestException("Type parameter is invalid");
        }
    }

    public static void checkKey(NoteCreate model){
        if(StringUtils.isNullOrEmpty(model.getKey())){
            throw new BadRequestException("Key parameter is invalid");
        }
    }

    public static void checkPayload(NoteCreate model){
        if(StringUtils.isNullOrEmpty(model.getPayload())){
            throw new BadRequestException("Payload parameter is invalid");
        }
    }

    private static String handleDefinition(int element){
        return Definition.getDefinition(element).toString();
    }

    private static String handleColor(int color){
        return Color.getColor(color).toString();
    }

    private static String handleAnimal(int animal){
        return Animal.getAnimal(animal).toString();
    }

    private static String handleAction(int action){
        return Action.getAction(action).toString();
    }
}
