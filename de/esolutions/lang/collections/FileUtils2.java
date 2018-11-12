package de.esolutions.lang.collections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/*
    Use an artifical external storage object
 */
public class FileUtils2 {

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

    static class AccumulatorStore<S> {
        public S store;
        public AccumulatorStore(S store){
            this.store = store;
        }
    }

    public static <S> S reduce(String fileName, S unity, BiFunction<S, String, S> function) throws IOException {

        // use forEachLine() here
        S result = unity;
        BufferedReader rin = getFileReader(fileName, DEFAULT_CHARSET);
        Objects.requireNonNull(rin);
        String line;
        while ((line = rin.readLine()) != null){
            result = function.apply(result, line);
        }
        return result;
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
