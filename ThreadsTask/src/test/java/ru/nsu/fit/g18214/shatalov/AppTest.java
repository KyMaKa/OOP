/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.g18214.shatalov;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class AppTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ThreadFind threadFind = new ThreadFind();
        long end, start;
        Integer[] arr = new Integer[8000];
        Arrays.fill(arr, 1000000007);
        for (int j = 1; j <= 3; j++) {
            System.out.println("Run " + j + ":");
            for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
                start = System.currentTimeMillis();
                threadFind.findThreadPool(arr, i);
                end = System.currentTimeMillis();
                System.out.println("Time for " + i + " thread(s): " + ((double) end - start) / 10000);
            }
        }
    }

    @Test
    public void testSingle() throws ExecutionException, InterruptedException {
        ThreadFind threadFind = new ThreadFind();
        long end, start;
        Integer[] arr = new Integer[8000];
        Arrays.fill(arr, 1000000007);
        for (int j = 1; j <= 3; j++) {
            System.out.println("Run " + j + ":");

            start = System.currentTimeMillis();
            threadFind.findThreadPool(arr, 1);
            end = System.currentTimeMillis();
            System.out.println("Time for " + 1 + " thread(s): " + ((double) end - start) / 10000);
        }
    }

    @Test
    public void testParallel() throws ExecutionException, InterruptedException {
        ThreadFind threadFind = new ThreadFind();
        long end, start;
        Integer[] arr = new Integer[8000];
        Arrays.fill(arr, 1000000007);
        for (int j = 1; j <= 3; j++) {
            System.out.println("Run " + j + ":");
            for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
                start = System.currentTimeMillis();
                threadFind.findStream(arr, i);
                end = System.currentTimeMillis();
                System.out.println("Time for " + i + " thread(s): " + ((double) end - start) / 10000);
            }
        }
    }
}
