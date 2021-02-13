package online.onedaynote.api.dto.enums;

import java.util.Arrays;

public enum Animal {

    UNDEFINED(0, "Undefines"),
    CAT(1, "Cat"),
    DOG(2, "Dog"),
    MANUL(3, "Manul"),
    HORSE(4, "Horse"),
    DUCK(5, "Duck");

    private final int animal;
    private final String animalName;

    Animal(int animal, String animalName){
        this.animal = animal;
        this.animalName = animalName;
    }
    public int getAnimal() {
        return this.animal;
    }
    public static Animal getAnimal(int animal) {
        return Arrays.stream(Animal.values())
                .filter(a -> animal == a.getAnimal())
                .findFirst().orElse(UNDEFINED);
    }

    public String getName(){
        return this.animalName;
    }

    public static String[] getValues(){
        return Arrays.stream(Animal.values())
                .map(Animal::getName)
                .toArray(String[]::new);
    }
}
