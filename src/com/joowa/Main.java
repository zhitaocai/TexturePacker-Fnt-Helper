package com.joowa;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String srcFntFileAbsPath = args[0];
        String newFntFileAbsPath = srcFntFileAbsPath.replace(".fnt", "_fix.fnt");
        int offsetX = Integer.valueOf(args[1]);
        int offsetY = Integer.valueOf(args[2]);

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(srcFntFileAbsPath));
            ArrayList<String> newLineList = new ArrayList<>();

            System.out.println("原始文件");
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("char ")) {
                    newLineList.add(handleOffset(offsetX, offsetY, line));
                } else {
                    newLineList.add(line);
                }
            }

            System.out.println("\n\n\n新文件");
            newLineList.forEach(s -> System.out.println(s));

            File outputFile = new File(newFntFileAbsPath);
            if (outputFile.exists()) {
                outputFile.delete();
                outputFile.createNewFile();
            }

            bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            for (String string : newLineList) {
                bufferedWriter.write(string + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static String handleOffset(int offsetX, int offsetY, String srcLine) {
        String[] lineWords = srcLine.split("( )+");
        ArrayList<String> newLineWordList = new ArrayList<>();
        for (String word : lineWords) {
            if (word.startsWith("y=")) {
                int oldValue = Integer.valueOf(word.split("=")[1]);
                int newValue = oldValue + offsetY;
                newLineWordList.add("y=" + newValue);
                continue;
            }
            if (word.startsWith("x=")) {
                int oldValue = Integer.valueOf(word.split("=")[1]);
                int newValue = oldValue + offsetX;
                newLineWordList.add("x=" + newValue);
                continue;
            }
            newLineWordList.add(word);
        }

        StringBuilder newLine = new StringBuilder(256);
        for (String word : newLineWordList) {
            newLine.append(word).append("    ");
        }
        return newLine.toString();
    }

}
