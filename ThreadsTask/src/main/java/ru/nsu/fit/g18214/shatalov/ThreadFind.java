package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.*;


public class ThreadFind {
  public void findThreadPool(Integer[] arr, Integer threads)
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

  public void findStream(Integer[] arr, int threads) throws ExecutionException, InterruptedException {

    Iterator<Integer> iter = Arrays.stream(arr).iterator();
    ForkJoinPool pool = new ForkJoinPool(threads);
    ArrayList<Future> check = new ArrayList<>();
    Iterator<Future> iterF = check.iterator();
    while (iter.hasNext()) {
      long num = iter.next();
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
