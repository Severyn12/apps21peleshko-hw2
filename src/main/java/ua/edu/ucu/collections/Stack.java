package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList data = new ImmutableLinkedList();
    public void push(Object element) {
        data = data.addLast(element);

    }

    public Object pop() {
        Object lastNode = data.getLast();
        data =  data.removeLast();
        return lastNode;
    }

    public Object peek() {
        return data.getLast();
    }
}
