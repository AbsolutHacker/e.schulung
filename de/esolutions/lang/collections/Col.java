package de.esolutions.lang.collections;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Col {
    public static void main(String[] args) {
        Stream<String> sstream = Arrays.asList("a", "b", "c", "d", "e", "f").stream();
        System.out.println(sstream.parallel().reduce(
                "",
                Col::accumulate,
                Col::combine
                ));
    }

    private static String accumulate(String accum, String inp) {
        return (accum.isEmpty() ? accum + inp : inp + "," + accum);
    }

    private static String combine(String left, String right) {
        if (!(left.isEmpty() || right.isEmpty())) right += ";";
        return right + left;
    }
}
