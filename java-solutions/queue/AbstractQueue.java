package queue;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    protected abstract Queue getInstance();

    @Override
    public void enqueue(final Object e) {
        enqueueImpl(e);
        size++;
    }

    protected abstract void enqueueImpl(final Object e);

    @Override
    public Object dequeue() {
        final Object result = element();
        size--;
        dequeueImpl();
        return result;
    }

    protected abstract void dequeueImpl();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void clear() {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();

    private Queue iterateNth(final int n, final Queue queue, final boolean shouldDelete) {
        final int prevSize = size;
        for (int i = 1; i <= prevSize; i++) {
            final Object cur = dequeue();
            if (!(shouldDelete && i % n == 0)) {
                enqueue(cur);
            }
            if (i % n == 0 && queue != null) {
                queue.enqueue(cur);
            }
        }
        return queue;
    }

    public Queue getNth(final int n) {
        return iterateNth(n, getInstance(), false);
    }

    public void dropNth(final int n) {
        iterateNth(n, null, true);
    }

    public Queue removeNth(final int n) {
        return iterateNth(n, getInstance(), true);
    }
}
