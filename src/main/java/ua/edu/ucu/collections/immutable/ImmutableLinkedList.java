package ua.edu.ucu.collections.immutable;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter @Getter
public final class ImmutableLinkedList implements ImmutableList{
    public Node start_node = null;
    public Node last_node = null;
    private int node_amount = 0;

    private class Node {
        private Object data;
        private Node next;

        private Node(Object data) {
            this.data = data;
            this.next = null;
        }

        private Node copy(){
            Node nw_node = new Node(data);
            nw_node.next = next;

            return nw_node;
        }
        private Node copy_chain() {
            Node cur_node = this.copy();
            Node st_node = cur_node;
            while (cur_node.next != null) {
                cur_node.next = cur_node.next.copy();
                cur_node = cur_node.next;
            }
            return st_node;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ImmutableLinkedList{" +
                "start_node=" + start_node +
                '}';
    }

    public ImmutableLinkedList(Object [] elements) {
        ImmutableLinkedList nw_list = (ImmutableLinkedList) new ImmutableLinkedList().addAll(elements);
        node_amount = nw_list.getNode_amount();
        start_node = nw_list.getStart_node();
        last_node = nw_list.getLast_node();

    }

    public ImmutableLinkedList() {
    }

    @Override
    public ImmutableList add(Object element) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();
        if (start_node == null){
            linked_list.setStart_node(new Node(element));
            linked_list.setLast_node(new Node(element));
        }
        else {
            
            linked_list.setStart_node(start_node.copy_chain());
            Node cur_node = linked_list.getStart_node();


            while (cur_node.next != null) {

                cur_node = cur_node.next;


            }
            cur_node.next = new Node(element);
        }


        linked_list.setNode_amount(node_amount + 1);
        return linked_list;
    }

    @Override
    public ImmutableList add(int index, Object element) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();

        int i = 0;


        if (start_node != null) {
            linked_list.setStart_node(start_node.copy_chain());
            Node cur_node = linked_list.getStart_node();

            while (cur_node.next != null && i < index-1) {

                cur_node = cur_node.next;
                i++;
            }
            if (cur_node.next != null) {
                Node next_node = cur_node.next;


                cur_node.next = new Node(element);
                cur_node.next.next = next_node;

            }
            else {
                cur_node.next = new Node(element);
            }


        }
        else{
            linked_list.setStart_node(new Node(element));
        }
        linked_list.setNode_amount(node_amount + 1);
        return linked_list;
    }

    @Override
    public ImmutableList addAll(Object[] elements) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();
        Node cur_node = null;

        if (start_node != null) {
            linked_list.setStart_node(start_node.copy_chain());
            cur_node = linked_list.getStart_node();
            while (cur_node.next != null) {

                cur_node = cur_node.next;
            }


            cur_node.next = new Node(elements[0]);

            cur_node = cur_node.next;
        }
        else{
            linked_list.setStart_node(new Node(elements[0]));
            cur_node = linked_list.getStart_node();
        }

        elements = Arrays.copyOfRange(elements, 1, elements.length);
        for (int i = 0; i < elements.length; i++){
            cur_node.next = new Node(elements[i]);
            cur_node = cur_node.next;
        }

        linked_list.setNode_amount(node_amount + elements.length + 1);

