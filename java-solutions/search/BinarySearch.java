package search;

import java.util.LinkedHashMap;

public class BinarySearch {
    // args.length > 0
    public static void main(String[] args) {
        // args[0] - число, которое нужно найти
        int x = Integer.parseInt(args[0]);
        // args.length > 0
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        // массив отсортирован в порядке невозрастания
        //System.out.println(recursiveBinSearch(x, a));
        System.out.println(binSearch(x, a));
    }

    private static int recursiveBinSearch(int x, int[] a) {
        return find(x, a, -1, a.length);
    }

    /*
        INV: a отсортированно в порядке невозрастания && a[left] > x && a[right] <= x && left < right
     */
    private static int find(int x, int[] a, int left, int right) {
        if (left >= right - 1) {
            // left >= right - 1
            return right;
        }
        int mid = (left + right) / 2;
        if (a[mid] > x) {
            return find(x, a, mid, right);
        } else {
            return find(x, a, left, mid);
        }
    }

    // Pred: a отсортирован в порядке невозрастания
    private static int binSearch(int x, int[] a) {
        int left = -1;
        int right = a.length;
        //INV: left <= right - 1 && a[left] > x && a[right] <= x
        while (left < right - 1) {
            // left < right - 1 && a[left] > x && a[right] <= x
            int mid = (left + right) / 2;
            // INV && m в (left; right)
            if (a[mid] > x) {
                // INV && a[mid] > x
                left = mid;
                // a[left] > x
            } else {
                // INV && a[mid] <= x
                right = mid;
                // a[right] <= x
            }
        }
        // left >= right - 1
        return right;
    }
    // Post:  0 <= R <= n && a[R] <= x && a[R - 1] > x
}
