package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;


public class ThreadFind {
  public void findThreadPool(Iterator<Integer> iterator, Integer threads)
      throws ExecutionException, InterruptedException {

    ExecutorService pool = Executors.newFixedThreadPool(threads);
    ArrayList<Future> check = new ArrayList<>();
    while (iterator.hasNext()) {
      long num = iterator.next();
      check.add(CompletableFuture.supplyAsync(() -> App.isNonPrime((int) num), pool));

    }
    for (Future<Boolean> elem : check) {
      if (elem.get()) {
        System.out.println("True");
        pool.shutdown();
        return;
      }
    }
  }

  public void findStream(Iterator<Integer> iterator, int threads, ArrayList<Integer> arr) throws ExecutionException, InterruptedException {

    ForkJoinPool pool = new ForkJoinPool(threads);
    ArrayList<Future> check = new ArrayList<>();
    //while (iterator.hasNext()) {
     // long num = iterator.next();

      /*check.add(pool.submit(() ->*/ arr.parallelStream().anyMatch(App::isNonPrime)/*))*/;
    //}
    /*for (Future<Boolean> elem : check) {
      if (!elem.get()) {
        System.out.println("True");
        pool.shutdown();
        return;
      }
    }*/
  }
}
