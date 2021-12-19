package ua.edu.ucu.collections.immutable;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter @Getter
public final class ImmutableLinkedList implements ImmutableList{
    private Node startNode = null;
    private Node lastNode = null;
    private int nodeAmount = 0;

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

    public ImmutableLinkedList(Object [] elements) {
        ImmutableLinkedList nw_list = (ImmutableLinkedList) new ImmutableLinkedList().addAll(elements);
        nodeAmount = nw_list.getNodeAmount();
        startNode = nw_list.getStartNode();
        lastNode = nw_list.getLastNode();

    }

    public ImmutableLinkedList() {
    }

    @Override
    public ImmutableList add(Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        if (startNode == null){
            linkedList.setStartNode(new Node(element));
            linkedList.setLastNode(new Node(element));
        }
        else {

            linkedList.setStartNode(startNode.copy_chain());
            Node curNode = linkedList.getStartNode();


            while (curNode.next != null) {

                curNode = curNode.next;


            }
            curNode.next = new Node(element);
        }


        linkedList.setNodeAmount(nodeAmount + 1);
        return linkedList;
    }

    @Override
    public ImmutableList add(int index, Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        int i = 0;


        if (startNode != null) {
            linkedList.setStartNode(startNode.copy_chain());
            Node curNode = linkedList.getStartNode();

            while (curNode.next != null && i < index-1) {

                curNode = curNode.next;
                i++;
            }
            if (curNode.next != null) {
                Node next_node = curNode.next;


                curNode.next = new Node(element);
                curNode.next.next = next_node;

            }
            else {
                curNode.next = new Node(element);
            }


        }
        else{
            linkedList.setStartNode(new Node(element));
        }
        linkedList.setNodeAmount(nodeAmount + 1);
        return linkedList;
    }

    @Override
    public ImmutableList addAll(Object[] elements) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        Node curNode = null;

        if (startNode != null) {
            linkedList.setStartNode(startNode.copy_chain());
            curNode = linkedList.getStartNode();
            while (curNode.next != null) {

                curNode = curNode.next;
            }


            curNode.next = new Node(elements[0]);

            curNode = curNode.next;
        }
        else{
            linkedList.setStartNode(new Node(elements[0]));
            curNode = linkedList.getStartNode();
        }

        elements = Arrays.copyOfRange(elements, 1, elements.length);
        for (int i = 0; i < elements.length; i++){
            curNode.next = new Node(elements[i]);
            curNode = curNode.next;
        }

        linkedList.setNodeAmount(nodeAmount + elements.length + 1);

        return linkedList;
        }


    @Override
    public ImmutableList addAll(int index, Object[] elements) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        int i = 0;
        Node curNode = null;
        if (startNode != null) {
            linkedList.setStartNode(startNode.copy_chain());
            curNode = linkedList.getStartNode();
            while (curNode.next != null && i < index-1) {
                i++;
                curNode = curNode.next;
            }
        }

        ImmutableLinkedList nw_link_lst = new ImmutableLinkedList(elements);
        if (curNode == null){
            linkedList.setStartNode(nw_link_lst.getStartNode());

        }
        else{

            if (curNode.next == null){

                curNode.next = nw_link_lst.getStartNode();
            }
            else {

                Node nx_node = curNode.next;
                curNode.next = nw_link_lst.getStartNode();
                curNode = curNode.next;
                while (curNode.next != null) {

                    curNode = curNode.next;
                }
                curNode.next = nx_node;
            }

        }


        linkedList.setNodeAmount(nodeAmount + elements.length);
        return linkedList;
    }

    @Override
    public Object get(int index) {

        Node curNode = startNode;
        int i = 0;
        if (startNode != null) {

            while (curNode.next != null && i < index) {

                curNode = curNode.next;
                i++;
            }
            
        }
        return curNode.data;

    }

    @Override
    public ImmutableList remove(int index) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        linkedList.setStartNode(startNode.copy_chain());
        
        Node curNode = linkedList.getStartNode();
        int i = 0;
        if (startNode != null) {

            while (curNode.next != null && i < index-1) {

                curNode = curNode.next;
                i++;
            }
            Node nx_test = curNode.next.next;
            curNode.next = nx_test;

        }
        linkedList.setNodeAmount(nodeAmount - 1);
        return linkedList;
    }

    @Override
    public ImmutableList set(int index, Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        linkedList.setStartNode(startNode.copy_chain());
        Node cur_node = linkedList.getStartNode();
        
        

        int i = 0;
        if (startNode != null) {

            while (cur_node.next != null && i < index) {

                cur_node = cur_node.next;
                i++;
            }

            cur_node.data = element;

        }
        linkedList.setNodeAmount(nodeAmount);
        return linkedList;
    }

    @Override
    public int indexOf(Object element) {
        ImmutableLinkedList linked_list = new ImmutableLinkedList();
        linked_list.setStartNode(startNode);
        
        Node cur_node = linked_list.getStartNode();
        if (startNode != null) {
            for (int i = 0; i < nodeAmount; i++) {
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
        return nodeAmount;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        if (nodeAmount == 0){
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object [] elements = new Object[nodeAmount];
        Node curNode = startNode;
        int i = 0;
        while (curNode.next != null){
            elements[i] = curNode.data;
            curNode = curNode.next;
        }
        return elements;
    }

    public ImmutableLinkedList addFirst(Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        linkedList.setLastNode(lastNode);
        Node curNode = startNode;

        linkedList.setStartNode(new Node(element));
        linkedList.getStartNode().next = curNode;
        linkedList.setNodeAmount(nodeAmount + 1);

        return linkedList;
    }

    public ImmutableLinkedList addLast(Object element) {
        ImmutableLinkedList nwList =  (ImmutableLinkedList) add(element);
        return nwList;
    }

    public Node getHead() {
        return startNode;
    }

    public Node getTail() {
        Node curNode = startNode;
        while (curNode.next != null){
            curNode = curNode.next;
        }
        return curNode;
    }

    public Object getFirst() {
        return startNode.data;
    }

    public Object getLast() {
        Node curNode = startNode;
        while (curNode.next != null){
            curNode = curNode.next;
        }
        return curNode.data;
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        linkedList.setStartNode(startNode.copy_chain());
        Node curNode = linkedList.getStartNode();
        Node nxNode = curNode.next;
        linkedList.setStartNode(nxNode);

        linkedList.setNodeAmount(nodeAmount - 1);
        return linkedList;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        linkedList.setStartNode(startNode.copy_chain());
        Node curNode = linkedList.getStartNode();
        if (nodeAmount == 2) {
            curNode.next = null;
        } else if (nodeAmount == 1) {
            linkedList.setStartNode(null);
        } else {
            while (curNode.next.next != null) {
                curNode = curNode.next;
            }
            curNode.next = null;


        }
        linkedList.setNodeAmount(nodeAmount - 1);

        return linkedList;

    }

    @Override
    public String toString() {
        return "ImmutableLinkedList{" +
                "start_node=" + startNode +
                '}';
    }



}
