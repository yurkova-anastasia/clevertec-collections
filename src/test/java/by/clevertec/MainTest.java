package by.clevertec;

import by.clevertec.model.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void task8Test_shouldReturnOldestAnimal() {
        // Given
        Animal expected = new Animal(87, "Arctic tern", 48, "Icelandic", "Female");

        // When
        Animal actual = Main.task8();

        // Then
        assertEquals(expected, actual);
    }
}