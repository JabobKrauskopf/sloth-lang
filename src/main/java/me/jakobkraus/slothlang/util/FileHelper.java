package me.jakobkraus.slothlang.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

    public static String readFile(String filepath) {

        Path path = Paths.get(filepath);
        StringBuilder buffer = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String str;
            while ((str = reader.readLine()) != null) {
                buffer.append(str).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    public static void saveBinary(String filepath, byte[] byteArray) {
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(byteArray);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
