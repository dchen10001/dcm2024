package com.e2.wfm.gurobidemo.queue;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class ListenableQueue<E> extends AbstractQueue<E> {

    //backing queue to store elements
    private final Queue<E> delegate;  // backing queue
    
    //registered listeners (this is similar to topic-subscriber pattern)
    private final List<Listener<E>> listeners = new ArrayList<>();

    public ListenableQueue(Queue<E> delegate) {
        this.delegate = delegate;
    }
    
    //register listener
    public ListenableQueue<E> registerListener(Listener<E> listener) {
        listeners.add(listener);
        return this;
    }
    
    @Override
    public boolean offer(E e) {
        if (delegate.offer(e)) {
            listeners.forEach(listener -> listener.onEventAdded(e));
            return true;
        } else {
            return false;
        }
    }

    // following methods just delegate to backing instance
    @Override public E poll() { return delegate.poll();}
    @Override public E peek() {return delegate.peek();}
    @Override public Iterator<E> iterator() {return delegate.iterator();}
    @Override public int size() {return delegate.size();}
}
