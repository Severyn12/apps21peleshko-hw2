package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.immutable.ImmutableList;

public class Stack {
    private ImmutableLinkedList data = new ImmutableLinkedList();
    public void push(Object element) {
        data = data.addLast(element);

    }

    public Object pop() {
        Object last_node = data.getLast();
        data =  data.removeLast();
        return last_node;
    }

    public Object peek() {
        return data.getLast();
    }
}
