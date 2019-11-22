/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.g18214.shatalov;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FindSubstring {
    /**
     *
     * @param filename contains path to file starting from folder src
     * @return array with text from file
     * @throws IOException if file inaccessible
     */
    private String[] read(String filename) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename),
                        "UTF-8"));

        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }

    /**
     *
     * @param substring contains substring that we need to find in text
     * @param filename  contains path to file starting from folder src
     * @return array filed with indexes of all occurrences for given substring
     * @throws IOException if there is no such file
     */
    public Integer[] findIndex(String substring, String filename) throws IOException {

        String[] lines = read(filename);

        ArrayList<Integer> index = new ArrayList<>();

        for (String line : lines)

            for (int i = 0; i < line.length(); i++) {
                int tmp;
                tmp = line.indexOf(substring, i);
                if (tmp != -1) {
                    index.add(tmp);
                    i = tmp + substring.length();
                }
                else
                    break;
            }

        return index.toArray(new Integer[index.size()]);
    }
}
