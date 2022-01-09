package net.falscherIdiot.FileCompression;

public class Extract {

    public static String filePath = null;
    public static String outputPath = null;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("extract <CompressedFilePath> <OutputPath>");
            System.out.println(0);
        } else {
            filePath = args[0];
            outputPath = args[1];
        }
    }
}