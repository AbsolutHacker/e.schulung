package de.esolutions.day7.regex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCharacterClassTest {

    @Test
    void testUnicodeCharacterConversion() {
        for (int i = 0; i <= 0x10FFFF; i++) {
            assertEquals(new String(Character.toChars(i)), new FindCharacterClass.UnicodeCharacter(i).toString());
        }
    }

}