package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    public ImmutableLinkedList data = new ImmutableLinkedList();

    public Object peek() {
        return data.getFirst();
    }

    public Object dequeue() {
        Object first_node = data.getFirst();
        data = data.removeFirst();
        return first_node;
    }

    public void enqueue(Object element) {
        data = data.addLast(element);
    }
}
