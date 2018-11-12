package de.esolutions.lang.collections;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.time.*;

public class StreamCollector {

    private static final int NUM_PERSONS = 1 << 10;

    public static void main(String[] args) {

        // prepare test data
        List<Person> pList = new ArrayList<>(NUM_PERSONS);
        for (int i = 0; i < NUM_PERSONS; i++) {
            pList.add(new Person("L" + i, "A" + i));
        }
        Stream<Person> inStream = pList.stream();

        Instant beforeCollection = Instant.now();
        String outs = inStream.parallel().collect(
                () -> new StringBuilder(),
                StreamCollector::append,
                StreamCollector::combine
        ).toString();
        System.out.println(outs);
        Instant afterCollection = Instant.now();

        System.out.println("\n Time expended: " +
                (afterCollection.getEpochSecond()-beforeCollection.getEpochSecond()) + "s " +
                (afterCollection.getNano()-beforeCollection.getNano()) + "ns");
    }

    private static void append(StringBuilder accum, Person inp) {
        if (accum.length() != 0) accum.append("; ");
        accum.append(inp.toString());
    }

    private static void combine(StringBuilder left, StringBuilder right) {
        if (left.length() != 0 && right.length() != 0) left.append(";;\n");
        left.append(right);
    }
}
