package queue;

import java.util.Arrays;

/*
    Model:
        [a1, a2, .., an]
        n - размер очереди

    INV:
        n >= 0
        forall i = 1.n : a[i] != null

    Immutable:
        n == n' && forall i = 1.n: a[i] == a'[i]
 */
public class ArrayQueueADT {
    private int head = 0;
    private int size = 0;
    private Object[] elements = new Object[2];

    /*
        Pred: true
        Post: R.n == 0 && R новый
     */
    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    /*
        Pred: e != null && queue != null
        Post: n = n' + 1 && a[n] == e && forall i = 1.n': a[i] = a'[i]
     */
    public static void enqueue(final ArrayQueueADT queue, final Object e) {
        ensureCapacity(queue,queue.size + 1);
        queue.elements[(queue.head + queue.size) % queue.elements.length] = e;
        queue.size++;
    }

    /*
        Pred: n > 0 && queue != null
        Post: Immutable && R == a[1]
    */
    public static Object element(final ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    /*
        Pred: n > 0 && queue != null
        Post: n = n' - 1 && forall i = 1.n: a[i] = a'[i + 1] && R == a'[1]
     */
    public static Object dequeue(final ArrayQueueADT queue) {
        final Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return result;
    }

    /*
        Pred: true && queue != null
        Post: Immutable && R == n
     */
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }

    /*
        Pred: true && queue != null
        Post: Immutable && R == (n == 0)
    */
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }

    /*
        Pred: true && queue != null
        Post: n == 0
     */
    public static void clear(final ArrayQueueADT queue) {
        queue.head = 0;
        queue.size = 0;
        queue.elements = new Object[2];
    }

    /*
        Pred: e != null && queue != null
        Post: n = n' + 1 && a[1] == e && forall i = 1..n': a[i + 1] == a'[i]
     */
    public static void push(final ArrayQueueADT queue, final Object e) {
        ensureCapacity(queue,queue.size + 1);
        queue.head = (queue.head + queue.elements.length - 1) % queue.elements.length;
        queue.elements[queue.head] = e;
        queue.size++;
    }

    /*
        Pred: n > 0 && queue != null
        Post: Immutable && R == a[n]
    */
    public static Object peek(final ArrayQueueADT queue) {
        return queue.elements[(queue.head + queue.size - 1) % queue.elements.length];
    }

    /*
        Pred: n > 0 && queue != null
        Post: n = n' - 1 && forall i = 1..n: a[i] = a'[i] && R == a'[n']
     */
    public static Object remove(final ArrayQueueADT queue) {
        final Object result = queue.elements[(queue.head + queue.size - 1) % queue.elements.length];
        queue.elements[(queue.head + queue.size - 1) % queue.elements.length] = null;
        queue.size--;
        return result;
    }

    /*
        Pred: INV && queue != null
        Post: R = a[1..n]
     */
    public static Object[] toArray(final ArrayQueueADT queue) {
        return copy(queue, queue.size);
    }

    /*
        Pred: INV && queue != null
        Post: R = "[a[1], a[2] .. a[n]]"
     */
    public static String toStr(final ArrayQueueADT queue) {
        return Arrays.toString(toArray(queue));
    }

    private static void ensureCapacity(final ArrayQueueADT queue, final int capacity) {
        if (queue.elements.length < capacity) {
            queue.elements = copy(queue, capacity * 2);
            queue.head = 0;
        }
    }

    private static Object[] copy(final ArrayQueueADT queue, final int arrSize) {
        final Object[] res = new Object[arrSize];
        if (queue.head + queue.size < queue.elements.length) {
            System.arraycopy(queue.elements, queue.head, res, 0, size(queue));
        } else {
            System.arraycopy(queue.elements, queue.head, res, 0, queue.elements.length - queue.head);
            System.arraycopy(queue.elements, 0, res, queue.elements.length - queue.head, (queue.head + queue.size) % queue.elements.length);
        }
        return res;
    }
}
