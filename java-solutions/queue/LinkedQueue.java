package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    protected Queue getInstance() {
        return new LinkedQueue();
    }

    @Override
    protected void enqueueImpl(final Object e) {
        if (tail == null) {
            tail = new Node(null, e);
            head = tail;
        } else {
            tail.prev = new Node(null, e);
            tail = tail.prev;
        }
    }

    @Override
    public Object element() {
        return head.value;
    }

    @Override
    protected void dequeueImpl() {
        head = head.prev;
        if (head == null) {
            tail = null;
        }
    }

    @Override
    protected void clearImpl() {
        head = null;
        tail = null;
    }

    private static class Node {
        private Node prev;
        private final Object value;

        public Node(final Node prev, final Object value) {
            this.prev = prev;
            this.value = value;
        }
    }
}
