package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import org.junit.Test;


import static org.junit.Assert.*;

public class LinkedListTest {

    ImmutableLinkedList linkedList = new ImmutableLinkedList();
    @Test
    public void addTest(){
        ImmutableLinkedList nwLinkedList = (ImmutableLinkedList) linkedList.add(12);
        assertEquals(nwLinkedList.getNode_amount(),1);

        ImmutableLinkedList nwLinkedList_2 = (ImmutableLinkedList) nwLinkedList.addAll(new Integer[] {124,23,45});
        assertEquals(nwLinkedList_2.getNode_amount(),4);

        ImmutableLinkedList nwLinkedList_3 = (ImmutableLinkedList) nwLinkedList_2.add(2,70);
        assertEquals(nwLinkedList_3.toString(),"ImmutableLinkedList{" +
                "start_node=Node{data=12, next=Node{data=124, next=Node{data=70," +
                " next=Node{data=23, next=Node{data=45, next=null}}}}}}");

        ImmutableLinkedList nwLinkedList_4 = (ImmutableLinkedList) new ImmutableLinkedList().add(2,70);
        assertEquals(nwLinkedList_4.toString(),"ImmutableLinkedList{start_node=Node{data=70, next=null}}");

        ImmutableLinkedList nwLinkedList_5 =  new ImmutableLinkedList(new Integer[] {1,2,3});
        assertEquals(nwLinkedList_5.getNode_amount(),3);

        ImmutableLinkedList nwLinkedList_6 = (ImmutableLinkedList) nwLinkedList_5.addAll(2,new Integer[] {124,56});
        assertEquals(nwLinkedList_6.toString(),"ImmutableLinkedList{start_node=Node{data=1, next=Node{data=2, " +
                "next=Node{data=124, next=Node{data=56, next=Node{data=3, next=null}}}}}}");
        assertEquals(nwLinkedList_6.getNode_amount(),5);

        ImmutableLinkedList nwLinkedList_7 =
                (ImmutableLinkedList) new ImmutableLinkedList().addAll(10,new Integer[]{70});
        assertEquals(nwLinkedList_7.toString(),"ImmutableLinkedList{start_node=Node{data=70, next=null}}");
    }

    @Test
    public void otherFunctionsTest(){
        ImmutableLinkedList nwLinkedList_2 = (ImmutableLinkedList) linkedList.addAll(new Integer[] {124,23,45});
        assertEquals(nwLinkedList_2.get(1),23);
        assertEquals(nwLinkedList_2.get(0),124);
        ImmutableLinkedList nwLinkedList_3 = (ImmutableLinkedList) nwLinkedList_2.remove(2);
        assertEquals(nwLinkedList_3.toString(),"ImmutableLinkedList{start_node=Node{data=124, " +
                "next=Node{data=23, next=null}}}");
        ImmutableLinkedList nwLinkedList_4 = (ImmutableLinkedList) nwLinkedList_3.set(0,100);
        assertEquals(nwLinkedList_4.get(0),100);
        assertEquals(nwLinkedList_4.getNode_amount(),2);
        assertEquals(nwLinkedList_2.indexOf(23),1);
        ImmutableLinkedList nwLinkedList_5 = (ImmutableLinkedList) nwLinkedList_2.clear();
        assertEquals(nwLinkedList_5.size(),0);
        assertEquals(nwLinkedList_2.toArray().length,3);
        ImmutableLinkedList nwLinkedList_6 = nwLinkedList_2.addFirst(90);
        assertEquals(nwLinkedList_6.toString(),"ImmutableLinkedList{start_node=Node{data=90, next=Node{data=124, " +
                "next=Node{data=23, next=Node{data=45, next=null}}}}}");
        ImmutableLinkedList nwLinkedList_7 = nwLinkedList_2.addLast(340);
        assertEquals(nwLinkedList_7.toString(),"ImmutableLinkedList{start_node=Node{data=124, next=Node{data=23, " +
                "next=Node{data=45, next=Node{data=340, next=null}}}}}");
        assertEquals(nwLinkedList_3.getFirst(),124);
        assertEquals(nwLinkedList_3.getLast(),23);
        ImmutableLinkedList nwLinkedList_8 = nwLinkedList_2.removeFirst();
        assertEquals(nwLinkedList_8.toString(),"ImmutableLinkedList{start_node=Node{data=23, " +
                "next=Node{data=45, next=null}}}");
        ImmutableLinkedList nwLinkedList_9 = nwLinkedList_2.removeLast();
        assertEquals(nwLinkedList_9.toString(),"ImmutableLinkedList{start_node=Node{data=124, " +
                "next=Node{data=23, next=null}}}");
    }

}
