package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.stream.Stream;


public class ThreadFind {
  public void findThreadPool(Iterator<Integer> iterator, Integer threads)
      throws ExecutionException, InterruptedException {

    ExecutorService pool = Executors.newFixedThreadPool(threads);
    ArrayList<Future> check = new ArrayList<>();
    while (iterator.hasNext()) {
      long num = iterator.next();
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

  public void findStream(Iterator<Integer> iterator, int threads) throws ExecutionException, InterruptedException {

    ForkJoinPool pool = new ForkJoinPool(threads);
    ArrayList<Future> check = new ArrayList<>();
    while (iterator.hasNext()) {
      long num = iterator.next();

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
