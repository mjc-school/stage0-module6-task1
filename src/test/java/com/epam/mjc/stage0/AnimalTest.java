package com.epam.mjc.stage0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;

public class AnimalTest {

    @Test
    public void classAnimalExists() {
        Assertions.assertDoesNotThrow(() -> Class.forName("com.epam.mjc.stage0.Animal"), "Class Animal does not exist");
    }

    @Test
    public void classAnimalHasAllNecessaryFields() {
        Class<Animal> animalClass = Animal.class;
        Field[] fields = animalClass.getDeclaredFields();

        Field color = Arrays.stream(fields).filter(field -> field.getName().equals("color")).findFirst().orElse(null);
        Assertions.assertNotNull(color, "Field color is missed");
        Assertions.assertTrue(Modifier.isPrivate(color.getModifiers()), "Field color is not private");
        Assertions.assertEquals(String.class, color.getType(), "Field color is not String type");

        Field numberOfPaws = Arrays.stream(fields).filter(field -> field.getName().equals("numberOfPaws")).findFirst().orElse(null);
        Assertions.assertNotNull(numberOfPaws, "Field numberOfPaws is missed");
        Assertions.assertTrue(Modifier.isPrivate(numberOfPaws.getModifiers()), "Field numberOfPaws is not private");
        Assertions.assertEquals(int.class, numberOfPaws.getType(), "Field numberOfPaws is not int type");

        Field hasFur = Arrays.stream(fields).filter(field -> field.getName().equals("hasFur")).findFirst().orElse(null);
        Assertions.assertNotNull(hasFur, "Field hasFur is missed");
        Assertions.assertTrue(Modifier.isPrivate(hasFur.getModifiers()), "Field hasFur is not private");
        Assertions.assertEquals(boolean.class, hasFur.getType(), "Field hasFur is not boolean type");
    }

    @Test
    public void classAnimalHasConstructorWithAllParameters() {
        try {
            Constructor<?> constructor = getAnimalConstructor();

            Parameter[] parameters = constructor.getParameters();
            Parameter color = Arrays.stream(parameters).filter(parameter -> parameter.getType().equals(String.class)).findFirst().orElse(null);
            Assertions.assertNotNull(color, "Parameter for color is missed");

            Parameter numberOfPaws = Arrays.stream(parameters).filter(parameter -> parameter.getType().equals(int.class)).findFirst().orElse(null);
            Assertions.assertNotNull(numberOfPaws, "Parameter for numberOfPaws is missed");

            Parameter hasFur = Arrays.stream(parameters).filter(parameter -> parameter.getType().equals(boolean.class)).findFirst().orElse(null);
            Assertions.assertNotNull(hasFur, "Parameter for hasFur is missed");

            Field colorField = Animal.class.getDeclaredField("color");
            colorField.setAccessible(true);
            Field numberOfPawsField = Animal.class.getDeclaredField("numberOfPaws");
            numberOfPawsField.setAccessible(true);
            Field hasFurField = Animal.class.getDeclaredField("hasFur");
            hasFurField.setAccessible(true);

            Animal animal = (Animal) constructor.newInstance("someColor", 123123, true);

            Assertions.assertEquals("someColor", (String) colorField.get(animal), "Not correct set up for color field");
            Assertions.assertEquals(123123, (int) numberOfPawsField.get(animal), "Not correct set up for numberOfPawsField field");
            Assertions.assertTrue((boolean) hasFurField.get(animal), "Not correct set up for hasFurField field");
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InstantiationException |
                 InvocationTargetException e) {
            Assertions.fail("Some Animal class configuration problems");
        }
    }

    @Test
    public void getDescriptionReturnCorrectString() {
        try {
            Constructor<?> constructor = getAnimalConstructor();

            Method getDescription = Animal.class.getMethod("getDescription");

            Animal animal = (Animal) constructor.newInstance("red", 3, true);
            Assertions.assertEquals("This animal is mostly red. It has 3 paws and a fur.", getDescription.invoke(animal), "Method getDescription() does not work correctly");

            animal = (Animal) constructor.newInstance("blue", 1213, false);
            Assertions.assertEquals("This animal is mostly blue. It has 1213 paws and no fur.", getDescription.invoke(animal), "Method getDescription() does not work correctly");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            Assertions.fail("Some Animal class configuration problems");
        }
    }

    @Test
    public void getDescriptionChangeResultDependingOnNumberOfPaws() {
        try {
            Constructor<?> constructor = getAnimalConstructor();

            Method getDescription = Animal.class.getMethod("getDescription");

            Animal animal = (Animal) constructor.newInstance("red", 3, true);
            Assertions.assertEquals("This animal is mostly red. It has 3 paws and a fur.", getDescription.invoke(animal), "Method getDescription() does not work correctly");

            animal = (Animal) constructor.newInstance("red", 1, true);
            Assertions.assertEquals("This animal is mostly red. It has 1 paw and a fur.", getDescription.invoke(animal), "Method getDescription() does not work correctly");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            Assertions.fail("Some Animal class configuration problems");
        }
    }

    private Constructor<?> getAnimalConstructor() throws NoSuchMethodException {
        Constructor<?> constructor = Animal.class.getConstructor(String.class, int.class, boolean.class);
        Assertions.assertNotNull(constructor, "There is no constructor with 3 parameters");
        return constructor;
    }
}
