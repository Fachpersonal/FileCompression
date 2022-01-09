package net.falscherIdiot.FileCompression;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compress {

    public static String filePath = null;
    public static String outputPath = null;
    public static char UNKNOWN_CHARACTER = '.';

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("compress <FilePath> <OutputPath>");
            System.out.println(0);
        } else {
            filePath = args[0];
            outputPath = args[1];

            try {
                //// String HexFile = convertFileToHex(Paths.get(filePath));
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        Extract.outputPath == null ? Compress.outputPath + ".compf" : Extract.outputPath + ".compf")));
                String[] HexFileSplit = convertFileToHex(Paths.get(filePath)).split(" ");
                int[] numbers = new int[HexFileSplit.length];
                for (int i = 0; i < HexFileSplit.length; i++) {
                    numbers[i] = Integer.parseInt(HexFileSplit[i]);
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String convertFileToHex(Path path) throws IOException {
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("File not found! " + path);
        }

        StringBuilder result = new StringBuilder();
        StringBuilder hex = new StringBuilder();
        StringBuilder input = new StringBuilder();

        int count = 0;
        int value;

        try (InputStream is = Files.newInputStream(path)) {
            while ((value = is.read()) != -1) {
                hex.append(String.format("%02X ", value));

                if (!Character.isISOControl(value)) {
                    input.append((char) value);
                } else {
                    input.append(UNKNOWN_CHARACTER);
                }

                if (count == 14) {
                    result.append(String.format("%s", hex, input));
                    hex.setLength(0);
                    input.setLength(0);
                    count = 0;
                } else {
                    count++;
                }
            }
            if (count > 0) {
                result.append(String.format("%s", hex, input));
            }
        }
        return result.toString();
    }
}
