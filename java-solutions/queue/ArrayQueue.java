package queue;

import java.util.Arrays;

public class ArrayQueue extends AbstractQueue {
    private int head = 0;
    private Object[] elements = new Object[2];

    private int tail() {
        return (head + size - 1) % elements.length;
    }

    protected Queue getInstance() {
        return new ArrayQueue();
    }

    public void enqueueImpl(final Object e) {
        ensureCapacity(size + 1);
        elements[(head + size) % elements.length] = e;
    }

    public Object element() {
        return elements[head];
    }

    public void dequeueImpl() {
        elements[head] = null;
        head = (head + 1) % elements.length;
    }

    public void clearImpl() {
        head = 0;
        elements = new Object[2];
    }

    private void ensureCapacity(final int capacity) {
        if (elements.length < capacity) {
            elements = copy(capacity * 2);
            head = 0;
        }
    }

    private Object[] copy(final int arrSize) {
        final Object[] res = new Object[arrSize] ;
        if (head + size < elements.length) {
            System.arraycopy(elements, head, res, 0, size());
        } else {
            System.arraycopy(elements, head, res, 0, elements.length - head);
            System.arraycopy(elements, 0, res, elements.length - head, (head + size) % elements.length);
        }
        return res;
    }

    /*
        Pred: e != null
        Post: n = n' + 1 && a[1] == e && forall i = 1..n': a[i + 1] == a'[i]
     */
    public void push(final Object e) {
        ensureCapacity(size + 1);
        head = (head + elements.length - 1) % elements.length;
        elements[head] = e;
        size++;
    }

    /*
        Pred: n > 0
        Post: Immutable && R == a[n]
    */
    public Object peek() {
        return elements[tail()];
    }

    /*
        Pred: n > 0
        Post: n = n' - 1 && forall i = 1..n: a[i] = a'[i] && R == a'[n']
     */
    public Object remove() {
        final Object result = elements[tail()];
        elements[tail()] = null;
        size--;
        return result;
    }

    /*
        Pred: INV
        Post: R = a[1..n]
     */
    public Object[] toArray() {
        return copy(size);
    }

    /*
        Pred: INV
        Post: R = "[a[1], a[2] .. a[n]]"
     */
    public String toStr() {
        return Arrays.toString(toArray());
    }
}
