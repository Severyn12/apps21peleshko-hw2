package ua.edu.ucu.collections.immutable;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter @Getter
public final class ImmutableLinkedList implements ImmutableList {
    private Node startNode = null;
    private Node lastNode = null;
    private int nodeAmount = 0;

    private class Node {
        private Object data;
        private Node next;

        private Node(Object info) {
            this.data = info;
            this.next = null;
        }

        private Node copy() {
            Node nwNode = new Node(data);
            nwNode.next = next;

            return nwNode;
        }
        private Node copyChain() {
            Node curNode = this.copy();
            Node stNode = curNode;
            while (curNode.next != null) {
                curNode.next = curNode.next.copy();
                curNode = curNode.next;
            }
            return stNode;
        }

        @Override
        public String toString() {
            return "Node{"
                   + "data=" + data
                   + ", next=" + next
                   + '}';
        }
    }

    public ImmutableLinkedList(Object[] elements) {
        ImmutableLinkedList nwList = (ImmutableLinkedList)
                new ImmutableLinkedList().addAll(elements);
        nodeAmount = nwList.getNodeAmount();
        startNode = nwList.getStartNode();
        lastNode = nwList.getLastNode();

    }

    public ImmutableLinkedList() {
    }

    @Override
    public ImmutableList add(Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        if (startNode == null) {
            linkedList.setStartNode(new Node(element));
            linkedList.setLastNode(new Node(element));
        }
        else {

            linkedList.setStartNode(startNode.copyChain());
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
            linkedList.setStartNode(startNode.copyChain());
            Node curNode = linkedList.getStartNode();

            while (curNode.next != null && i < index - 1) {

                curNode = curNode.next;
                i++;
            }
            if (curNode.next != null) {
                Node nextNode = curNode.next;


                curNode.next = new Node(element);
                curNode.next.next = nextNode;

            }
            else {
                curNode.next = new Node(element);
            }


        }
        else {
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
            linkedList.setStartNode(startNode.copyChain());
            curNode = linkedList.getStartNode();
            while (curNode.next != null) {

                curNode = curNode.next;
            }


            curNode.next = new Node(elements[0]);

            curNode = curNode.next;
        }
        else {
            linkedList.setStartNode(new Node(elements[0]));
            curNode = linkedList.getStartNode();
        }

        elements = Arrays.copyOfRange(elements, 1, elements.length);
        for (int i = 0; i < elements.length; i++) {
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
            linkedList.setStartNode(startNode.copyChain());
            curNode = linkedList.getStartNode();
            while (curNode.next != null && i < index - 1) {
                i++;
                curNode = curNode.next;
            }
        }

        ImmutableLinkedList nwLinkLst = new ImmutableLinkedList(elements);
        if (curNode == null) {
            linkedList.setStartNode(nwLinkLst.getStartNode());

        }
        else {

            if (curNode.next == null) {

                curNode.next = nwLinkLst.getStartNode();
            }
            else {

                Node nxNode = curNode.next;
                curNode.next = nwLinkLst.getStartNode();
                curNode = curNode.next;
                while (curNode.next != null) {

                    curNode = curNode.next;
                }
                curNode.next = nxNode;
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
        linkedList.setStartNode(startNode.copyChain());
        Node curNode = linkedList.getStartNode();
        int i = 0;
        if (startNode != null) {

            while (curNode.next != null && i < index - 1) {

                curNode = curNode.next;
                i++;
            }
            Node nxTest = curNode.next.next;
            curNode.next = nxTest;

        }
        linkedList.setNodeAmount(nodeAmount - 1);
        return linkedList;
    }

    @Override
    public ImmutableList set(int index, Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        linkedList.setStartNode(startNode.copyChain());
        Node curNode = linkedList.getStartNode();

        int i = 0;
        if (startNode != null) {

            while (curNode.next != null && i < index) {

                curNode = curNode.next;
                i++;
            }

            curNode.data = element;

        }
        linkedList.setNodeAmount(nodeAmount);
        return linkedList;
    }

    @Override
    public int indexOf(Object element) {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        linkedList.setStartNode(startNode);
        Node curNode = linkedList.getStartNode();
        if (startNode != null) {
            for (int i = 0; i < nodeAmount; i++) {
                if (curNode.data == element) {
                    return  i;
                }
                curNode = curNode.next;

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
        if (nodeAmount == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] elements = new Object[nodeAmount];
        Node curNode = startNode;
        int i = 0;
        while (curNode.next != null) {
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
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        return curNode;
    }

    public Object getFirst() {
        return startNode.data;
    }

    public Object getLast() {
        Node curNode = startNode;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        return curNode.data;
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        linkedList.setStartNode(startNode.copyChain());
        Node curNode = linkedList.getStartNode();
        Node nxNode = curNode.next;
        linkedList.setStartNode(nxNode);

        linkedList.setNodeAmount(nodeAmount - 1);
        return linkedList;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();

        linkedList.setStartNode(startNode.copyChain());
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
        return "ImmutableLinkedList{"
                + "start_node=" + startNode
                + '}';
    }



}
