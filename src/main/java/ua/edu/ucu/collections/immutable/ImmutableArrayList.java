package ua.edu.ucu.collections.immutable;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public final class ImmutableArrayList implements ImmutableList {

    private Object[] data = new Object[1];
    private int elementNum = 0;

    public ImmutableArrayList(Object[] elements) {
        ImmutableArrayList nwList = (ImmutableArrayList) new ImmutableArrayList().addAll(elements);
        data = nwList.getData();
        elementNum = nwList.getElementNum();
    }

    public ImmutableArrayList() {
    }

    @Override
    public ImmutableList add(Object element) {
        Object[] nwData = null;
        ImmutableArrayList nwList = new ImmutableArrayList();
        if (elementNum + 1 > data.length) {
            nwData = new Object[data.length * 2];
        }
        else {
            nwData = new Object[data.length];
        }
        nwList.setData(copyData(data, nwData));
        nwData[elementNum] = element;
        nwList.setElementNum(elementNum + 1);

        return nwList;
    }
    public Object[] copyData(Object[] arr1, Object[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }
    @Override
    public ImmutableList add(int index, Object element) {
        Object[] nwData = null;
        ImmutableArrayList nwList = new ImmutableArrayList();
        if (elementNum + 1 > data.length) {
            nwData = new Object[data.length * 2];
        }
        else {
            nwData = new Object[data.length];
        }
        nwData = copyData(data, nwData);
        if (index < nwData.length) {


            Object cur_element = nwData[index];
            nwData[index] = element;

            for (int i = index + 1; i <= elementNum; i++) {
                Object nx_element = nwData[i];
                nwData[i] = cur_element;
                cur_element = nx_element;
            }
        }
        else {
            nwData[elementNum] = element;
        }

        nwList.setData(nwData);
        nwList.setElementNum(elementNum + 1);


        return nwList;
    }

    @Override
    public ImmutableList addAll(Object[] elements) {
        Object[] nwData = null;
        int size = 0;
        ImmutableArrayList nwList = new ImmutableArrayList();
        if (data.length == 1) {
            size = (elementNum + elements.length) + (elementNum + elements.length) % 2;
        }
        else if ((elementNum + elements.length) % 2 == 0) {
            size = (int) Math.ceil((elementNum + elements.length) / (float)data.length);
        }
        else {
            size = (int) Math.ceil((elementNum + elements.length) % 2 / (float) data.length);
        }
        if (elementNum + elements.length > data.length) {
            nwData = new Object[data.length + data.length * size];
        }
        else {
            nwData = new Object[data.length];
        }
        nwData = copyData(data, nwData);

        for (int i = 0; i < elements.length; i++) {
            nwData[elementNum + i] = elements[i];
        }

        nwList.setData(nwData);
        nwList.setElementNum(elementNum + elements.length);
        return nwList;
    }

    @Override
    public ImmutableList addAll(int index, Object[] elements) {
        Object[] nwData = null;
        int size = 0;
        ImmutableArrayList nwList = new ImmutableArrayList();
        if (data.length == 1) {
            size = (elementNum + elements.length) + (elementNum + elements.length) % 2;
        }
        else if ((elementNum + elements.length) % 2 == 0) {
            
            size = (int) Math.ceil((elementNum + elements.length) / (float)data.length);
        }
        else {
            size = (int) Math.ceil((elementNum + elements.length) % 2 / (float) data.length);
        }
        if (elementNum + elements.length > data.length) {

            nwData = new Object[data.length + data.length * size];
        }
        else {
            nwData = new Object[data.length];
        }
        nwData = copyData(data, nwData);

        Object cur_element = nwData[index];
        for (int i = 0; i < elements.length; i++) {
            nwData[index + i] = elements[i];
        }
        for (int i = index + 1; i <= elementNum; i++) {
            Object nx_element = nwData[i + elements.length - 1];
            nwData[i + elements.length - 1] = cur_element;
            cur_element = nx_element;
        }


        nwList.setData(nwData);
        nwList.setElementNum(elementNum + elements.length);


        return nwList;
    }

    @Override
    public Object get(int index) {
        if (index < data.length) {
            return data[index];
        }
        return null;
    }

    @Override
    public ImmutableList remove(int index) {
        Object[] nwData = null;
        nwData = data.clone();
        ImmutableArrayList nwList = new ImmutableArrayList();
        if (elementNum > 1) {
            for (int i = index; i < elementNum; i++) {
                nwData[i] = data[i + 1];
            }
        }
        else {
            nwData[0] = null;
        }
        nwList.setData(nwData);
        nwList.setElementNum(elementNum - 1);


        return nwList;
    }

    @Override
    public ImmutableList set(int index, Object element) {
        Object[] nwData = null;
        nwData = data.clone();
        ImmutableArrayList nwList = new ImmutableArrayList();
        nwData[index] = element;
        nwList.setData(nwData);
        nwList.setElementNum(elementNum);
        return nwList;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < data.length; i++) {

            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return elementNum;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        if (elementNum == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] elements = new Object[elementNum];
        for (int i = 0; i < elementNum; i++) {
            elements[i] = data[i];
        }
        return elements;
    }


}
