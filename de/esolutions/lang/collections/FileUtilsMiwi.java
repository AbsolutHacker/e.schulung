
package de.esolutions.lang.collections;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.vavr.collection.List;

public class FileUtilsMiwi {

    public static void forEachLine(String fileName, Consumer<String> lineConsumer) throws FileNotFoundException, IOException {
        forEachLine(fileName, StandardCharsets.UTF_8, lineConsumer);
    }

    private static <T> T forEachLineInternal(String fileName, Charset charset, T first, BiFunction<T, String, T> function) throws FileNotFoundException, IOException {
        try (
                InputStream in = new FileInputStream(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(in, charset))
        ) {
            String s;
            T current = first;
            while ((s = br.readLine()) != null) {
                current = function.apply(current, s);
            }
            return current;
        }
    }

    public static void forEachLine(String fileName, Charset charset, Consumer<String> lineConsumer) throws FileNotFoundException, IOException {
        forEachLineInternal(
                fileName,
                charset,
                null,
                (e, s) -> {
                    lineConsumer.accept(s);
                    return null;
                }
        );
    }

    public static class ObjectWrapper<T> {
        public T object;
        public ObjectWrapper(T object) {
            this.object = object;
        }
    }

    public static <S> S reduce(String fileName, S unity, BiFunction<S, String, S> accumulator) throws FileNotFoundException, IOException {
//                     ObjectWrapper<S> accu = new ObjectWrapper<S>(unity);
//                     forEachLine(fileName, (s) -> accu.object = accumulator.apply(accu.object, s));
//                     return accu.object;
        return forEachLineInternal(
                fileName,
                StandardCharsets.UTF_8,
                unity,
                accumulator
        );
    }

    public static <S> List<S> map(String fileName, Function<String, S> mapper) throws FileNotFoundException, IOException {
        return forEachLineInternal(
                fileName,
                StandardCharsets.UTF_8,
                List.of(),
                (al, s) -> al.append(mapper.apply(s))
        );
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Integer> result = map("zahlen.txt", FileUtilsMiwi::splitLine).flatMap(l -> l.iterator());
        System.out.println(result);
    }

    private static List<Integer> splitLine(String line) {
        String[] numbers = line.split("\\s+");
        return List.ofAll(Arrays.stream(numbers)
                .filter(n -> !n.isEmpty())
                .map(n -> Integer.parseInt(n))
                .collect(Collectors.toList())
        );
    }

}

