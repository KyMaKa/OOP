package ru.nsu.shatalov.g19214;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Finder {

    public void find(Integer[] arr, Integer threads) 
        throws ExecutionException, InterruptedException {

            ExecutorService pool = Executors.newFixedThreadPool(threads);
            ArrayList<Future> check = new ArrayList<>();
            for (long n : arr) {
                check.add(pool.submit(new Calculate(n)));
            }

            for (Future<Boolean> elem : check) {
                if (!elem.get()) {
                    System.out.println("True");
                    pool.shutdown();
                    return;
                }
            }
    }
}