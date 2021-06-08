import java.util.Scanner;

public class HeapSort {

  private int[] heapSort(int[] arr) {
    // Build heap from array
    int[] arrToSort = arr;
    for (int i = arrToSort.length / 2 - 1; i >= 0; i--) {
      heapify(arrToSort, arrToSort.length, i);
    }

    for (int i = arrToSort.length - 1; i >= 0; i--) {
      swap(arrToSort, 0, i);
      heapify(arrToSort, i, 0);
    }
    return arrToSort;
  }

  private void heapify(int[] arr, int size, int root) {
    int largest = root;
    int left  = 2 * root + 1; //left element
    int right = 2 * root + 2; //right element

    if (left < size && arr[left] > arr[largest]) {
      largest = left;
    }

    if (right < size && arr[right] > arr[largest]) {
      largest = right;
    }

    if (largest != root) {
      swap(arr, root, largest);
      heapify(arr, size, largest);
    }
  }

  private static void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int size = in.nextInt();
    int[] arr = new int[size];

    System.out.print("[");
    for (int i = 0; i < size; i++) {
      int num = in.nextInt();
      arr[i] = num;
    }
    System.out.println("]");

    HeapSort hs = new HeapSort();
    int[] sortedArr = hs.heapSort(arr);

    System.out.print("[ ");
    for (int j : sortedArr) {
      System.out.print(j + " ");
    }
    System.out.println("]");
  }

}
