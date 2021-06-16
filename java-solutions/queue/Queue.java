package queue;
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
public interface Queue {
    /*
        Pred: e != null
        Post: n = n' + 1 && a[n] == e && forall i = 1..n': a[i] == a'[i]
     */
    void enqueue(Object e);

    /*
        Pred: n > 0
        Post: Immutable && R == a[1]
    */
    Object element();

    /*
        Pred: n > 0
        Post: n = n' - 1 && forall i = 1..n: a[i] = a'[i + 1] && R == a'[1]
     */
    Object dequeue();

    /*
        Pred: true
        Post: Immutable && R == n
     */
    int size();

    /*
        Pred: true
        Post: Immutable && R == (n == 0)
    */
    boolean isEmpty();

    /*
        Pred: true
        Post: n == 0
     */
    void clear();

    /*
        Pred: n > 0
        Post: R == Queue[a_n, a_{2n}, ... a_{size'/n * n}] && Immutable
     */
    Queue getNth(int n);

    /*
        Pred: n > 0
        Post: forall i = 1...size a[i] == a'[i + i / n] && size == size' - size' / n
    */
    void dropNth(int n);

    /*
        Pred: n > 0
        Post: R == Queue[a_n, a_{2n}, ... a_{size'/n * n}] && forall i = 1...size a[i] == a'[i + i / n] && size == size' - size' / n
     */
    Queue removeNth(int n);
}
