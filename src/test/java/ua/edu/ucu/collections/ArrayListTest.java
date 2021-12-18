package ua.edu.ucu.collections;


import org.junit.Test;
import ua.edu.ucu.collections.immutable.ImmutableArrayList;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {
    ImmutableArrayList arr_1 = new ImmutableArrayList();

    @Test
    public void addTest(){
        ImmutableArrayList arr_2 = (ImmutableArrayList) arr_1.add(123);
        assertEquals(arr_2.getElementNum(),1);
        ImmutableArrayList arr_3 = (ImmutableArrayList) arr_2.add(1023);
        assertEquals(Arrays.toString(arr_2.getData()),"[123]");
        assertEquals(arr_3.getElementNum(),2);
        assertEquals(Arrays.toString(arr_3.getData()),"[123, 1023]");
        ImmutableArrayList arr_4 = (ImmutableArrayList) arr_3.addAll(new Object []{2,6,34,9});
        assertEquals(arr_4.getElementNum(),6);
        ImmutableArrayList arr_5 = (ImmutableArrayList) arr_4.addAll(new Object []{1,3,5,7});
        assertEquals(Arrays.toString(arr_4.getData()),"[123, 1023, 2, 6, 34, 9, null, null]");
        assertEquals(arr_5.getElementNum(),10);
    }
    @Test
    public void addIndexTest(){
        ImmutableArrayList arr_2 = (ImmutableArrayList) arr_1.add(34,56);
        assertEquals(arr_2.getElementNum(),1);
        assertEquals(Arrays.toString(arr_2.getData()),"[56]");
        ImmutableArrayList arr_3 = (ImmutableArrayList) arr_2.add(0,956);
        assertEquals(arr_3.getElementNum(),2);
        assertEquals(Arrays.toString(arr_3.getData()),"[956, 56]");
        ImmutableArrayList arr_4 = (ImmutableArrayList) arr_3.addAll(1,new Object []{1,3,5,7});
        assertEquals(arr_4.getElementNum(),6);
        assertEquals(Arrays.toString(arr_4.getData()),"[956, 1, 3, 5, 7, 56, null, null]");

    }
    @Test
    public void removeTest() {
        ImmutableArrayList arr_2 = (ImmutableArrayList) arr_1.add(0,10);
        assertEquals(arr_2.getElementNum(),1);
        ImmutableArrayList arr_3 = (ImmutableArrayList) arr_2.remove(0);
        assertEquals(arr_3.getElementNum(),0);
        assertEquals(Arrays.toString(arr_3.getData()),"[null]");
        ImmutableArrayList arr_4 = (ImmutableArrayList) arr_2.addAll(0,new Object []{2, 4, 6, 8});
        ImmutableArrayList arr_5 = (ImmutableArrayList) arr_4.remove(1);
        assertEquals(arr_5.getElementNum(),4);
        assertEquals(Arrays.toString(arr_5.getData()),"[2, 6, 8, 10, null, null, null]");
    }
    @Test
    public void otherFuncTest(){
        ImmutableArrayList arr_2 = (ImmutableArrayList) arr_1.add(0,100);
        assertEquals(arr_2.get(0),100);
        assertEquals(arr_2.get(3),null);
        ImmutableArrayList arr_3 = (ImmutableArrayList) arr_2.set(0,200);
        assertEquals(arr_3.getElementNum(),1);
        assertEquals(Arrays.toString(arr_3.getData()),"[200]");
        assertEquals(arr_3.indexOf(100),-1);
        assertEquals(arr_3.indexOf(200),0);
        assertEquals(arr_2.size(),1);
        ImmutableArrayList arr_4 = (ImmutableArrayList) arr_3.clear();
        assertEquals(arr_4.size(),0);
        assertEquals(arr_4.isEmpty(),true);
        assertEquals(arr_3.isEmpty(),false);
        ImmutableArrayList arr_5 = new ImmutableArrayList(new Object []{2, 4, 6, 8});
        assertEquals(arr_5.getElementNum(),4);
        assertEquals(Arrays.toString(arr_5.toArray()),"[2, 4, 6, 8]");
    }

}
