package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item9_Prefer_try_with_resources_to_try_finally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FirstLineOfFile {
    /**
     * try, finally 기법
     */
    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    /**
     * try, resource 기법
     */
    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }
}
