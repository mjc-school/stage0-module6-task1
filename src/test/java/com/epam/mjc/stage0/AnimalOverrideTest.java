package com.epam.mjc.stage0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnimalOverrideTest {

    @Test
    public void classDogExists() {
        Assertions.assertDoesNotThrow(() -> Class.forName("com.epam.mjc.stage0.Dog"), "Class Dog does not exist");
    }

    @Test
    public void classDogHasNoArgsConstructor() {
        try {
            Constructor<?> constructor = Dog.class.getConstructor();
            Assertions.assertNotNull(constructor, "There is no constructor with 0 parameters for Dog class");
        } catch (NoSuchMethodException e) {
            Assertions.fail("Some Dog class configuration problems");
        }
    }

    @Test
    public void getDescriptionReturnCorrectStringForDogClass() {
        try {
            Constructor<?> constructor = Dog.class.getConstructor();
            Dog dog = (Dog) constructor.newInstance();

            Method getDescription = Dog.class.getMethod("getDescription");

            Assertions.assertEquals("This animal is mostly brown. It has 4 paws and a fur.", getDescription.invoke(dog), "Method getDescription() does not work correctly for Dog class");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            Assertions.fail("Some Dog class configuration problems");
        }
    }

    @Test
    public void classBirdExists() {
        Assertions.assertDoesNotThrow(() -> Class.forName("com.epam.mjc.stage0.Bird"), "Class Bird does not exist");
    }

    @Test
    public void classBirdHasNoArgsConstructor() {
        try {
            Constructor<?> constructor = Bird.class.getConstructor();
            Assertions.assertNotNull(constructor, "There is no constructor with 0 parameters for Bird class");
        } catch (NoSuchMethodException e) {
            Assertions.fail("Some Bird class configuration problems");
        }
    }

    @Test
    public void getDescriptionReturnCorrectStringForBirdClass() {
        try {
            Constructor<?> constructor = Bird.class.getConstructor();
            Bird bird = (Bird) constructor.newInstance();

            Method getDescription = Bird.class.getMethod("getDescription");

            Assertions.assertEquals("This animal is mostly blue. It has 2 paws and no fur. Moreover, it has 2 wings and can fly.", getDescription.invoke(bird), "Method getDescription() does not work correctly for Bird class");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            Assertions.fail("Some Bird class configuration problems");
        }
    }
}
