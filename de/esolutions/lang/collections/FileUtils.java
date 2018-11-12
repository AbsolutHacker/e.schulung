package de.esolutions.lang.collections;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileUtils {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void forEachLine(String fileName, Consumer<String> lineConsumer) throws IOException {
        forEachLine(fileName, lineConsumer, DEFAULT_CHARSET);
    }

    public static void forEachLine(String fileName, Consumer<String> lineConsumer, Charset charset) throws IOException {
        BufferedReader rin = getFileReader(fileName, charset);
        Objects.requireNonNull(rin);
        String line;
        while ((line = rin.readLine()) != null){
            lineConsumer.accept(line);
        }
    }

    public static <S> S reduce(String fileName, S unity, BiFunction<S, String, S> function) throws IOException {
        S result = unity;
        BufferedReader rin = getFileReader(fileName, DEFAULT_CHARSET);
        Objects.requireNonNull(rin);
        String line;
        while ((line = rin.readLine()) != null){
            result = function.apply(result, line);
        }
        return result;
    }

    public static void transmogrify(String fileName) {
    }

    private static BufferedReader getFileReader(String fileName, Charset charset) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            return reader;
        } catch (FileNotFoundException fnfExc) {
            System.out.println("File not found: " + fileName);
        }
        return null;
    }

}
