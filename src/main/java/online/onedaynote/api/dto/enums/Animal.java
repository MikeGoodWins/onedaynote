package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum Animal {

    UNDEFINED(0),
    CAT(1),
    DOG(2),
    MANUL(3),
    HORSE(4),
    DUCK(5);

    private int animal;

    Animal(int animal){
        this.animal = animal;
    }
    public int getAnimal() {
        return this.animal;
    }
    public static Animal getAnimal(int animal) {
        return Arrays.stream(Animal.values())
                .filter(a -> animal == a.getAnimal())
                .findFirst().orElse(UNDEFINED);
    }
}
