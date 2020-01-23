package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadFind {
  public void find(Integer[] arr, Integer threads)
      throws ExecutionException, InterruptedException {

    ExecutorService pool = Executors.newFixedThreadPool(threads);
    ArrayList<Future> check = new ArrayList<>();
    for (long num : arr) {
      check.add(pool.submit(new App(num)));

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
