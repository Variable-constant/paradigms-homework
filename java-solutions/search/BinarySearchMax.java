package search;

public class BinarySearchMax {
    // args.length > 0 && args[i] можно преобразовать в int && args[i] != null
    public static void main(String[] args) {
        // args.length > 0
        int[] a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        // сначала элементы в массиве a[] строго возрастают, затем строго убывают
        //System.out.println(recursiveBinSearch(a));
        System.out.println(binSearch(a));
    }

    private static int recursiveBinSearch(int[] a) {
        return find(a, -1, a.length - 1);
    }

    /*
        INV: a состоит из присоединения строго возрастающего к строго убывающему массиву && a.length > 0
            && left >= -1 && right < a.length && a[left] <= ans && a[right] >= ans
    */
    private static int find(int[] a, int left, int right) {
        if (left >= right - 1) {
            // INV && left >= right - 1 && a[right] >= a[left]
            return a[right];
        }
        int mid = (left + right) / 2;
        if (a[mid + 1] > a[mid]) {
            // INV && left == mid && a[mid] находится в возрастающей части
            return find(a, mid, right);
        } else {
            // a[mid + 1] <= a[mid] -> a[mid] находится в убывающей части || равен максимуму
            return find(a, left, mid);
        }
    }
    // Post: right - left < right' - left'

    // Pred: a состоит из присоединения строго возрастающего к строго убывающему массиву && a.length > 0
    private static int binSearch(int[] a) {
        int left = -1;
        int right = a.length - 1;
        //INV: left <= right - 1 && a[right] <= ans
        while (left < right - 1) {
            // left < right - 1
            int mid = (left + right) / 2;
            // INV && m в границах [left; right)
            if (a[mid + 1] > a[mid]) {
                // INV && a[mid + 1] > a[mid]
                left = mid;
                // left < индекс максимума
            } else {
                // INV && a[mid] <= x && a[mid + 1] <= a[mid]
                right = mid;
                // right <= индекс максимума
            }
            // right - left < right' - left'
        }
        // 0 <= right < a.length
        return a[right];
    }
    // Post:  0 <= R <= n && a[R] = max(a[])
}
