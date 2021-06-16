package queue;

import java.util.Arrays;

/*
    Model:
        [a1, a2, ..., an]
        n - размер очереди

    INV:
        n >= 0
        forall i = 1..n : a[i] != null

    Immutable:
        n == n' && forall i = 1..n: a[i] == a'[i]
 */
public class ArrayQueueModule {
    private static int head = 0;
    private static int size = 0;
    private static Object[] elements = new Object[2];

    /*
        Pred: e != null
        Post: n = n' + 1 && a[n] == e && forall i = 1..n': a[i] = a'[i]
     */
    public static void enqueue(Object e) {
        ensureCapacity(size + 1);
        elements[(head + size) % elements.length] = e;
        size++;
    }

    /*
        Pred: n > 0
        Post: Immutable && R == a[1]
    */
    public static Object element() {
        return elements[head];
    }

    /*
        Pred: n > 0
        Post: n = n' - 1 && forall i = 1..n: a[i] = a'[i + 1] && R == a'[1]
     */
    public static Object dequeue() {
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return result;
    }

    /*
        Pred: true
        Post: Immutable && R == n
     */
    public static int size() {
        return size;
    }

    /*
        Pred: true
        Post: Immutable && R == (n == 0)
    */
    public static boolean isEmpty() {
        return size == 0;
    }

    /*
        Pred: true
        Post: n == 0
     */
    public static void clear() {
        head = 0;
        size = 0;
        elements = new Object[2];
    }

    /*
        Pred: e != null
        Post: n = n' + 1 && a[1] == e && forall i = 1..n': a[i + 1] == a'[i]
     */
    public static void push(Object e) {
        ensureCapacity(size + 1);
        head = (head + elements.length - 1) % elements.length;
        elements[head] = e;
        size++;
    }

    /*
        Pred: n > 0
        Post: Immutable && R == a[n]
    */
    public static Object peek() {
        return elements[(head + size - 1) % elements.length];
    }

    /*
        Pred: n > 0
        Post: n = n' - 1 && forall i = 1..n: a[i] = a'[i] && R == a'[n']
     */
    public static Object remove() {
        Object result = elements[(head + size - 1) % elements.length];
        elements[(head + size - 1) % elements.length] = null;
        size--;
        return result;
    }

    /*
        Pred: INV
        Post: R = a[1..n]
     */
    public static Object[] toArray() {
        return copy(size);
    }

    /*
        Pred: INV
        Post: R = "[a[1], a[2] .. a[n]]"
     */
    public static String toStr() {
        return Arrays.toString(toArray());
    }

    private static void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            elements = copy(capacity * 2);
            head = 0;
        }
    }

    private static Object[] copy(int arrSize) {
        Object[] res = new Object[arrSize];
        if (head + size < elements.length) {
            System.arraycopy(elements, head, res, 0, size());
        } else {
            System.arraycopy(elements, head, res, 0, elements.length - head);
            System.arraycopy(elements, 0, res, elements.length - head, (head + size) % elements.length);
        }
        return res;
    }
}
