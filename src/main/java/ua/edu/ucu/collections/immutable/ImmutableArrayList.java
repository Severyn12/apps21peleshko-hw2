package ua.edu.ucu.collections.immutable;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;
@Setter @Getter
public final class ImmutableArrayList implements ImmutableList {

    private Object[] data = new Object[1];
    private int elementNum = 0;

    public ImmutableArrayList(Object[] elements) {
        ImmutableArrayList nw_list = (ImmutableArrayList) new ImmutableArrayList().addAll(elements);
        data = nw_list.getData();
        elementNum = nw_list.getElementNum();
    }

    public ImmutableArrayList() {
    }

    @Override
    public ImmutableList add(Object element) {
        Object [] nw_data = null;
        ImmutableArrayList nw_list = new ImmutableArrayList();
        if (elementNum + 1 > data.length){
            nw_data = new Object[data.length*2];
        }
        else{
            nw_data = new Object[data.length];
        }
        nw_list.setData(copy_data(data, nw_data));
        nw_data[elementNum] = element;
        nw_list.setElementNum(elementNum + 1);

        return nw_list;
    }
    public Object[] copy_data(Object[] arr_1, Object[] arr_2){
        for (int i = 0; i < arr_1.length; i++){
            arr_2[i] = arr_1[i];
        }
        return arr_2;
    }
    @Override
    public ImmutableList add(int index, Object element) {
        Object [] nw_data = null;
        ImmutableArrayList nw_list = new ImmutableArrayList();
        if (elementNum + 1 > data.length) {
            nw_data = new Object[data.length * 2];
        }
        else{
            nw_data = new Object[data.length];
        }
        nw_data = copy_data(data, nw_data);
        if (index < nw_data.length) {


            Object cur_element = nw_data[index];
            nw_data[index] = element;

            for (int i = index + 1; i <= elementNum; i++) {
                Object nx_element = nw_data[i];
                nw_data[i] = cur_element;
                cur_element = nx_element;
            }
        }
        else{
            nw_data[elementNum] = element;
        }

        nw_list.setData(nw_data);
        nw_list.setElementNum(elementNum + 1);


        return nw_list;
    }

    @Override
    public ImmutableList addAll(Object[] elements) {
        Object [] nw_data = null;
        int size = 0;
        ImmutableArrayList nw_list = new ImmutableArrayList();
        if (data.length == 1){
            size = (elementNum + elements.length) + (elementNum + elements.length) % 2;
        }
        else if ( (elementNum + elements.length)%2 == 0){
            size = (int) Math.ceil((elementNum + elements.length)/ (float)data.length);
        }
        else {
            size = (int) Math.ceil((elementNum + elements.length) % 2 / (float) data.length);
        }
        if (elementNum + elements.length > data.length){
            nw_data = new Object[data.length+data.length*size];
        }
        else{
            nw_data = new Object[data.length];
        }
        nw_data = copy_data(data, nw_data);

        for (int i = 0; i < elements.length; i++){
            nw_data[elementNum+i] = elements[i];
        }

        nw_list.setData(nw_data);
        nw_list.setElementNum(elementNum + elements.length);
        return nw_list;
    }

    @Override
    public ImmutableList addAll(int index, Object[] elements) {
        Object [] nw_data = null;
        int size = 0;
        ImmutableArrayList nw_list = new ImmutableArrayList();
        if (data.length == 1){
            size = (elementNum + elements.length) + (elementNum + elements.length) % 2;
        }
        else if ( (elementNum + elements.length)%2 == 0){
            
            size = (int) Math.ceil((elementNum + elements.length)/ (float)data.length);
        }
        else {
            size = (int) Math.ceil((elementNum + elements.length) % 2 / (float) data.length);
        }
        if (elementNum + elements.length > data.length){

            nw_data = new Object[data.length+data.length*size];
        }
        else{
            nw_data = new Object[data.length];
        }
        nw_data = copy_data(data, nw_data);

        Object cur_element = nw_data[index];
        for (int i = 0; i < elements.length; i++){
            nw_data[index+i] = elements[i];
        }
        for (int i = index + 1; i <= elementNum; i++) {
            Object nx_element = nw_data[i + elements.length - 1];
            nw_data[i + elements.length - 1] = cur_element;
            cur_element = nx_element;
        }


        nw_list.setData(nw_data);
        nw_list.setElementNum(elementNum + elements.length);


        return nw_list;
    }

    @Override
    public Object get(int index) {
        if (index < data.length){
            return data[index];
        }
        return null;
    }

    @Override
    public ImmutableList remove(int index) {
        Object [] nw_data = null;
        nw_data = data.clone();
        ImmutableArrayList nw_list = new ImmutableArrayList();
        if (elementNum > 1) {
            for (int i = index; i < elementNum; i++) {
                nw_data[i] = data[i + 1];
            }
        }
        else {
            nw_data[0] = null;
        }
        nw_list.setData(nw_data);
        nw_list.setElementNum(elementNum - 1);


        return nw_list;
    }

    @Override
    public ImmutableList set(int index, Object element) {
        Object [] nw_data = null;
        nw_data = data.clone();
        ImmutableArrayList nw_list = new ImmutableArrayList();
        nw_data[index] = element;
        nw_list.setData(nw_data);
        nw_list.setElementNum(elementNum);
        return nw_list;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < data.length; i++){

            if (data[i].equals(element)){
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
        Object [] elements = new Object[elementNum];
        for (int i = 0; i < elementNum; i++){
            elements[i] = data[i];
        }
        return elements;
    }


}
