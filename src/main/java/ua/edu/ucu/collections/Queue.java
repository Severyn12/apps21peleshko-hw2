package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList data = new ImmutableLinkedList();

    public Object peek() {
        return data.getFirst();
    }

    public Object dequeue() {
        Object firstNode = data.getFirst();
        data = data.removeFirst();
        return firstNode;
    }

    public void enqueue(Object element) {
        data = data.addLast(element);
    }
}
