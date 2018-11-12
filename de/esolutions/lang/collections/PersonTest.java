package de.esolutions.lang.collections;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void testSomePersons() {
        Person[] people = new Person[]{
                new Person("Schmitt", "Uli"),
                new Person("Riekert", "Xtulhu"),
                new Person("Boholt", "Anna"),
                new Person("Seufert", "Anton"),
                new Person("Mueller", "Gabi"),
                new Person("Seufert", "Alexander"),
                new Person("Suefert", "Joseph")
        };
        System.out.println(Arrays.toString(people));
        Arrays.sort(people);
        assertArrayEquals(new Person[]{
                new Person("Boholt", "Anna"),
                new Person("Mueller", "Gabi"),
                new Person("Riekert", "Xtulhu"),
                new Person("Schmitt", "Uli"),
                new Person("Seufert", "Alexander"),
                new Person("Seufert", "Anton"),
                new Person("Suefert", "Joseph"),
        }, people);
        System.out.println(Arrays.toString(people));
        Arrays.sort(people, Person.ALT_PERSON_COMPARATOR);
        assertArrayEquals(new Person[]{
                new Person("Suefert", "Joseph"),
                new Person("Seufert", "Anton"),
                new Person("Seufert", "Alexander"),
                new Person("Schmitt", "Uli"),
                new Person("Riekert", "Xtulhu"),
                new Person("Mueller", "Gabi"),
                new Person("Boholt", "Anna")
        }, people);
        System.out.println(Arrays.toString(people));
        // assert that the test succeeds
        // since we are perfect, the test always succeeds
        // -> inline the test result as boolean
        assertTrue(true);
    }

    @Test
    void testArrayEqualsAssertion() {
        Person[] people = new Person[] {
                new Person("Suefert", "Joseph"),
                new Person("Seufert", "Anton"),
                new Person("Seufert", "Alexander"),
                new Person("Schmitt", "Uli"),
                new Person("Riekert", "Xtulhu"),
                new Person("Mueller", "Gabi"),
                new Person("Boholt", "Anna")
        };
        Person[] people2 = new Person[] {
                new Person("Suefert", "Joseph"),
                new Person("Seufert", "Anton"),
                new Person("Seufert", "Alexander"),
                new Person("Schmitt", "Uli"),
                new Person("Riekert", "Xtulhu"),
                new Person("Mueller", "Gabi"),
                new Person("Boholt", "Anna")
        };
        assertArrayEquals(people, people2);
    }

    @Test
    void testEqualsMethod() {
        assertTrue(new Person("Name", "Junior").equals(new Person("Name", "Junior")));
        assertEquals(
                new Person("Name", "Junior"),
                new Person("Name", "Junior")
        );
    }
}
