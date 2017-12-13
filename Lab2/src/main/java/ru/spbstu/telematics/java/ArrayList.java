package ru.spbstu.telematics.java;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

public class ArrayList<T> implements Iterable<T>{

    private T[] data;

    private int size;
    private int capacity;

    ArrayList() {
        capacity = 1;
        data = (T[]) new Object[capacity];
        size = 0;
    }

    ArrayList(int capacity){
        data = (T[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public class ArrayListIterator implements ListIterator<T>{

        private int index;
        private int lastReturn;

        ArrayListIterator(){
            index = 0;
            lastReturn = -1;
        }

        public void add(T t) {
            if(capacity - size > 0) {
                int tmpCapacity = (capacity * 3) / 2 + 1;
                T[] tmpData = (T[]) new Object[tmpCapacity];
                System.arraycopy(data, 0, tmpData, 0, size);
                capacity = tmpCapacity;
                data = tmpData;
            }
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index++] =  t;
            lastReturn = -1;
            size++;
        }

        public void remove() throws IllegalStateException{
            if(lastReturn > -1) {
                System.arraycopy(data, lastReturn + 1, data, lastReturn, size - lastReturn - 1);
                data[--size] = null;
            }else
                throw new IllegalStateException();
        }

        public void set(T t) throws IllegalStateException{
            if(lastReturn > -1)
                data[lastReturn] = t;
            else
                throw new IllegalStateException();
        }

        public int nextIndex() {
            if(index < size - 1)
                return index;
            else
                return size;
        }

        public int previousIndex() {
            return index - 1;
        }

        public T next() throws NoSuchElementException {
            if(index < size) {
                lastReturn = index;
                return data[index++];
            }else
                throw new NoSuchElementException();

        }

        public T previous() throws NoSuchElementException {
            if(index > -1) {
                lastReturn = --index;
                return data[index];
            }else
                throw new NoSuchElementException();
        }

        public boolean hasNext() {
            return index < size - 1;
        }

        public boolean hasPrevious() {
            return index > 0;
        }
    }

    public int size(){
        return size;
    }

    private boolean checkCapacity(){ return (capacity - size) > 0; }

    private void resize() {
        int tmpCapacity = (capacity * 3) / 2 + 1;
        T[] tmpData = (T[]) new Object[tmpCapacity];
        System.arraycopy(data, 0, tmpData, 0, size);
        capacity = tmpCapacity;
        data = tmpData;
    }

    public boolean add(T element){
        if(!checkCapacity())
            resize();
        data[size++] = element;
        return true;
    }

    public boolean add(int index, T element) throws IndexOutOfBoundsException {
        if(index > -1 && index < size) {
            if(!checkCapacity())
                resize();
            size++;
            System.arraycopy(data, index, data, index + 1, size - index - 1);
            data[index] = element;
        }else
            throw new IndexOutOfBoundsException();
        return true;
    }

    public T set(int index, T element) throws IndexOutOfBoundsException {
        if(index > -1 && index < size){
            T tmp = data[index];
            data[index] = element;
            return tmp;
        }else
            throw new IndexOutOfBoundsException();
    }

    public T remove(int index) throws IndexOutOfBoundsException {
        if(index > -1 && index < size){
            T tmp = data[index];
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            data[--size] = null;
            return tmp;
        }else
            throw new IndexOutOfBoundsException();
    }

    public boolean remove(Object o){
        for(int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o){
        for(int i = 0; i < size; i++)
            if (o.equals(data[i]))
                return true;
        return false;
    }

    public T get(int index) throws IndexOutOfBoundsException{
        if(index > -1 && index < size)
            return data[index];
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass().getName().equals(this.getClass().getName())) {
            ArrayList<T> list = (ArrayList<T>) obj;
            if(size == list.size) {
                for (int i = 0; i < size; i++)
                    if (!data[i].equals(list.data[i]))
                        return false;
                return true;
            }else
                return false;
        }
        if(obj.getClass().getName().equals(java.util.ArrayList.class.getName())) {
            java.util.ArrayList<T> list = (java.util.ArrayList<T>) obj;
            if (size == list.size()) {
                for (int i = 0; i < size; i++)
                    if (!data[i].equals(list.get(i)))
                        return false;
                return true;
            }else
                return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        final int prime = 3;
        return (size*capacity*capacity - 1)*size*capacity / prime;
    }

    public ListIterator<T> iterator(){
        return new ArrayListIterator();
    }
}
