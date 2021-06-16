package queue;

public class MyArrayQueueTest {
    public static void fill(Queue queue, int n) {
        for (int i = 1; i <= n; i++) {
            queue.enqueue(i);
        }
    }

    public static void dump(Queue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                            queue.size() + " " +
                            queue.element() + " " +
                            queue.dequeue());
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(i + "   i = " + (i + (i - 1) / 4));
        }
    }
}
