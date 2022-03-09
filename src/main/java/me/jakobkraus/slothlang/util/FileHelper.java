package me.jakobkraus.slothlang.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

    public static String readFile(String filepath) throws IOException {

        Path path = Paths.get(filepath);
        StringBuilder buffer = new StringBuilder();

        BufferedReader reader = Files.newBufferedReader(path);
        String str;
        while ((str = reader.readLine()) != null) {
            buffer.append(str).append("\n");
        }


        return buffer.toString();
    }

    public static void saveBinary(String filepath, ByteArrayOutputStream byteArray) throws IOException {
        FileOutputStream fos = new FileOutputStream(filepath);
        byteArray.writeTo(fos);
        fos.close();
    }

    public static byte[] readBinary(String filepath) throws IOException {
        return Files.readAllBytes(Paths.get(filepath));
    }
}