        return linked_list;
        }


    @Override
    public ImmutableList addAll(int index, Object[] elements) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();

        int i = 0;
        Node cur_node = null;
        if (start_node != null) {
            linked_list.setStart_node(start_node.copy_chain());
            cur_node = linked_list.getStart_node();
            while (cur_node.next != null && i < index-1) {
                i++;
                cur_node = cur_node.next;
            }
        }

        ImmutableLinkedList nw_link_lst = new ImmutableLinkedList(elements);
        if (cur_node == null){
            linked_list.setStart_node(nw_link_lst.getStart_node());

        }
        else{

            if (cur_node.next == null){

                cur_node.next = nw_link_lst.getStart_node();
            }
            else {

                Node nx_node = cur_node.next;
                cur_node.next = nw_link_lst.getStart_node();
                cur_node = cur_node.next;
                while (cur_node.next != null) {

                    cur_node = cur_node.next;
                }
                cur_node.next = nx_node;
            }

        }


        linked_list.setNode_amount(node_amount + elements.length);
        return linked_list;
    }

    @Override
    public Object get(int index) {

        Node cur_node = start_node;
        int i = 0;
        if (start_node != null) {

            while (cur_node.next != null && i < index) {

                cur_node = cur_node.next;
                i++;
            }
            
        }
        return cur_node.data;

    }

    @Override
    public ImmutableList remove(int index) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();
        linked_list.setStart_node(start_node.copy_chain());
        
        Node cur_node = linked_list.getStart_node();
        int i = 0;
        if (start_node != null) {

            while (cur_node.next != null && i < index-1) {

                cur_node = cur_node.next;
                i++;
            }
            Node nx_test = cur_node.next.next;
            cur_node.next = nx_test;

        }
        linked_list.setNode_amount(node_amount - 1);
        return linked_list;
    }

    @Override
    public ImmutableList set(int index, Object element) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();
        linked_list.setStart_node(start_node.copy_chain());
        Node cur_node = linked_list.getStart_node();
        
        

        int i = 0;
        if (start_node != null) {

            while (cur_node.next != null && i < index) {

                cur_node = cur_node.next;
                i++;
            }

            cur_node.data = element;

        }
        linked_list.setNode_amount(node_amount);
        return linked_list;
    }

    @Override
    public int indexOf(Object element) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();
        linked_list.setStart_node(start_node);
        
        Node cur_node = linked_list.getStart_node();
        if (start_node != null) {
            for (int i = 0; i < node_amount; i++) {
                if (cur_node.data == element){
                    return  i;
                }
                cur_node = cur_node.next;

            }
        }
        return 0;
    }

    @Override
    public int size() {
        return node_amount;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        if (node_amount == 0){
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object [] elements = new Object[node_amount];
        Node cur_node = start_node;
        int i = 0;
        while (cur_node.next != null){
            elements[i] = cur_node.data;
            cur_node = cur_node.next;
        }
        return elements;
    }

    public ImmutableLinkedList addFirst(Object element) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();

        linked_list.setLast_node(last_node);
        Node cur_node = start_node;

        linked_list.setStart_node(new Node(element));
        linked_list.getStart_node().next = cur_node;
        linked_list.setNode_amount(node_amount + 1);

        return linked_list;
    }

    public ImmutableLinkedList addLast(Object element) {
        ImmutableLinkedList nw_list =  (ImmutableLinkedList) add(element);
        return nw_list;
    }

    public Node getHead() {
        return start_node;
    }

    public Node getTail() {
        Node cur_node = start_node;
        int i = 0;
        while (cur_node.next != null){
            cur_node = cur_node.next;
        }
        return cur_node;
    }

    public Object getFirst() {
        return start_node.data;
    }

    public Object getLast() {
        Node cur_node = start_node;
        int i = 0;
        while (cur_node.next != null){
            cur_node = cur_node.next;
        }
        return cur_node.data;
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();

        linked_list.setStart_node(start_node.copy_chain());
        Node cur_node = linked_list.getStart_node();
        Node nx_node = cur_node.next;
        linked_list.setStart_node(nx_node);
        
        linked_list.setNode_amount(node_amount - 1);
        return linked_list;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();

        linked_list.setStart_node(start_node.copy_chain());
        Node cur_node = linked_list.getStart_node();
        if (node_amount == 2) {
            cur_node.next = null;
        } else if (node_amount == 1) {
            linked_list.setStart_node(null);
        } else {
            while (cur_node.next.next != null) {
                cur_node = cur_node.next;
            }
            cur_node.next = null;


        }
        linked_list.setNode_amount(node_amount - 1);

        return linked_list;

    }



}
