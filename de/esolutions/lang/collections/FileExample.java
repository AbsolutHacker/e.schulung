package de.esolutions.lang.collections;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileExample {
    public static void main(String[] args) {
        copy("test.txt", "out.txt");
    }

    public static void copy(Reader inr, Writer outw) throws IOException {
        int c = 0;
        while ((c = inr.read()) != -1) {
            outw.write(c);
        }
        outw.flush();
    }

    public static void copy(String inFile, String outFile) {
        try (
                Reader fisr = new InputStreamReader(new FileInputStream(new File(inFile)), Charset.forName("Windows-1252"));
                Writer fosw = new OutputStreamWriter(new FileOutputStream(new File(outFile)), StandardCharsets.UTF_8)
        ) {
            copy(fisr, fosw);
        } catch (FileNotFoundException fnfExc) {
            System.out.println(fnfExc);
        } catch (IOException ioExc) {
            System.out.println(ioExc);
        }
    }
}
