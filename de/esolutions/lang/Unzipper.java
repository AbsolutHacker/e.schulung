package de.esolutions.lang;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unzipper {
    public static final int BUFFER_SIZE = 4096;
    public static void main(String[] args) {
        String output_folder = "unicode/";
        String zipFileName = "UCD.zip";
        File outFolder = new File(output_folder);
        if (!outFolder.exists()) outFolder.mkdir();
        try (ZipFile zipFile = new ZipFile(zipFileName)) {
            zipFile.stream().forEach(ze -> {
                try {
                    String outFilePath = output_folder + ((ZipEntry) ze).getName();
                    File outFile = new File(outFilePath);
                    if (outFile.createNewFile()) {
                        copy(zipFile.getInputStream(ze), new FileOutputStream(outFile));
                    }
                } catch (IOException e) {
                    System.out.println("I/O exception while extracting file <" + output_folder + ((ZipEntry) ze).getName() + ">");
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("I/O exception while unzipping file <" + zipFileName + ">");
            e.printStackTrace();
        }
    }
    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] copyBuffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = in.read(copyBuffer)) != -1) {
            out.write(copyBuffer);
        }
    }
}
