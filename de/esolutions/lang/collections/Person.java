package de.esolutions.lang.collections;

import java.util.Arrays;
import java.util.Comparator;

public class Person implements Comparable<Person> {

    String firstName;
    String lastName;

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }

    private static final Comparator<Person> FIRST_NAME_COMPARATOR =
            Comparator.comparing(p -> p.firstName);
//            (p1, p2) -> p1.firstName.compareTo(p2.firstName);

    private static final Comparator<Person> LAST_NAME_COMPARATOR =
            Comparator.comparing(p -> p.lastName);
//            (p1, p2) -> p1.lastName.compareTo(p2.lastName);

    public static final Comparator<Person> PERSON_COMPARATOR =
            LAST_NAME_COMPARATOR.thenComparing(FIRST_NAME_COMPARATOR);

    public static final Comparator<Person> ALT_PERSON_COMPARATOR =
            PERSON_COMPARATOR.reversed();

    public boolean equals(Object o) {
        if (!(o instanceof Person)) {
            return false;
        }
        Person p = ((Person) o);
        return (this.firstName.equals(p.firstName) && this.lastName.equals(p.lastName));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int compareTo(Person p) {
        return PERSON_COMPARATOR.compare(this, p);
    }
}
